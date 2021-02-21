package com.wong.exception;

/*
編寫應用程序EcmDef.java，接收命令行的兩個參數，要求不能輸入負數，計算兩數相除。
對數據類型不一致(NumberFormatException)、缺少命令行參數(ArrayIndexOutOfBoundsException、
  除0(ArithmeticException)及輸入負數(EcDef 自定義的異常)進行異常處理。
提示：
(1)在主類(EcmDef)中定義異常方法(ecm)完成兩數相除功能。
(2)在main()方法中使用異常處理語句進行異常處理。
(3)在程序中，自定義對應輸入負數的異常類(EcDef)。
(4)運行時接受參數 java EcmDef 20 10
//args[0]=“20” args[1]=“10”
(5)Interger類的static方法parseInt(String s)將s轉換成對應的int值。如int a=Interger.parseInt(“314”); //a=314;
* */

//  run as --> Run Configurations --> Arguments
public class EcmDef {
    public static void main(String[] args) {
        try {
            String a = args[0];
            String b = args[1];
            int x = Integer.parseInt(a);
            int y = Integer.parseInt(b);
            System.out.println(ecm(x, y));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("對數據類型不一致");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("缺少命令行參數");
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
            System.out.println("除數不能為0");
        } catch (EcDef e) {
            System.out.println(e.getMessage());
        }

    }

    public static double ecm(int a, int b) throws EcDef {
        if (a < 0 || b < 0) {
            throw new EcDef("輸入的數不能為負數");
        }
        return a / b;
    }
}

//
class EcDef extends Exception {
    /*
    自定義異常類型
    * */
    static final long serialVersionUID = -3387516993124229999L;

    // 構造器
    public EcDef() {
        super();
    }

    public EcDef(String message) {
        super(message);
    }
}
