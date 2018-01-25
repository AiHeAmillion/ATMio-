package com.feicuiedu.atm.userbusiness;

import java.io.File;
import java.util.HashMap;

import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.userinfo.User;

//用户存钱
public class SaveMoney extends UserParent{
	
	@Override
	public void userBusi(HashMap<String, User> userInfoMap,String key){
		
		aa:
		while (true) {
			
			//获取指定键的值 = 要操作的对象
			User user = userInfoMap.get(key);
			System.out.println("*****存款业务*****");
			System.out.println("请输入存款金额：");	

			//接受输入的存钱数
			double input = Double.valueOf(Tool.input());
			
			while(true){		
				
				//读取子菜单1.确认 2.重新输入 3.返回主界面
				Tool.rUi(new File("txt"+File.separator+"SubMenu.txt"));
				int temp = Integer.valueOf(Tool.input());
				
				if (temp == 1) {
					System.out.println("wozaicunqian");
					//余额为钱数 + 存钱数
					user.setBalance(user.getBalance()+input);	
					
					//把取钱数转成字符串
					String s_temp = Double.toString(input);	
					
					//存入流水信息
					user.setFlow(user.getFlow().append("存款业务"+" "+"存款"+s_temp+"元"+" "+Tool.strDate()).append("\n"));	

					System.out.println("操作成功，自动返回主界面！");
					
					//更新Map里此键对应的值（对象）
					userInfoMap.put(key, user);
					break;
				}else if (temp == 2) {
					continue aa;
				}else if (temp == 3) {
					break;
				}
					System.out.println("输入错误，请重新输入！");
			}
				//用于跳出外层while
				break;
			
		}
	}
}
