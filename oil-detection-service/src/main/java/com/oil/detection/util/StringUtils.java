/**
* Copyright(c) 2002-2013, 360buy.com  All Rights Reserved
*/

package com.oil.detection.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang.StringUtils{
	
	private static final Logger logger = LoggerFactory.getLogger(StringUtils.class); 
	
	private static final String patternByMaps = "&";
	private static final String patternByKeyValues = "=";
	
	/**
	 * 将map中key,value值转换为字符串，Map.Entry之间默认分隔符<b> ${patternByMaps} </b>联接,key,value之间默认分隔符<b> ${patternByKeyValues} </b>联接.
	 * @param params
	 * @param patternByMaps
	 * @param patternByKeyValues
	 * @return
	 * @author wangx
	 * @date 2013-7-12
	 */
	public static String map2String(Map<String, ? extends Object> params, String patternByMaps, String patternByKeyValues, boolean valueHasQuotationMarks){
		int count = 0; 
		StringBuilder builder = new StringBuilder();
		for(Entry<String, ? extends Object> entry : params.entrySet()){
			if(count > 0){
				builder.append(patternByMaps);
			}
			builder.append(entry.getKey());
			builder.append(patternByKeyValues);
			if(valueHasQuotationMarks){
				builder.append("\""+entry.getValue()+"\"");
			}else{
				builder.append(entry.getValue());
			}
			count++;
		}
		return builder.toString();
	}
	
	/**
	 * 将map中key,value值转换为字符串，Map.Entry之间默认分隔符<b> & </b>联接,key,value之间默认分隔符  <b> = </b>联接.
	 * @param params	参数
	 * @return			字符串
	 * @author wangx
	 * @date 2013-7-12
	 */
	public static String map2String(Map<String, ? extends Object> params){
		return map2String(params, patternByMaps, patternByKeyValues, false);
	}
	
	public static String collect2String(Collection<String> params){
		if(null == params || params.isEmpty()){
			return "";
		}
		StringBuilder builder = new StringBuilder();
		
		Iterator<String> iterator = params.iterator();
		int count = 0;
		while(iterator.hasNext()){
			if(count == 0){
				builder.append("; ");
			}
			builder.append(iterator.next());
			count++;
		}
		return builder.toString();
	}
	
	public static String replace(String str){
		if(isBlank(str)){
			return str;
		}
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			if(c == '\r'){
				builder.append("\\r");
			}else if(c == '\n'){
				builder.append("\\n");
			}else if(c == '\t'){
				builder.append("\\t");
			}else if(c == '\b'){
				builder.append("\\b");
			}else if(c == '\f'){
				builder.append("\\f");
			}else if(c == '"'){
				builder.append("\\\"");
			}else if(c == '\\'){
				builder.append("\\\\");
			}else{
				builder.append(c);
			}
		}
		return builder.toString();
	}
	
	
	/**
	 * 计算字符串长度，区别中英文，中文一个字符占2个长度，英文一个字符占1个长度
	 * @param str		待计算字符串
	 * @return			字符串长度
	 * @author wangx
	 * @date 2013-9-25
	 */
	public static int lengthZH_cn(String str){
		int count = 0;
		if(isEmpty(str)){
			return count;
		}
		int length = str.length();
		for(int i = 0; i < length; i++){
			char c = str.charAt(i);
			if(c >= '\u0391' && c <= '\uFFE5' ){
				count += 2;
			}else{
				count += 1;
			}
		}
		return count;
	}
	
	/**
	 * 截取最大长度的字符串，中文一个字符占2个长度，英文一个字符占1个长度
	 * @param str		待计算字符串
	 * @param maxLength	最大长度
	 * @return			字符串
	 * @author wangx
	 * @date 2013-9-25
	 */
	public static String subStringZH_cn(String str, int maxLength){
		int count = 0;
		int endIndex = 1;
		
		if(isEmpty(str)){
			return str;
		}
		int length = str.length();
		for(int i = 0; i < length; i++){
			char c = str.charAt(i);
			if(c >= '\u0391' && c <= '\uFFE5'){
				count += 2;
			}else{
				count += 1;
			}
			
			if(count == maxLength){
				endIndex = i+1;
				break;
			}else if(count > maxLength){
				endIndex = i;
				break;
			}else{
				endIndex = i+1;
			}
		}
		return str.substring(0, endIndex);
	}
	
	/**手机正则规则*/
	private static final String mobile = "1[0-9]{10}";
	/**
	 * 处理手机号码<br/>
	 * 	如果是1开头的11位数字，即认为是手机号码，将返回<b>前三位 + ** + 后四位</b><br/>
	 * 	否则原样返回。<br/>
	 *  eg. 13892323232 >> 138**3232
	 * @param str		输入参数
	 * @return
	 * @author wangx
	 * @date 2013-10-8
	 */
	public static String subStringByMobile(String str){
		if(isEmpty(str)){
			return str;
		}
		
		if(Pattern.matches(mobile, str)){
			return str.substring(0, 3) + "**" + str.substring(7, 11);
		}
		return str;
	}
	
	/**
	 * 
	 * 图片url地址实际为：http://image.jd.com/{X*Y}/jfs/t1/124/1073598/139632/682a9296f3ffb25dN.jpg
	 * 其中{X*Y}为动态长度和宽度的值;
	 * 替换后的值：http://image.jd.com/200x300/jfs/t1/124/1073598/139632/682a9296f3ffb25dN.jpg
	 * @param url			原始url
	 * @param width			宽度
	 * @param height		高度
	 * @return				替换后的url
	 * @author wangx
	 * @date 2014-3-17
	 */
	public static String replaceImgUrl(String url, int width, int height){
		if(isEmpty(url)){
			return url;
		}
		return url.replace("{X*Y}",  width + "x" + height);
	}
	
	public static String encode(String value){
		if(StringUtils.isNotEmpty(value) && !value.contains("%")){
			try {
				return URLEncoder.encode(value, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e.getCause());
			}
		}
		return value;
	}
	
	public static String decode(String value){
		if(StringUtils.isNotEmpty(value)){
			try {
				return URLDecoder.decode(value, "utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error(e.getMessage(), e.getCause());
			}
		}
		return value;
	}
	
	/**
	 * 解析属性 ：属性值
	 * @param attribute
	 * @return
	 */
	public static List<String> getAttributeName(String attribute) {
		List<String> attList = new ArrayList<String>();
		if (attribute == null || attribute.equals("")) {
			return attList;
		}
		String[] attributes = attribute.split(";");
		if (attributes == null || attributes.length == 0) {
			return attList;
		}
		String attributeName = "";
		for (int i = 0; i < attributes.length; i++) {
			String temp = attributes[i];
			String[] ids = temp.split(":");
			if (ids == null || ids.length != 4) {
				continue;
			}
			attributeName = intercepStr(temp, 2, ":");
			attList.add(attributeName);
		}
		return attList;
	}
	
	
	
	
	public static void main(String[] args) {
//		String string = "{" +
//		    "appversion = \"1.0\";" +
//		    "body = \"Adds\nDVDs\n\";" +
//		    "channel = debug;" +
//		    "model = iPhone;" +
//		    "platform = iPhone;" +
//		    "systemname = \"iPhone OS\";" +
//		    "systemversion = \"6.1.2\";" +
//		"}";
//		
//		System.out.println(string);
//		System.out.println(replace(string));
		
		System.out.println(replaceImgUrl("123{X*Y}456", 200, 399));
		
	}
	/**
	* 截取字符串。按某字串截取，且截取指定次数
	*
	* @param target 需要被截取的字符串
	* @param intercepNo 截取次数
	* @param intercepStr 被参照的子串
	* @return
	*/
	public static String intercepStr(String target, int intercepNo,
			String intercepStr) {
		int len = intercepStr.length();
		for (int i = 0; i < intercepNo; i++) {
			int index = target.indexOf(intercepStr);
			if (index != -1) {
				target = target.substring(index + len);
			} else {
				target = null;
				break;
			}
		}
		return target;
	}
	
}

