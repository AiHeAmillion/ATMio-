package com.feicuiedu.atm;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

//运行测试
public class Test {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Register register = new Register();
		register.login();
		
	}
	
}
