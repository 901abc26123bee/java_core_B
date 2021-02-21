package com.wong.jdk9;

public class MyInterfaceImpl implements MyInterface{

	@Override
	public void methodAbstract() {
		
	}
	
	// default can either overwrite the default method or not 
//	@Override
//	public void methodDefault() {
//		System.out.println("MyInterfaceImpl 默認方法");
//	}
	public static void main(String[] args) {
		// the static method declare in interface can only be used by interface itself
		MyInterface.methodStatic();
		// 接口實現類不能調用街口靜態方法
//		MyInterfaceImpl.methodStatic();// error
		MyInterfaceImpl myInterfaceImpl = new MyInterfaceImpl();
		myInterfaceImpl.methodDefault();
//		myInterfaceImpl.methodPrivate();
	}
	
}
