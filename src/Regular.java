package common.util;

import java.util.HashMap;
import java.util.Map;

public class Regular
{
	/**
	 * 是否包含数字
	 */
	public static final String STR_Contatin_NUM_="[0-9]";  
	/**
	 * 是否包含字母
	 */
	public static final String STR_COntatin_Letter_="[a-zA-z]";  
	/**
	 * 是否包含特殊字符
	 */
	public static final String STR_COntatin_SpecialChar_="[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";  
    /** 
     * 允许字母、数字、下划线
     */
    public static final String STR_ENG_NUM_="^[A-Za-z0-9_]+$";  
    /** 
     * Email正则表达式
     */
    public static final String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+";
    /** 
     * 电话号码正则表达式  
     */
    public static final String PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)" ;  
    /** 
     * 手机号码正则表达式
     */
    public static final String MOBILE ="^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";  
	/**
	 * 数值正则表达式
	 */
	public static final String Number = "^(\\-|\\+)?\\d+(\\.\\d+)?$";
	/**
	 * 正数正则表达式
	 */
	public static final String NumberPlus = "^[+]{0,1}(\\d+)$|^[+]{0,1}(\\d+\\.\\d+)$";
    /** 
     *整数正则表达式
     */
    public static final String  INTEGER = "^-?[1-9]\\d*$"; 
    /**
     * 负数NumberMinus
     */
    public static final String  NumberMinus = "^-[1-9]\\d*|0$"; 
    /**
     * 正整数的正则表达式IntegerPlus
     */
    public static final String  IntegerPlus = "^[0-9]*[1-9][0-9]*$"; 
    /**
     * 负整数正则表达式IntegerMinus
     */
    public static final String  IntegerMinus = "^((-\\d+)|(0+))$";  
    /** 
     * 正Double正则表达式 >=0 
     */
    public static final String  DOUBLE_NEGATIVE ="^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";  
    /*** 
     * 日期正则 支持： 
     *  YYYY-MM-DD  
     *  YYYY/MM/DD  
     *  YYYY_MM_DD  
     *  YYYYMMDD 
     *  YYYY.MM.DD的形式 
     */
    public static final String DATE_ALL="((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)" +  
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)" +  
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)" +  
            "([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)" +  
            "|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)" +  
            "(0?2)([-\\/\\._]?)(29)$)" +  
            "|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)" +  
            "([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|" +  
            "(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))"; 
    /**
     * 正则表达式 支持
     * yyyy-MM
     */
    public static final String YEAR_MONTH="(1[9][0-9][0-9]|2[0][0-9][0-9])[-](0[1-9]|0[1-9]|1[0-2])";
    /*** 
     * 日期正则 支持： 
     *  YYYY-MM-DD  
     */
    public static final String DATE_FORMAT1="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
	/**
	 * 中文正则表达式
	 */
	public static final String CHINESE = "^[\u4e00-\u9fa5]+$";
    /** 
     * 身份证正则表达式 
     */
    public static final String IDCARD="^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
    /**
     * 台湾身份首字母对应数字 
     */
    public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
    static{
    	twFirstCode.put("A", 10);
		twFirstCode.put("B", 11);
		twFirstCode.put("C", 12);
		twFirstCode.put("D", 13);
		twFirstCode.put("E", 14);
		twFirstCode.put("F", 15);
		twFirstCode.put("G", 16);
		twFirstCode.put("H", 17);
		twFirstCode.put("J", 18);
		twFirstCode.put("K", 19);
		twFirstCode.put("L", 20);
		twFirstCode.put("M", 21);
		twFirstCode.put("N", 22);
		twFirstCode.put("P", 23);
		twFirstCode.put("Q", 24);
		twFirstCode.put("R", 25);
		twFirstCode.put("S", 26);
		twFirstCode.put("T", 27);
		twFirstCode.put("U", 28);
		twFirstCode.put("V", 29);
		twFirstCode.put("X", 30);
		twFirstCode.put("Y", 31);
		twFirstCode.put("W", 32);
		twFirstCode.put("Z", 33);
		twFirstCode.put("I", 34);
		twFirstCode.put("O", 35);
    }
}
