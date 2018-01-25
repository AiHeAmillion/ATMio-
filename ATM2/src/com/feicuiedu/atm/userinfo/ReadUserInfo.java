package com.feicuiedu.atm.userinfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;

// 读取userInfo 文件
public class ReadUserInfo {
	
	/**
	 * 读取文件可以共用，可以用static
	 * @param file 需要读取的文件
	 * @return 返回读取的HashMap
	 */
	public static HashMap<String, User> read(File file) {	//读取文件 输入流 从文件到程序
		
		try {
			FileInputStream inputStream = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(inputStream);
			
			//序列化读出文件的Map赋值到新的Map
			HashMap<String, User> userInfoMap = (HashMap<String, User> ) ois.readObject();
			
			//关闭流
			ois.close();
			
			//返回这个新的Map
			return userInfoMap;
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
