package com.study.practice.class_19.Enum;

import java.util.Arrays;

public class MyRainbowEnum {
    // Enum是枚举，非常有限的可能性，限制死了。
    // 比如一周七天，彩虹七色

    // 如果没有Enum，可能需要把彩虹的七种颜色声明称 static final int
    // - 但是不是很好：1. 需要维护num到颜色的映射， 2. 给我传个8，我run time才发现出不来
    public static class RainbowColor_NotGood {
        public static final int RED = 0;
        public static final int ORANGE = 1;
        public static final int YELLOW = 2;
        public static final int GREEN = 3;
        public static final int CYAN = 4;
        public static final int BLUE = 5;
        public static final int PURPLE = 6;
    }


    // 定义Enum：（还有别的方法，可以提供method和值）
    enum RainbowColor { RED, ORANGE, YELLOW, GREEN, CYAN, BLUE, PURPLE }
    enum WeekDayEnum { Mon, Tue, Wed, Thu, Fri, Sat, Sun }

    // 定义带field和methods的enum：
    enum RainbowColor2 {
        RED(1, "RED"),
        GREEN(2, "GREEN"),
        BLUE(3, "BLUE");

        int order;
        String name;
        RainbowColor2(int n, String name) {
            this.order = n;
            this.name = name;
        }
    }

    // 使用Enum：
    public void getToday(WeekDayEnum today) {
        switch(today) {
            case Mon: //do something; break;
            case Tue: //do something; break;
            case Wed: //do something; break;
            case Thu: //do something; break;
            case Fri: //do something; break;
            case Sat: //play sports game; break;
            case Sun: //have a rest; break;
        }
    }

    public void useMethods() {
        // values --> static method, 返回内容
        RainbowColor[] colors = RainbowColor.values();
        // ordinal --> non-static, belongs to an instance
        int order = RainbowColor.BLUE.ordinal();
        // valueOf --> static method, give a string, found corresponding instance
        RainbowColor color = RainbowColor.valueOf("BLUE");

        System.out.println(Arrays.toString(colors));
        System.out.println(order);
        System.out.println(color);
    }

    public void advancedEnum() {
        System.out.println(RainbowColor2.RED);
        System.out.println(RainbowColor2.RED.name);
        System.out.println(RainbowColor2.RED.order);

    }
}
