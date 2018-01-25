package com.feicuiedu.atm.userbusiness;

import java.io.File;
import java.util.HashMap;

import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.tool.VerifyAccount;
import com.feicuiedu.atm.userinfo.User;

//用户转账
public class TransferBussiness extends UserParent {

	//转账
	@Override
	public void userBusi(HashMap<String, User> userInfoMap,String key){	
		
		aa:
		while (true) {
			System.out.println("*****转账业务*****");
			System.out.println("请输入对方银行卡号：");		
			String number = Tool.input();				//对方卡号
			System.out.println("请输入转账金额：");	
			double temp = Double.valueOf(Tool.input());//转账的金额

			String key2 = "";  // 2号用户的键
			
			//调用验证 输入的2号User账号是否存在
			key2 = VerifyAccount.numberExist(userInfoMap,number);
			
			if (key2.equals("false")) {	
				
				//账号不存在 
				System.out.println("账号不存在,重新输入！");	
				continue aa;
			}
			
			 //检测余额是否足够转账
			if (userInfoMap.get(key).getBalance()<temp) {
				System.out.println("余额不足！");
				
				//跳转到菜单 暗示查询
				break;
			}
			
			
			do{
				//读取子菜单 1.确认 2.重新输入 3.返回菜单
				Tool.rUi(new File("txt"+File.separator+"SubMenu.txt"));
				
				int temp2 = Integer.valueOf(Tool.input());
				if (temp2 == 1) {			
					
					//弹出显示对方信息界面
					showInformation(userInfoMap,key,key2,temp);		
					break;
					
				}else if (temp2 == 2) {		
					
					//重新调用转账界面
					continue aa;	
					
				}else if (temp2 == 3) {
					
					// 返回主菜单
					break;	
					
				}
			}
			while(true);
		}
		
	}	
	//显示对方卡号信息方法 
	public void	showInformation(HashMap<String, User> userInfoMap,String key,String key2,double temp){	
		
		do{	
			System.out.println("对方卡号："+userInfoMap.get(key2).getAccount());
			System.out.println("对方姓名："+userInfoMap.get(key2).getName());
			System.out.println("转账金额："+temp);	
			System.out.println("     1.确认转账");
			System.out.println("     2.返回上一级");
			System.out.println("     3.返回菜单");
			
			int temp5 = Integer.valueOf(Tool.input());
			if (temp5 == 1) {	// 1确认转账
				
				// 1卡余额为余额-转账金额
				userInfoMap.get(key).setBalance(userInfoMap.get(key).getBalance()-temp);
				
				// 转账账户加钱
				userInfoMap.get(key2).setBalance(userInfoMap.get(key2).getBalance()+temp);
				
				//把取钱数转成字符串
				String s_temp = Double.toString(temp);	

				//1号用户存入流水信息
				userInfoMap.get(key).setFlow(userInfoMap.get(key).getFlow().append("转账业务"+" "+"向"+userInfoMap.get(key2).getAccount()+"转"+s_temp+"元"+" "+Tool.strDate()).append("\n"));	
				
				//2号用户存入流水信息
				userInfoMap.get(key2).setFlow(userInfoMap.get(key2).getFlow().append("转账业务"+" "+"收到"+userInfoMap.get(key).getAccount()+"转"+s_temp+"元"+" "+Tool.strDate()).append("\n"));
				
				System.out.println("操作成功！");
				break;
			}
			else if (temp5 == 2) {		// 2返回上一级
				
				break;	//有问题
				
				
			}
			else if (temp5 == 3) {		// 3返回菜单
				
				//跳转到菜单界面 
				break;
			}
				//输入 1 2 3之外的东西还在显示界面
				System.out.println("输入错误，请重新输入！");
				
		}
		while(true);
	}
	
}
