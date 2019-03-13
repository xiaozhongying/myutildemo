package common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alibaba.fastjson.JSONObject;

import dswork.core.util.EncryptUtil;
import dswork.core.util.TimeUtil;

public class HttpClientUtil
{

	/**
	 * 获取Opac的token
	 */
	public static String getOpacToken(){
		HttpClient client=null;
		PostMethod post=null;
		try{
			//创建连接
			client=new HttpClient();
			post=new PostMethod("http://10.194.200.30/opac/digitalUnions/getToken");
			//设置编码
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			//添加参数
			post.addParameter("tokenName","gzlib");
			post.addParameter("tokenPass","tcinterlib");
			 // 执行请求
			client.executeMethod(post);
			String info=new String(post.getResponseBody(),"UTF-8");
			JSONObject json=JSONObject.parseObject(info);
			return json.getString("data").toString();
		}catch (Exception e) {
			// TODO: handle exception
			return "";
		}finally {
			post.releaseConnection();
		}
	}
	/**
	 * 获取28接口的token
	 * @return
	 */
	public static String getToken(){
		HttpClient client=null;
		PostMethod post=null;
		try{
			//创建连接
			client=new HttpClient();
			post=new PostMethod("http://10.194.200.128/interlibSSO/AuthorizationServlet");
			//设置编码
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			//添加参数
			String enc="appid"+TimeUtil.getCurrentTime("yyyy-MM-ddHH")+"statickey";
			post.addParameter("cmdACT","getToken");
			post.addParameter("appid","appid");
			post.addParameter("enc",EncryptUtil.encryptMd5(enc).toLowerCase());
			 // 执行请求
			client.executeMethod(post);
			String info=new String(post.getResponseBody(),"UTF-8");
			JSONObject json=JSONObject.parseObject(info);
			return json.getString("token").toString();
		}catch (Exception e) {
			// TODO: handle exception
			return "";
		}finally {
			post.releaseConnection();
		}
	}
	/**
	 * 获取28的token
	 */
	/**
	 * 验证读者是否办证，返回证件信息 key:status  value : 1 有效  2 读者证号失效 0 未办理
	 * @param uid 读者证号
	 * @param password 密码
	 * @return 判断是否办证，证件是否有效
	 * 
	 */
	public static Map<String, String> validationUser(String uid,String password){
		Map<String, String> map=new HashMap<>();
		HttpClient client=null;
		PostMethod post=null;
		try{
			//创建连接
			client=new HttpClient();
			post=new PostMethod("http://10.194.200.30/opac/digitalUnions/validationUser");
			//设置编码
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			//添加参数
			String time=TimeUtil.getCurrentTime("yyyyMMddHHmmss");
			//握手秘钥
			String ws="GT2019~)*";
			//32位小写加密
			String sn=EncryptUtil.encryptMd5(uid+ws+password+time).toLowerCase();
			post.addParameter("token",HttpClientUtil.getOpacToken());
			post.addParameter("time",time);
			post.addParameter("sn",sn);
			post.addParameter("uid",uid);
			 // 执行请求
			client.executeMethod(post);
			String info=new String(post.getResponseBody(),"UTF-8");
			System.out.println(info);
			JSONObject json=JSONObject.parseObject(info);
			if(json.get("code").toString().equals("1")){//身份证有效
				System.out.println(1111111111);
				map.put("status", "1");
			}else if(json.get("code").toString().equals("0")){
				if(json.get("rdcfstate")!=null){
					map.put("status", "2");
				}else{
					map.put("status", "0");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			post.releaseConnection();
		}
		return map;
	}
	/**
	 * 判断密码是否过于简单
	 * true:不是
	 * false:是
	 * @param password
	 * @return
	 */
	public static boolean validationPassword(String password){
		HttpClient client=null;
		PostMethod post=null;
		boolean flag=false;
		
		try{
			//创建连接
			client=new HttpClient();
			post=new PostMethod("http://10.194.200.30/opac/digitalUnions/checkPassStrong");
			//设置编码
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			//添加参数
			post.addParameter("token",HttpClientUtil.getOpacToken());
			post.addParameter("password",password);
			 // 执行请求
			client.executeMethod(post);
			String info=new String(post.getResponseBody(),"UTF-8");
			JSONObject json=JSONObject.parseObject(info);
			//如果密码强度符合要求
			if(json.get("code").toString().equals("200")){
				flag=true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			post.releaseConnection();
		}
		return flag;
	}
	/**
	 * 添加读者的接口
	 * @param rdCertify 身份证号
	 * @param rdAddress 地址
	 * @param Phone 电话
	 * @param rdid 读者证号
	 * @param rdpassword 密码
	 * @return 1 成功  0失败
	 * @throws Exception
	 */
	public static String addReader(String rdCertify,String rdAddress,String Phone,String rdid,String rdpassword) throws Exception{
		//################时间部分##########################//			
		String hms=TimeUtil.getCurrentTime("HH:mm:ss");
		Calendar calendar=Calendar.getInstance();
		calendar.add(calendar.YEAR,1);
		Date time=calendar.getTime();
		DateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		//################参数部分##########################//
		String token=HttpClientUtil.getOpacToken();//获取token
		String startDate=TimeUtil.getCurrentTime("yyyy-MM-dd")+"T"+hms;//当前时间
		String endDate=f.format(time)+"T"+hms;//明年的这个时候
		String url="http://10.194.200.30/opac/digitalUnions/readerWebservice";
		PostMethod postMethod = new PostMethod(url+"?token="+token);
		String soapRequestData = ""
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
				+ "xmlns:web=\"http://webservice.opac.interlib.com/\"><soapenv:Header/>"
				+ "<soapenv:Body><web:addReader><!--Optional:-->"
				+ "<reader>"
				+ "<endDate>"+endDate+"</endDate>"
				+ "<rdAddress>"+rdAddress+"</rdAddress>"
				+ "<rdCertify>"+rdCertify+"</rdCertify>"
				+ "<rdLib>GZL</rdLib><rdName>先生</rdName>"
				+ "<rdPasswd>"+rdpassword+"</rdPasswd><rdType>999</rdType><rdcfstate>1</rdcfstate>"
				+ "<rdid>"+rdid+"</rdid><regman>admin</regman>"
				+ "<startDate>"+startDate+"</startDate>"
				+ "<rdPhone>"+Phone+"</rdPhone></reader>"
				+ "<indate>12</indate></web:addReader>"
				+ "</soapenv:Body></soapenv:Envelope>";
		byte[] b = soapRequestData.toString().getBytes("utf-8");;
		InputStream is = new ByteArrayInputStream(b, 0, b.length);
		RequestEntity re = new InputStreamRequestEntity(is, b.length, "text/xml; charset=UTF-8");
		postMethod.setRequestEntity(re);
		HttpClient httpClient = new HttpClient();
		int status = httpClient.executeMethod(postMethod);
		String value="0";
		if (status == 200) {
			String msg=dom4jUtil.getXMLString(postMethod.getResponseBodyAsString());
			if(msg.equals("OK")){
				value="1";
			}
		}
		return value;
	}
	/**
	 * 修改密码
	 * @param rdid  读者证号
	 * @param newpwd 新密码
	 * @param oldpwd 旧密码
	 * @return 1 成功 0失败
	 * @throws 
	 */
	public static String updReader(String rdid,String newpwd,String oldpwd) throws Exception{
		String url="http://10.194.200.30/opac/digitalUnions/readerWebservice";
		String pwssword=EncryptUtil.encryptMd5(oldpwd);
		PostMethod postMethod = new PostMethod(url+"?token="+HttpClientUtil.getOpacToken());
		String soapRequestData = ""
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webservice.opac.interlib.com/\">"
				+ "<soapenv:Header/><soapenv:Body><web:updateReader>"
				+ "<!--Optional:-->"
				+ "<reader>"
				+ "<rdType>999</rdType>"
				+ "<rdid>"+rdid+"</rdid>"
				+"<rdpassword>"+newpwd+"</rdpassword>"
				+ "</reader>"
				+ "<password>"+pwssword+"</password>"
				+ "</web:updateReader></soapenv:Body></soapenv:Envelope>";
		byte[] b = soapRequestData.toString().getBytes("utf-8");;
		InputStream is = new ByteArrayInputStream(b, 0, b.length);
		RequestEntity re = new InputStreamRequestEntity(is, b.length, "text/xml; charset=UTF-8");
		postMethod.setRequestEntity(re);
		HttpClient httpClient = new HttpClient();
		int status = httpClient.executeMethod(postMethod);
		String value="0";
		if (status == 200) {
			String msg=dom4jUtil.getXMLString(postMethod.getResponseBodyAsString());
			if(msg.equals("OK")){
				value="1";
			}
		}
		return value;
	}
	/**
	 * 通过身份证判断读者是否办证
	 * @param idcard
	 * @return true : 是  flase ： 否
	 */
	public static boolean validationUserHandle(String idcard){
		HttpClient client=null;
		PostMethod post=null;
		boolean flag=false;
		try{
			//创建连接
			client=new HttpClient();
			post=new PostMethod("http://10.194.200.30/opac/digitalUnions/vaildateRdCertify");
			//设置编码
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			//添加参数
			post.addParameter("rdcertify",idcard);
			post.addParameter("token",HttpClientUtil.getOpacToken());
			 // 执行请求
			client.executeMethod(post);
			String info=new String(post.getResponseBody(),"UTF-8");
			JSONObject json=JSONObject.parseObject(info);
			//如果返回值长度大于2,证明办理过读者证
			if(json.get("data").toString().length()>2){
				flag=true;
			}
			return flag;
		}catch (Exception e) {
			// TODO: handle exception
			return flag;
		}finally {
			post.releaseConnection();
		}
	}
	/**
	 * 通过身份证判断读者证是否正常
	 * @param idcard
	 * @return true : 是  flase ： 否
	 */
	public static boolean validationUserEffective(String idcard){
		HttpClient client=null;
		PostMethod post=null;
		boolean flag=false;
		try{
			//创建连接
			client=new HttpClient();
			post=new PostMethod("http://10.194.200.30/opac/digitalUnions/vaildateRdCertify");
			//设置编码
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			//添加参数
			post.addParameter("rdcertify",idcard);
			post.addParameter("rdcfstate","1");
			post.addParameter("token",HttpClientUtil.getOpacToken());
			 // 执行请求
			client.executeMethod(post);
			String info=new String(post.getResponseBody(),"UTF-8");
			JSONObject json=JSONObject.parseObject(info);
			//如果返回值长度大于2,证明办理过读者证
			if(json.get("data").toString().length()>2){
				flag=true;
			}
			return flag;
		}catch (Exception e) {
			// TODO: handle exception
			return flag;
		}finally {
			post.releaseConnection();
		}
	}
	/**
	 * 通过身份证判断读者是否过期
	 * @param idcard
	 * @return true : 没过期  flase ： 过期
	 */
	public static boolean validationUserOver(String idcard){
		HttpClient client=null;
		PostMethod post=null;
		boolean flag=false;
		try{
			//创建连接
			client=new HttpClient();
			post=new PostMethod("http://10.194.200.30/opac/digitalUnions/vaildateRdCertify");
			//设置编码
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			//添加参数
			post.addParameter("rdcertify",idcard);
			post.addParameter("rdcfstate","1");
			post.addParameter("findOverdue","false");
			post.addParameter("token",HttpClientUtil.getOpacToken());
			 // 执行请求
			client.executeMethod(post);
			String info=new String(post.getResponseBody(),"UTF-8");
			JSONObject json=JSONObject.parseObject(info);
			//如果返回值长度大于2,证明办理过读者证
			if(json.get("data").toString().length()>2){
				flag=true;
			}
			return flag;
		}catch (Exception e) {
			// TODO: handle exception
			return flag;
		}finally {
			post.releaseConnection();
		}
	}
}
