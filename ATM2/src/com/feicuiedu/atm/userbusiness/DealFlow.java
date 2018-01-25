package com.feicuiedu.atm.userbusiness;

import java.util.HashMap;

import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.userinfo.User;

//流水信息
public class DealFlow extends UserParent{
	@Override
	public void userBusi(HashMap<String, User> userInfoMap,String key){	//显示流水
		
		//  用while 取消递归
		boolean temp = true;
		User user = userInfoMap.get(key);
		while (temp) {
			System.out.println("交易流水");
			System.out.println(user.getFlow());
			System.out.println("1.返回上一级");
			int input = Integer.valueOf(Tool.input());
			
			if (input !=1 ) {	//判断输入的是不是 1
				System.out.println("输入错误！重新输入");
			}
			
			temp = false;
		}
		
	}
}
