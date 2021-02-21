package com.wong.jdk8;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {
	public static void main(String[] args) {
		Man man = new Man();
		Human proxyInstance = (Human)ProxyFactory.getProxyInstance(man);
		
		System.out.println(proxyInstance.getBelief());
		proxyInstance.eat("apple");
		
		System.out.println("************************");
		// 運行時，動態生成代理類，只需提供被代理類
		NikeClothFactory nike = new NikeClothFactory();
		ClothFactory proxyInstance2 = (ClothFactory)ProxyFactory.getProxyInstance(nike);
		proxyInstance2.produceCoth();
	}
}

class ProxyFactory {
	// return a proxy
	public static Object getProxyInstance(Object obj) {
		MyInvocationHandler handler = new MyInvocationHandler();
		
		handler.bind(obj);
		
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
	}
}

class MyInvocationHandler implements InvocationHandler{
	
	private Object obj; 
	
	public void bind(Object obj) {
		this.obj = obj;
	}
	
	@Override
	public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws Throwable{
		HumanUtil util = new HumanUtil();
		util.method1();
		
		// 即為代理類對象調用的方法，也就是作為被代理類對象調用的方法
		Object returnvalue = method.invoke(obj, args);
		
		util.method2();
		return returnvalue;
	}
}

interface Human{
	String getBelief();
	void eat(String food);
}

class Man implements Human{

	@Override
	public String getBelief() {
		return "I belief";
	}

	@Override
	public void eat(String food) {
		System.out.println("Man eat");
	}
}

class HumanUtil{

	public void method1() {
		System.out.println("========= methodutil 1 =========");
	}
	
	public void method2() {
		System.out.println("========= methodutil 2 =========");
	}
	
}