package com.feicuiedu.atm.adminbusiness;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Properties;

import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.tool.VerifyAccount;
import com.feicuiedu.atm.userinfo.User;

//修改信息
public class ChangeAccount extends AdminParent {
	
	@Override
	public void adminBusi(HashMap<String, User> userInfoMap) throws FileNotFoundException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{
		
		aa:
		while (true) {
		
			System.out.println("*****************修改信息***************");
			System.out.println("输入修改的账号：");
			String input = Tool.input();
			
			//长度格式验证 账号错误 返回0  正确返回1
			boolean temp = VerifyAccount.numberLengeth(input);
			
			//账号存在验证
			if (temp) {
				
				//账号存在 返回该要修改的对象的键 不存在返回 key="false"
				String key = VerifyAccount.numberExist(userInfoMap, input);
				if ("false".equals(key)) {
					
					System.out.println("账号不存在");	
					
					//账号不存在 重新输入
					continue aa;
							
				}else {
						
					Properties prop = new Properties();
					
					while (true) {
						
						//显示要修改用户信息
						Tool.look(userInfoMap, key);
						User user = userInfoMap.get(key);
						
						System.out.println("输入修改序号(5/6/7/8为不可修改，9确认修改并返回)：");
						String methodCard = String.valueOf(Tool.input());
						
						if ("9".equals(methodCard)) {
							
							//确认并返回
							break;
						}
						
						//读取properties文件
						prop.load(new FileInputStream(new File("txt"+File.separator+"ChangeAccount.properties")));
						
						Class clsUser = Class.forName("com.feicuiedu.atm.userinfo.User");
						Method method = clsUser.getMethod(prop.getProperty(methodCard), String.class);
							
						System.out.println("输入新信息：");
						String input2 = Tool.input();

						//执行方法
						method.invoke(user, input2);						
					}

				}
			}else {
				System.out.println("账号输入错误");
			}
			
			break;
		}
	}
		
}
