package com.wong.jvm.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceTest {
	// 當前類對象的聲明
	public static PhantomReferenceTest obj;
	// 引用隊列
	static ReferenceQueue<PhantomReferenceTest> phantomQueue = null;

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("調用當前類的finalize方法");
		obj = this;
	}

//	public static class CheckRefQueue extends Thread {
//		@Override
//		public void run() {
//			while (true) {
//				if (phantomQueue != null) {
//					PhantomReference<PhantomReferenceTest> objt = null;
//					try {
//						objt = (PhantomReference<PhantomReferenceTest>) phantomQueue.remove();
//					} catch (Exception e) {
//						e.getStackTrace();
//					}
//					if (objt != null) {
//						System.out.println("追踪垃圾回收過程：PhantomReferenceTest實例被GC了");
//					}
//				}
//			}
//		}
//	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			while (true) {
				if (phantomQueue != null) {
					PhantomReference<PhantomReferenceTest> objt = null;
					try {
						objt = (PhantomReference<PhantomReferenceTest>) phantomQueue.remove();
					} catch (Exception e) {
						e.getStackTrace();
					}
					if (objt != null) {
						System.out.println("追踪垃圾回收過程：PhantomReferenceTest實例被GC了");
					}
				}
			}
		}, "t1");
		thread.setDaemon(true); // 設置為守護線程，當程序中沒有非守護線程時，守護線程也就執行結束
		thread.start();

		phantomQueue = new ReferenceQueue<>();
		obj = new PhantomReferenceTest();
		// 構造了PhantomReferenceTest對象的虛引用，並指定了引用隊列
		PhantomReference<PhantomReferenceTest> phantomReference = new PhantomReference<>(obj, phantomQueue);
		try {
			System.out.println(phantomReference.get());
			// 去除強引用
			obj = null;
			// 第一次進行GC，由於對象可複活，GC無法回收該對象
			System.out.println("第一次GC操作");
			System.gc();
			Thread.sleep(1000);
			if (obj == null) {
				System.out.println("obj 是 null");
			} else {
				System.out.println("obj 不是 null");
			}
			System.out.println("第二次GC操作");
			obj = null;
			System.gc();
			Thread.sleep(1000);
			if (obj == null) {
				System.out.println("obj 是 null");
			} else {
				System.out.println("obj 不是 null");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
