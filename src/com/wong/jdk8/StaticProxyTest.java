package com.wong.jdk8;

public class StaticProxyTest {
	public static void main(String[] args) {
		NikeClothFactory nike = new NikeClothFactory();
		// 靜態代理類，編譯時顯示定義，寫死的
		ProxyClothFactory proxy = new ProxyClothFactory(nike);
		proxy.produceCoth();
	}
}

interface ClothFactory{
	void produceCoth();
}

class ProxyClothFactory implements ClothFactory{
	
	private ClothFactory factory;
	
	public ProxyClothFactory(ClothFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void produceCoth() {
		System.out.println(" ... ProxyClothFactory prepare something ...");
		factory.produceCoth();
		System.out.println(" ... ProxyClothFactory cleaning up ...");
	}
	
}

class NikeClothFactory implements ClothFactory{

	@Override
	public void produceCoth() {
		System.out.println(" NikeClothFactory producing products ");
	}
	
}
