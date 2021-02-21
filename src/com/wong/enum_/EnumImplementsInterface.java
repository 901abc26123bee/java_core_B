package com.wong.enum_;

public class EnumImplementsInterface {

}
/*
枚舉類實現接口
* */

interface Info {
    void show();
}

enum Season3 implements Info {
    // 創建類的實例對像只能放在最前面，多個實例之間用","分隔,
    SPRING ("spring", "春暖"),
    SUMMER ("summer", "夏熱"),
    AUTUMN ("autumn", "秋涼"),
    WINTER ("winter", "冬寒");

    // 1 屬性，聲明為private final
    private final String seasonName;
    private final String seasonDesc;

    // 構造器，默認已經是 private
    Season3(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    // 方法
    // 提供public方法訪問屬性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }

    @Override
    public void show() {
        System.out.println("四季之一: " + seasonName);
    }

}


enum Season4 implements Info {

    // 特殊的實現
    SPRING ("spring", "春暖") {
        public void show() {
            System.out.println("春眠不覺曉");
        }
    },
    SUMMER ("summer", "夏熱") {
        public void show() {
            System.out.println("夏日炎炎");
        }
    },
    AUTUMN ("autumn", "秋涼") {
        public void show() {
            System.out.println("秋風煞爽");
        }
    },
    WINTER ("winter", "冬寒") {
        public void show() {
            System.out.println("千里冰封，萬里雪飄");
        }
    };

    private final String seasonName;
    private final String seasonDesc;

    Season4(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    // 方法
    // 提供public方法訪問屬性
    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }

}