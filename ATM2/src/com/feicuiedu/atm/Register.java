package com.feicuiedu.atm;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.feicuiedu.atm.admincontrol.AdmainControl;
import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.tool.VerifyAccount;
import com.feicuiedu.atm.usercontrol.UserControl;
import com.feicuiedu.atm.userinfo.ReadUserInfo;
import com.feicuiedu.atm.userinfo.User;

//登录
public class Register {
	public void login() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		
		AdmainControl admainControl = new AdmainControl();
		UserControl userControl = new UserControl();
		
		do {
			
			//读取文本
			Tool.rUi(new File("txt"+File.separator+"Login.txt"));
			
			//接受用户输入选项
			String input = Tool.input();
			
			if ("1".equals(input)) {
				
				//管理员登录账号密码验证
				boolean bln = adminLin();
				
				//调用管理员登录 返回bln=false 则账号密码错误
				if (bln) {
					
					//密码正确 调用管理员流程控制
					admainControl.adminFlowControl();
				}
				System.out.println("信息错误，重新登录！");
				
			}else if("2".equals(input)){
				
				//用户登录验证账号密码
				String str = userLin();
				
				//调用用户登录 返回key="false" 则账号密码错误
				if (!"false".equals(str)) {
					
					//密码正确 调用用户流程控制
					userControl.userFlowControl(str);
				}
				
			}else {
				System.out.println("输入错误，重新输入！");
			}
			
		} while (true);
	}
	
	/**
	 * 验证用户输入密码账号正确
	 * @return	错误返回“false”，正确返回该账号对应用户的键
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String userLin() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		
		File file = new File("txt"+File.separator+"UserInfo.txt");
		
		//用户信息文件肯定已经存在可以直接读取
		HashMap<String, User> userInfoMap = ReadUserInfo.read(file);
		
		String key  = "false";
					
		//输入账号/身份证号
		System.out.println("账户/身份证号：");
		String number = Tool.input();
			
		//输入密码
		System.out.println("密码：");
		String strPassword = Tool.input();
			
		//调用验证输入号格式
		boolean temp = VerifyAccount.numberLengeth(number);
			
		//账号格式正确 验证存在
		key = VerifyAccount.numberExist(userInfoMap, number);
			
		//验证密码
		boolean bln = VerifyAccount.passwordExist(userInfoMap, key, strPassword);
			
		if (!temp) {
				
			//不正确返回key="false"
			System.out.println("账号输入错误!");
			return key;
		}else if ("false".equals(key)) {
				
			//不正确返回key="false"
			System.out.println("账号不存在！");
			return key;
		}else if (!bln) {
				
			//不正确返回key="false"
			System.out.println("密码不正确！");
			return key="false";
		}
			
			//三项都正确返回用户的键 temp && (!"false".equals(key)) && bln
			return key;	
	}
	
	/**
	 * @return 返回true管理员输入账号密码正确，false不正确
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public boolean adminLin() throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		while (true) {
			
			System.out.println("管理员账户：");
			String str = Tool.input();
			System.out.println("账户密码：");
			String str1 = Tool.input();
			System.out.println("账户姓名：");
			String str2 = Tool.input();
			
			//验证三条信息是否正确
			if (str.equals("370120180104")&&str1.equals("123456")&&str2.equals("翡翠侠")) {
				
				//信息正确 进入管理员业务的流程控制
				return true;
				
			}
			
			return false;
		}
	
	}
}
