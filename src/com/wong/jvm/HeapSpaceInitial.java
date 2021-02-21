package com.wong.jvm;

/**
 * 1. 設定堆空間大小的引數
 * -Xms 用來設定堆空間（年輕代+老年代）的初始記憶體大小
 *      -X 是jvm的執行引數
 *      ms 是memory start
 * -Xmx 用來設定堆空間（年輕代+老年代）的最大記憶體大小
 *
 * 2. 預設堆空間的大小
 *    初始記憶體大小：物理電腦記憶體大小 / 64
 *             最大記憶體大小：物理電腦記憶體大小 / 4
 * 3. 手動設定：-Xms600m -Xmx600m
 *     開發中建議將初始堆記憶體和最大的堆記憶體設定成相同的值。
 *
 * 4. 檢視設定的引數：方式一： jps   /  jstat -gc 程序id
 *                  方式二：-XX:+PrintGCDetails
 */
public class HeapSpaceInitial {
  public static void main(String[] args) {

    //返回Java虛擬機器中的堆記憶體總量
    long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
    //返回Java虛擬機器試圖使用的最大堆記憶體量
    long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

    System.out.println("-Xms : " + initialMemory + "M");
    System.out.println("-Xmx : " + maxMemory + "M");

    System.out.println("系統記憶體大小為：" + initialMemory * 64.0 / 1024 + "G");
    System.out.println("系統記憶體大小為：" + maxMemory * 4.0 / 1024 + "G");

//    try {
//      // 使用 -XX:+PrintGCDetails 將時間調小
//      Thread.sleep(1000000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
  }
}