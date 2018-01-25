package com.feicuiedu.atm.usercontrol;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.userbusiness.UserParent;
import com.feicuiedu.atm.userinfo.ReadUserInfo;
import com.feicuiedu.atm.userinfo.User;
import com.feicuiedu.atm.userinfo.WriteUserInfo;

/**
 * @author 宁强
 * 	1.查询
 *	2.转账
 *	3.存款
 *	4.取款
 *	5.流水
 *	6.退出
 * 用户业务流程控制反射
 */
public class UserControl {
	
	public void userFlowControl(String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		while (true) {
			
			File file = new File("txt"+File.separator+"UserInfo.txt");
			
			//获取文件里的集合Map
			HashMap<String, User> userInfoMap= ReadUserInfo.read(file);
			
			//显示完界面并返回用户输入的下一步 并转成字符串
			String input = String.valueOf(showUserXmb());
			
			//如果用户输入6直接break，跳出循环
			if ("6".equals(input)) {
				break;
			}
			
			Properties prop = new Properties();
			
			//读取properties文件	
			prop.load(new FileInputStream(new File("txt"+File.separator+"UserControl.properties")));
			
			//异常抛出了
			Object obj = Class.forName(prop.getProperty(input)).newInstance();
			UserParent pBusi = (UserParent) obj;
			pBusi.userBusi(userInfoMap, key);
			
			//写入文件
			WriteUserInfo.write(userInfoMap, file);
		}
	}
	
	//用户主界面
	public int showUserXmb() {
		
		while (true) {

			//读取用户界面
			Tool.rUi(new File("txt"+File.separator+"UserXmb.txt"));
			
			//接受 用户输入判断正确并返回
			System.out.println("输入选择的业务：");
			int input = Integer.valueOf(Tool.input());
			boolean temp = (input==1)||(input==2)||(input==3)||(input==4)||(input==5)||(input==6);
				
			if (temp) {
				return input;
			}
				System.out.println("输入错误！重新输入");
			
		}
	}
	
}
