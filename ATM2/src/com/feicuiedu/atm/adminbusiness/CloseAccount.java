package com.feicuiedu.atm.adminbusiness;

import java.io.File;
import java.util.HashMap;

import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.tool.VerifyAccount;
import com.feicuiedu.atm.userinfo.User;

//销户
public class CloseAccount extends AdminParent {
	
	@Override
	public void adminBusi(HashMap<String, User> userInfoMap) {
		
		aa:
		while (true) {

			System.out.println("*****************销户*******************");
			System.out.println("输入账号：");
			String strAccount = Tool.input();
			System.out.println("输入身份证号：");
			String strIdNo = Tool.input();
			
			//调用验证账号及身份证号
			String temp1 = VerifyAccount.numberExist(userInfoMap, strAccount);
			String temp2 = VerifyAccount.numberExist(userInfoMap, strIdNo);
			
			//两个返回值相等且 不为“false” 账号存在
			boolean bln = temp1.equals(temp2) &&(!temp1.equals("false")) ;
			
			if (bln) {
				
				//账号身份证号正确查看一下信息
				Tool.look(userInfoMap, temp1);
		
				//信息无误 删除
					do{			
						
						//读取子菜单
						Tool.rUi(new File("txt"+File.separator+"SubMenu.txt"));
						int temp = Integer.valueOf(Tool.input());
						if (temp == 1) {
							
							//确认无误 删除 并返回更新后的userInfo
							userInfoMap.remove(temp1);
							System.out.println("删除成功！");
							break;
							
						}else if (temp == 2) {
							
							//重新输入
							continue aa;
							
						}else if (temp == 3) {
							
							//返回菜单
							break;
							
						}
							System.out.println("输入错误，重新输入！");
					}while(true);
				
				break;
			}
				System.out.println("输入错误，重新输入！");
		}
	}
		
}
