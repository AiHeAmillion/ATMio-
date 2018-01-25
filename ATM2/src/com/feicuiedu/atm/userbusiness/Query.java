package com.feicuiedu.atm.userbusiness;

import java.util.HashMap;

import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.userinfo.User;

//用户查询
public class Query extends UserParent {
	
	// 查询用户信息 返回值为用户在此输入值 参数为user类的一个对象
	@Override
	public void userBusi(HashMap<String, User> userInfoMap,String key){   

		while (true) {
			
			//通过制定键获取值即要操作对象
			User user = userInfoMap.get(key);
			
			System.out.println("账户姓名:"+user.getName());
			System.out.println("账户余额:"+user.getBalance()+"元");
			System.out.println("1.返回上一级");
			
			//存入流水信息
			user.setFlow(user.getFlow().append("查询业务"+" "+Tool.strDate()).append("\n"));	
			
			int input = Integer.valueOf(Tool.input());
			if (input == 1 ) {	
				
				//把修改的User更新到HashMap
				userInfoMap.put(key, user);
				
				// 跳出返回上一级
				break;
			}
			System.out.println("输入错误！重新输入");
		}	
	
	}
	
}
