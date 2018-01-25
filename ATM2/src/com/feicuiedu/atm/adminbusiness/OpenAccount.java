package com.feicuiedu.atm.adminbusiness;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.feicuiedu.atm.tool.Tool;
import com.feicuiedu.atm.tool.VerifyAccount;
import com.feicuiedu.atm.userinfo.User;

//管理员开户
public class OpenAccount extends AdminParent {
	
	@Override
	public void adminBusi(HashMap<String, User> userInfoMap) {	
		
		//读取开户的Ui界面
		Tool.rUi(new File("txt"+File.separator+"OpenAccount.txt"));
			
		User user = new User();
		
		//序号
		String number = (Tool.input());
		
		//姓名
		user.setName(Tool.input());
		
		//性别
		user.setGender(Integer.valueOf(inputGender()));
		
		//学历
		user.setEducation(Tool.input());
		
		//联系地址
		user.setAddress(Tool.input());
		
		//身份证号
		user.setIdNo(inputStrIdNo(userInfoMap));
			
		//密码
		user.setPassword(inputPassword());
		
		//账号
		user.setAccount(inputStrAccount(user.getGender()));
				
		//显示账号信息
		System.out.println("账号：\n"+user.getAccount());
				
		userInfoMap.put(number, user);
		System.out.println("开户成功！");
	}
	
	
	//检测输入的是否为1/2
	public String inputGender() {
		
		while (true) {
			String gender = Tool.input();
			if ("1".equals(gender)) {
				return gender;
			}else if ("2".equals(gender)) {
				return gender;
			}
			System.out.println("请输入1/2表示！");
		}	
	}
	
	//检测输入身份证是否正确或者唯一
	public String inputStrIdNo(HashMap<String, User> userInfoMap) {
		
		do {
			
			//身份证号
			String strIdNo = Tool.input();
			
			//验证长度格式是否正确
			boolean temp = VerifyAccount.numberLengeth(strIdNo);
			
		    //验证是否已经存在    不存在返回key="false"
			String key = VerifyAccount.numberExist(userInfoMap, strIdNo);
			
			boolean bln = temp && "false".equals(key);
			
			if (bln) {
				
				//格式正确且唯一返回输入的该号
				return strIdNo;
			}
			
			System.out.println("身份证输入错误，重新输入！");
		} while (true);
	}
	
	//验证输入密码的格式
	public String inputPassword() {
		
		do {
			
			String strPassword = Tool.input();
			
			boolean bln =  VerifyAccount.passwordLength(strPassword);
			if (bln) {
				
				//输入密码的格式符合
				return strPassword;
			}
			
			System.out.println("密码格式错误！");
		} while (true);
	}
	
	
	//生成账号
	public String inputStrAccount(int gender) {
		
		//账号 系统自动生成  规则  37+(如果是男01,如果是女02)+当前时间(年月日时分秒毫秒)
		Date date = new Date();	
						
		//格式化输出时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsssss");
		String strAccount = "370"+gender+sdf.format(date);
		
		return strAccount;
	}
	
}
