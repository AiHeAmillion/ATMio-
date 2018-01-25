package com.feicuiedu.atm.admincontrol;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Properties;

import com.feicuiedu.atm.adminbusiness.AdminParent;
import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.userinfo.ReadUserInfo;
import com.feicuiedu.atm.userinfo.User;
import com.feicuiedu.atm.userinfo.WriteUserInfo;

/**
 * 
 * @author 宁强
 * 	1.开户
 *	2.销户
 *	3.显示信息
 *	4.修改信息
 *	5.退出
 * 管理员业务流程控制
 *
 */
public class AdmainControl {
	public void adminFlowControl() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		while (true) {
			
			File file1 = new File("txt"+File.separator+"UserInfo.txt");
			File file = createFile(file1);
			//获取文件里的集合Map
			HashMap<String, User> userInfoMap= ReadUserInfo.read(file);

			//显示完界面并返回用户输入的下一步 并转成字符串
			String input = String.valueOf(showAdminXmb());
			
			//如果管理员输入5直接break，跳出循环
			if ("5".equals(input)) {
				break;
			}
			
			Properties prop = new Properties();

			//读取properties文件
			prop.load(new FileInputStream(new File("txt"+File.separator+"AdminControl.properties")));
			
			//异常抛出了
			Object obj = Class.forName(prop.getProperty(input)).newInstance();
			AdminParent aBusi = (AdminParent) obj;
			aBusi.adminBusi(userInfoMap);
			
			//写入文件
			WriteUserInfo.write(userInfoMap, file);
		}
	}
	
	//管理员主界面
	public int showAdminXmb() {	
		
		while (true) {
		
			//读取用户界面
			Tool.rUi(new File("txt"+File.separator+"AdminXmb.txt"));
			
			//接受 管理员输入判断正确并返回
			System.out.println("输入选择的业务：");
			int input = Integer.valueOf(Tool.input());
			boolean temp = (input==1)||(input==2)||(input==3)||(input==4)||(input==5);

			if (temp) {
				return input;
			}
				System.out.println("输入错误！重新输入");
		}
	}
	
	//检测文件是否存在 不存在创建文件并返回
	public File createFile(File file) {
		
		//是否存在
		if (file.exists()) {
			return file;
		}
		
		//不存在返回
		try {
			file.createNewFile();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return file;
		
	}
}
