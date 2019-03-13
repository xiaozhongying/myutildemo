package common.util;
 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
 
/**
 * 验证工具类
 */
public class RegMacher {
    /** 
     * 判断字段是否为空 符合返回ture 
     * @param str 
     * @return boolean 
     */
    public static synchronized boolean StrisNull(String str) {  
        return null == str || str.trim().length() <= 0 ? true : false ;  
    }  
    /** 
     * 判断字段是否为Email 符合返回ture 
     * @param str 
     * @return boolean 
     */
    public static  boolean isEmail(String str) {  
        return Regular(str,Regular.EMAIL);  
    }  
    /** 
     * 判断是否为电话号码 符合返回ture 
     * @param str 
     * @return boolean 
     */
    public static  boolean isPhone(String str) {  
        return Regular(str,Regular.PHONE);  
    }  
    /** 
     * 判断是否为手机号码 符合返回ture 
     * @param str 
     * @return boolean 
     */
    public static  boolean isMobile(String str) {  
        return Regular(str,Regular.MOBILE);  
    }  
    /**  
     * 判断字段是否为数值
     * @param str 
     * @return boolean
     */
    public static  boolean isNumber(String str) {  
    	if(str!=null&&!"".equals(str)){
    		 return Regular(str,Regular.Number);  
    	}
    	return false;
       
    }  
    /**
     * isNumberPlus
     * 判断是否为正数
     */
    public static  boolean isNumberPlus(String str) {  
    	if(str!=null&&!"".equals(str)){
    		 return Regular(str,Regular.NumberPlus); 
    	}
    	return false;
        
    }  
    /** 
     * 判断字段是否为整数
     * @param str 
     * @return boolean 
     */
    public static  boolean isInteger(String str) {  
    	if(!"".equals(str)&& null!=str){
    		return Regular(str,Regular.INTEGER);  
    	}
    	return false; 
        
    }  
    /**
     * isNumberMinus
     * 判断是否为负数
     */
    public static  boolean isNumberMinus(String str) {  
    	if(!"".equals(str)&& null!=str){
    		return Regular(str,Regular.NumberMinus);  
    	}
    	return false; 
        
    }  
    /**
     * 正整数IntegerPlus
     */
    public static  boolean isIntegerPlus(String str) {  
    	if(!"".equals(str)&& null!=str){
    		return Regular(str,Regular.IntegerPlus);  
    	}
    	return false; 
        
    }
    /**
     * 负整数IntegerMinus
     */
    public static  boolean isIntegerMinus(String str) {  
    	if(!"".equals(str)&& null!=str){
    		return Regular(str,Regular.IntegerMinus);  
    	}
    	return false; 
        
    }
    /**  
     * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture 
     * @param str 
     * @return boolean 
     */
    public static  boolean isDOUBLE_NEGATIVE(String str) {  
        return Regular(str,Regular.DOUBLE_NEGATIVE);  
    }  
    /** 
     * 判断字段是否为日期 符合返回ture 
     * @param str 
     * @return boolean 
     */
    public static  boolean isDate(String str) {

    	return Regular(str,Regular.DATE_ALL);
    }  
    /** 
     * 判断字段是否为日期 符合返回ture 
     * @param str 
     * @return boolean 
     */
    public static  boolean isDate(String str,String format) {
    	if("yyyy-MM".equals(format)){
    		return Regular(str,Regular.YEAR_MONTH);  
    	}else if("yyyy-MM-dd".equals(format)){
    		return Regular(str,Regular.DATE_FORMAT1); 
    	}
    	return Regular(str,Regular.DATE_ALL);
    }  
    /** 
     * 判断字段是否为空
     * 超过长度{leng}返回false 反之返回ture 
     * @param str 
     * @param leng 
     * @return boolean 
     */
    public static  boolean isNull(String str) {       
    	if("".equals(str.trim())||null==str){
        	return false ;
    	}
    	return true;
    }  
    /**
     * 判断是否为中文
     */
    public static  boolean isChinese(String str) {       
    	if(!"".equals(str)&&null!=str){
    		return Regular(str, Regular.CHINESE);
    	}
    	return true;
    }  
    /** 
     * 判断字段是否为中国大陆身份证 符合返回ture 
     * @param str 
     * @return boolean 
     */
    public static  boolean isIdCard(String idcard) {  
    	
	    	if(!Regular(idcard,Regular.IDCARD)){//如果身份证号18位数校验不通过
	    		return false;
	    	}
    		//"11":"北京","12":"天津","13":"河北","14":"山西","15":"内蒙古",
    		//"21":"辽宁","22":"吉林","23":"黑龙江",
    		//"31":"上海","32":"江苏","33":"浙江","34":"安徽","35":"福建","36":"江西","37":"山东",
    		//"41":"河南","42":"湖北","43":"湖南","44":"广东","45":"广西","46":"海南",
    		//"50":"重庆","51":"四川","52":"贵州","53":"云南","54":"西藏",
    		//"61":"陕西","62":"甘肃","63":"青海","64":"宁夏","65":"新疆",
    		//"71":"台湾",
    		//"81":"香港","82":"澳门",
    		//"91":"国外"
    		//前两位数必须是上面定义的city
    		String v=idcard.substring(0,2);//身份证号前两位判断省份
    		if("11_12_13_14_15_21_22_23_31_32_33_34_35_36_37_41_42_43_44_45_46_50_51_52_53_54_61_62_63_64_65_71_81_82_91".indexOf(v)==-1){
    			return false;
    		}
    		//校验出生日期是否合法
    		String birth=idcard.substring(6,10)+"/"+idcard.substring(10,12)+"/"+idcard.substring(12,14);
    		Date date=null;
    		try
    		{
    			date=new SimpleDateFormat("yyyy/MM/dd").parse(birth);
    			if(!new SimpleDateFormat("yyyy/MM/dd").format(date).equals(birth)){
    				return false;
    			}
    		}
    		catch(ParseException e)
    		{
    			e.printStackTrace();
    		}
    		String last=String.valueOf(doVerify(idcard));
    		System.out.println(last);
    		//如果最后一位身份证号不和归
    		if(!(idcard.substring(idcard.length()-1, idcard.length())).equals(last)){
    			return false;
    		}
    		
    	 return true;
    }  
    /**
	 * 验证台湾身份证号码
	 */
	public static boolean isTWCard(String idCard) {
		String start = idCard.substring(0, 1);
		String mid = idCard.substring(1, 9);
		String end = idCard.substring(9, 10);
		Integer iStart = Regular.twFirstCode.get(start);
		Integer sum = iStart / 10 + (iStart % 10) * 9;
		char[] chars = mid.toCharArray();
		Integer iflag = 8;
		for (char c : chars) {
			sum = sum + Integer.valueOf(c + "") * iflag;
			iflag--;
		}
		return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
	}
    /** 
     * 判断字符串是不是全部是英文字母+数字+下划线 
     * @param str 
     * @return boolean 
     */
    public static boolean isENG_NUM_(String str) {  
        return Regular(str,Regular.STR_ENG_NUM_) ;  
    }  
     
