package com.compilespace.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.compilespace.utils.EncryptUtil;

public class TestEncrypt {
	private Map<String, Object> map = new HashMap<String, Object>();
	@Test
	public void testEncrypt(){
		String strNum = EncryptUtil.encrypt("本来无一物,何处然尘埃");
		System.out.println("加密后的内核索引下标:"+strNum);
		map = EncryptUtil.map;
		System.out.println("map:"+map);
		//30,49,7,3,7,66,30,49,5,66,64,4
		//3049737663049566644
	}
	@Test
	public void decryption(){
		String string = EncryptUtil.decryption("304956164304956543049544593049345993049615830491643049356443049489330496025304946407304946642");
		System.out.println("解密:"+string);
		//源unicode编码: \u848b\u6bc5
		//还原unicode编码:\u848b\u6bc5
		
	}
}
