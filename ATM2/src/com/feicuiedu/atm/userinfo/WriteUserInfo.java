package com.feicuiedu.atm.userinfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class WriteUserInfo {		//从程序到文件 输出流 
	
	/**
	 * 写入文件共用的，用了static
	 * @param userInfoMap 需要保存的HashMap 
	 * @param file	保存的文件
	 */
	public static void write(HashMap<String, User> userInfoMap,File file) {
		
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(outputStream);
			
			// 序列化对象，写入输出流 从程序到文件
			oos.writeObject(userInfoMap);
			
			//刷新流
			//关闭流
			oos.flush();
			oos.close();
		} catch ( IOException e) {
			e.printStackTrace();
		}
	}
}
