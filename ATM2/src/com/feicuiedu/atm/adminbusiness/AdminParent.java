package com.feicuiedu.atm.adminbusiness;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import com.feicuiedu.atm.userinfo.User;

public abstract class AdminParent {
	public abstract void adminBusi(HashMap<String, User> userInfoMap) throws FileNotFoundException, IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException;

}