    /** 
     * 匹配是否符合正则表达式pattern 匹配返回true 
     * @param str 匹配的字符串 
     * @param pattern 匹配模式 
     * @return boolean 
     */
    private static  boolean Regular(String str,String pattern){  
        if(null == str || str.trim().length()<=0)  
            return false;           
        Pattern p = Pattern.compile(pattern);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
	/**
	 * 校验身份证号码最后一位
	 * @param id
	 * @return
	 */
	public static char doVerify(String idcard) {
		char pszSrc[] = idcard.toCharArray();
		int iS = 0;
		int iW[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		char szVerCode[] = new char[] { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
		int i;
		for (i = 0; i < 17; i++) {
			iS += (int) (pszSrc[i] - '0') * iW[i];
		}
		int iY = iS % 11;
		return szVerCode[iY]==10?'X':szVerCode[iY];
	} 
    /**
     * 校验组织机构代码
     * @return
     */
    public static boolean orgCode(String v){
    	String CC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";// 下标位值0-35
		int[] WW = {3,  7,  9, 10, 5, 8,  4,  2};// 加权因子对应1-8位数值
		// 计算方式
		// 由1-8位字符对应的位置，从CC取出其位于的下标值[C]，如0位于0，A位于10，Y位于34
		// 再分别与WW中加权因子[W]进行相乘
		// SUM8 = C1*W1 + C2*W2 + ... + C8*W8
		// 将8个乘积的和计算整数，用11求余即，[X] = MOD(SUM8, 11)
		// 第9位的值，即校验码的值为：C9 = 11 - X，如果C9为10，则换为X表示，其余则用原数值代替
		int sum=0;
		for(int i = 0; i < 8; i++)
		{
			sum+=(CC.indexOf(v.charAt(i)))*WW[i];
			v.charAt(i);
		}
        String C9=String.valueOf(11-sum%11);
		if(C9.equals("11"))
		{
			C9 = "0";
		}
		if(C9.equals("10"))
		{
			C9 ="X";
		}
		if(C9.equals(String.valueOf(v.charAt(8))))
		{
			return true;
		}
		return false;
		
    }
    /**
     * 校验统一社会信用编码
     * @param args
     */
    public static boolean UnitCode(String code){
        //数字及大写字母[不含IOSVZ]
        String  basecode="0123456789ABCDEFGHJKLMNPQRTUWXY";
        char [] codeArr=code.toCharArray();
    	if(code.length()!=18){
    		return false;
    	}
        //校验统一社会编码是否符合规定
        for(int i = 0; i < codeArr.length; i++)
		{
        	//如果数字不在这些数字内
			if(basecode.indexOf(codeArr[i])==-1){
				return false;
			}
		}
        //校验组织机构代码
        if(!orgCode(code.substring(8, 17))){
        	return false;//不符合组织机构代码
        }
		String CC = "0123456789ABCDEFGHJKLMNPQRTUWXY0";// 下标位值0-30，当余数为0时，用来取第31（即第0位）
		int WW[] = {1,  3,  9, 27, 19, 26, 16, 17, 20, 29, 25, 13,  8, 24, 10, 30, 28};// 加权因子对应1-17位数值
		int sum=0;
		for(int i = 0; i < 17; i++)
		{
			sum += (CC.indexOf(code.charAt(i))) * WW[i];
		}
		if(CC.charAt(31 - sum%31)==code.charAt(17))// 当余数为0时，31-0=31，原本应该取0值的，但CC增加了第31位用来代替0，故这里不需要再处理
		{
			return true;
		}
    	return false;
    }
    /**
     * 是否包含数字
     * @param company
     * @return
     */
    public static boolean isContainNumber(String str) {
    	
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
    /**
     * 是否包含字母
     * @param value
     * @return
     */
	 public static boolean isContainLetter(String str){
		 Pattern p = Pattern.compile("[a-zA-z]");
	        if(p.matcher(str).find())
	        {
	            return true;
	        }else{
	            return false;
	        }
	 }
	 /**
	     * 判断是否含有特殊字符
	     *
	     * @param str
	     * @return true为包含，false为不包含
	     */
	    public static boolean isSpecialChar(String str) {
	        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
	        Pattern p = Pattern.compile(regEx);
	        Matcher m = p.matcher(str);
	        return m.find();
	    }
     
}  
