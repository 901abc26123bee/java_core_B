package com.wong.jvm.gc;

/**
 * 測試Object類中finalize()方法，即物件的finalization機制。
 */
public class CanReliveObj {
    public static CanReliveObj obj;//類變數，屬於 GC Root


    //此方法只能被呼叫一次
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("呼叫當前類重寫的finalize()方法");
        obj = this; //當前 待回收的物件 在finalize()方法中 與 引用鏈上的一個物件obj 建立了聯絡
    }


    public static void main(String[] args) {
        try {
            obj = new CanReliveObj();
            // 物件第一次成功拯救自己
            obj = null;
            System.gc();//呼叫垃圾回收器
            System.out.println("第1次 gc");
            // 因為Finalizer執行緒優先順序很低，讓主線程main 暫停2秒，以等待它 System.gc() 執行
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
          
            System.out.println("第2次 gc");
            // 下面這段程式碼與上面的完全相同，但是這次自救卻失敗了
            obj = null;
            System.gc();
            // 因為Finalizer執行緒優先順序很低，暫停2秒，以等待它
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}