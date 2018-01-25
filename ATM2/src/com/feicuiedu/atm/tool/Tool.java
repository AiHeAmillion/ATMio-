package com.feicuiedu.atm.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import com.feicuiedu.atm.userinfo.User;

/**
 * @author 宁强
 * 工具类，里有公用方法
 */
public class Tool {	
	/**
	 * 静态方法接受用户输入
	 * @return 返回用户输入的
	 */
	public static String input () {
		Scanner scanner = new Scanner(System.in);
		return scanner.nextLine();
	}
	
	/**
	 * 获取时间日期转换字符串
	 * @return 返回当前时间的字符串形式
	 */
	public static String strDate() {
		
		//时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");	
		
		//获取当前时间按格式输出并返回
		return sdf.format(new Date());	
	}

	/**
	 * 查看一个用户的信息
	 * @param userInfoMap 用户信息集合
	 * @param key	需要查看的用户的键
	 */
	public static void look(HashMap<String, User> userInfoMap,String key) {
		
		System.out.println("1.姓名："+userInfoMap.get(key).getName());
		System.out.println("2.密码："+userInfoMap.get(key).getPassword());
		System.out.println("3.学历："+userInfoMap.get(key).getEducation());
		System.out.println("4.联系地址："+userInfoMap.get(key).getAddress());
		System.out.println("5.账号："+userInfoMap.get(key).getAccount());
		System.out.println("6.密码："+userInfoMap.get(key).getPassword());
		System.out.println("7.余额："+userInfoMap.get(key).getBalance());
		System.out.println("8.身份证号："+userInfoMap.get(key).getIdNo());
	}
	
	/**
	 * 字节流读取UI文件可以用static
	 * @param file
	 * @return 返回读取出的UI界面
	 */
	public static void rUi(File file) {
		//输入流 文件到程序 
		try {
			
			FileInputStream fis = new FileInputStream(file);
			byte[] b = new byte[1024];
			int count = 0;
			while((count = fis.read(b)) != -1) {
				String str = new String(b,0,count);
				System.out.println(str);
			}
			
			//关闭流
			fis.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
