package com.feicuiedu.atm.userbusiness;

import java.io.IOException;
import java.util.HashMap;

import com.feicuiedu.atm.userinfo.User;

// 用户业务父类
public abstract class UserParent {
	public abstract void userBusi(HashMap<String, User> userInfoMap,String key) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException;
}
