package common.util;


import org.omg.CORBA.StringHolder;




public class datatypeUtil
{
	/**
	 * 
	 * @param type
	 * @param str
	 * @return
	 */
	public static boolean validate(String type,String str) {
		switch(type)
		{
			case "Require":;return RegMacher.isNull(str);//必填
			
			case "Chinese":return RegMacher.isChinese(str);//中文
			
			case "WebDate":return RegMacher.isDate(str);//日期
			
			case "Char": return RegMacher.isENG_NUM_(str);//英、数、下划线(必填) 
			
			case "IdCard": return RegMacher.isIdCard(str);//身份证号码
			
			case "TwCard": return RegMacher.isTWCard(str);//台湾身份证号码
			
			case "Mobile":return RegMacher.isMobile(str);//手机号码
			
			case "Money": return RegMacher.isDOUBLE_NEGATIVE(str);//金额"
			
			case "Email": return RegMacher.isEmail(str);//邮件格式(必填) 
			
			case "Number": return RegMacher.isNumber(str);//数值
			
			case "NumberPlus": return RegMacher.isNumberPlus(str);//正数
			
			case "NumberMinus": return RegMacher.isNumberMinus(str);//负数
			
			case "UnitCode":return RegMacher.UnitCode(str);//统一社会信用代码
			
			case "Integer": return RegMacher.isInteger(str);//整数
			
			case "IntegerPlus": return RegMacher.isIntegerPlus(str);//正整数
			
			case "IntegerMinus": return RegMacher.isIntegerMinus(str);//负整数
			
			case "isContainNumber": return RegMacher.isContainNumber(str);//包含数字
			
			case "isContainLetter": return RegMacher.isContainLetter(str);//包含字母
			
			case "isSpecialChar": return RegMacher.isSpecialChar(str);//包含特殊字符

			
			default:return false;
		}
	}
	/**
	 * 
	 * @param type
	 * @param str 
	 * @param holder 返回信息
	 * @return
	 */
	public static boolean validate(String type,String str,StringHolder holder) {
		switch(type)
		{
			case "Require":holder.value="必填";return RegMacher.isNull(str);////测试成功
			
			case "Chinese":holder.value="中文";return RegMacher.isChinese(str);//测试成功
			
			case "WebDate":holder.value="日期";return RegMacher.isDate(str);//日期(必填) 
			
			case "Char":holder.value="英、数、下划线"; return RegMacher.isENG_NUM_(str);//英、数、下划线(必填) 
			
			case "IdCard":holder.value="身份证号码"; return RegMacher.isIdCard(str);//测试成功
			
			case "TwCard":holder.value="台湾身份证号码"; return RegMacher.isTWCard(str);//测试成功
			
			case "Mobile":holder.value="手机号码"; return RegMacher.isMobile(str);//测试成功
			
			case "Money":holder.value="金额"; return RegMacher.isDOUBLE_NEGATIVE(str);//测试成功
			
			case "Email":holder.value="邮件格式"; return RegMacher.isEmail(str);//邮件格式(必填) 
			
			case "Number":holder.value="数值"; return RegMacher.isNumber(str);//测试成功
			
			case "NumberPlus":holder.value="正数"; return RegMacher.isNumberPlus(str);//测试成功
			
			case "NumberMinus":holder.value="负数"; return RegMacher.isNumberMinus(str);//测试成功
			
			case "UnitCode":holder.value="纯统一社会信用代码"; return RegMacher.UnitCode(str);//测试成功
			
			case "Integer":holder.value="整数"; return RegMacher.isInteger(str);//测试成功
			
			case "IntegerPlus":holder.value="正整数"; return RegMacher.isIntegerPlus(str);//测试成功
			
			case "IntegerMinus":holder.value="负整数"; return RegMacher.isIntegerMinus(str);//测试成功
			
			case "isContainNumber":holder.value="包含数字"; return RegMacher.isContainNumber(str);//测试成功
			
			case "isContainLetter":holder.value="包含字母"; return RegMacher.isContainLetter(str);//测试成功
			
			case "isSpecialChar":holder.value="包含特殊字符"; return RegMacher.isSpecialChar(str);//测试成功

			
			default:return false;
		}
	}
	/**
	 * 自定义日期格式
	 * @param type
	 * @param str
	 * @param holder
	 * @param format（yyyy-MM 或yyyy-MM-dd）
	 * @return
	 */
	public static boolean validate(String type,String str,StringHolder holder,String format) {
		switch(type)
		{
			case "Require":holder.value="必填";return RegMacher.isNull(str);//字符串(必填)  
			
			case "Chinese":holder.value="中文";return RegMacher.isChinese(str);//中文(必填) 
			
			case "WebDate":holder.value="日期(例如2018年1月格式为：2018-01)";return RegMacher.isDate(str,format);//日期(必填) 
			
			case "Char":holder.value="英、数、下划线"; return RegMacher.isENG_NUM_(str);//英、数、下划线(必填) 
			
			case "IdCard":holder.value="身份证号码"; return RegMacher.isIdCard(str);//测试成功
			
			case "Mobile":holder.value="手机号码"; return RegMacher.isMobile(str);//手机号码(必填) 
			
			case "Money":holder.value="金额"; return RegMacher.isDOUBLE_NEGATIVE(str);//金额(必填) 
			
			case "Email":holder.value="邮件格式"; return RegMacher.isEmail(str);//邮件格式(必填) 
			
			case "UnitCode":holder.value="纯统一社会信用代码"; return RegMacher.UnitCode(str);//测试成功
			
			case "Number":holder.value="数值"; return RegMacher.isNumber(str);//测试成功
			
			case "NumberPlus":holder.value="正数"; return RegMacher.isNumberPlus(str);//测试成功
			
			case "NumberMinus":holder.value="负数"; return RegMacher.isNumberMinus(str);//测试成功
			
			case "Integer":holder.value="整数"; return RegMacher.isInteger(str);//测试成功
			
			case "IntegerPlus":holder.value="正整数"; return RegMacher .isIntegerPlus(str);//测试成功
			
			case "IntegerMinus":holder.value="负整数"; return RegMacher.isIntegerMinus(str);//测试成功
			
			default:return false;
		}
	}

	
}
