package com.compilespace.utils;

import java.util.HashMap;
import java.util.Map;

public class EncryptUtil {
	public static  Map<String, Object> map = new HashMap<>();	
	
	/**
	 * 解密方法decryption
	 * @return
	 */
	public static String decryption(String strDecryption){
		//静态内核
		String stringCore = "1234567890!@#$%^&*()-=_+[]{};'\\:\"\\|,./<>?`~qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		String stringIndex = (String)map.get(strDecryption);//获取分离前的索引30,49,7,3,7,66,30,49,5,66,64,4
		if(stringIndex!=null){
			String[] strIndex = stringIndex.split(",");//分离后的索引数组
		
			char[] charArrayCore = stringCore.toCharArray();
			StringBuffer sb = new StringBuffer();		
			for(int i=0;i<strIndex.length;i++){
				int srcIndex = Integer.parseInt(strIndex[i]);
				System.out.println("源的对应下标:"+strIndex.toString());
				char charUnicode = charArrayCore[srcIndex];
				sb.append(charUnicode);//拼接unicode编码
			}
			String stringUnicode = sb.toString();
			System.out.println("还原unicode编码:"+stringUnicode);
			String string = unicode2String(stringUnicode);//将unicode编码转成字符串
			return string;
		}
		
		return "";
	}
	/**
	 * 加密方法encrypt
	 * @param str
	 * @return
	 */
	public static String encrypt(String strEncrypt) {		
		String unicode = string2Unicode(strEncrypt);//将源字符串转成unicode编码 
		System.out.println("源unicode编码:"+unicode);
		StringBuffer sb = new StringBuffer();//拼接对比下标
		StringBuffer sb2 = new StringBuffer();//拼接分离后的下标
		
		StringBuffer sbUnicode = new StringBuffer();
		//静态内核
		sbUnicode.append(
				"1234567890!@#$%^&*()-=_+[]{};'\\:\"\\|,./<>?`~qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM");

		String stringCore = sbUnicode.toString();
		System.out.println("静态内核:" + stringCore);
		// 核心字符数组
		char[] charArray = stringCore.toCharArray();

		// 源字符数组
		char[] charArraySrc = unicode.toCharArray();
		System.out.println("核心字符数组:" + charArray.length);
		System.out.println("源字符数组:" + charArraySrc.length);
		
		// 把源字符数组与核心字符数组进行对比,拿到与核心字符数组对比之后的下标
		for (int i = 0; i < charArraySrc.length; i++) {
			for (int j = 0; j < charArray.length; j++) {
				if (unicode.charAt(i) == charArray[j]) {
					if (i == unicode.length() - 1) {
						sb.append(j);
					} else {
						sb.append(j + ",");//拼接对比的下标,用逗号分开
					}
					break;
				} else {

				}
			}
		}

		String stringIndex = sb.toString();// 分离前的内核索引
		String[] splitArr = stringIndex.split(",");// 用逗号分离下标字符串

		System.out.println("分离前的内核索引:" + stringIndex);
		for (int i = 0; i < splitArr.length; i++) {
			sb2.append(splitArr[i]);
		}
		String strIndex = sb2.toString();// 分离后的下标字符串
		System.out.println("分离后的下标字符串:" + strIndex);
		// 分离前的内核索引   stringIndex
		// 分离后的下标字符串  strIndex
		map.put(strIndex,stringIndex);
		// return unicode;//返回目标unicode编码
		return strIndex;//返回内核索引
	}


	/**
	 * 转字符串unicode
	 */
	public static String string2Unicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			unicode.append("\\u" + Integer.toHexString(c));
		}
		return unicode.toString();
	}

	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {
		StringBuffer string = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			int data = Integer.parseInt(hex[i], 16);
			string.append((char) data);
		}
		return string.toString();
	}

}
