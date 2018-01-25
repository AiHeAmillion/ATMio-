package com.feicuiedu.atm.userbusiness;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.userinfo.User;

//用户取钱
public class DrawMoney extends UserParent{
	
	@Override
	public void userBusi(HashMap<String, User> userInfoMap,String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
	
		aa:	
		do{			//do while循环+标签 处理 输入 1 2 3之外其他东西时重新输入
			//获取指定键的值 = 要操作的对象
			User user = userInfoMap.get(key);
			System.out.println("*****取款业务*****");
			System.out.println("请输入取款金额：");	
			double input = Double.valueOf(Tool.input()); //接受输入的取钱数

			if (user.getBalance()<input) {	
				//判断余额是否足够 不足的话返回主界面暗示用户查询余额或其他操作
				System.out.println("余额不足！");
				break;
			}
			
			//读取文件
			Tool.rUi(new File("txt"+File.separator+"SubMenu.txt"));
			
			int temp = Integer.valueOf(Tool.input());
			if (temp == 1) {
				user.setBalance(user.getBalance()-input);	// 余额为 钱数 - 取钱数
				
				//把取钱数转成字符串
				String s_temp = Double.toString(input);

				//存入流水信息
				user.setFlow(user.getFlow().append("取款业务"+" "+"取款"+s_temp+"元 "+" "+Tool.strDate()).append("\n"));	
				
				System.out.println("操作成功，返回主界面！");
				//更新这个Map集合
				userInfoMap.put(key, user);
				//返回这个新Map
				break;
			}else if (temp == 2) {
				continue aa;
			}else if (temp == 3) {
				break;
			}
				System.out.println("输入错误，请重新输入！");
		
		}
		while(true);
	
	}
}
