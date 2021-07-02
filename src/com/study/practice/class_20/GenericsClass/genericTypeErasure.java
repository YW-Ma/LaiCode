package com.study.practice.class_20.GenericsClass;

import java.util.ArrayList;

class TypeErasure {
    // generic method printArray
    public static <E> void printArray1(E[] inputArray) {
        // Display array elements
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }


    // generic method printArray
    public static void printArray2(Object[] inputArray) {
        // Display array elements
        for (Object element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    // generic method printArray
    public static <E extends Comparable<E>> void printArray3(E[] inputArray) {
    }


    // generic method printArray
    public static void printArray4(Comparable[] inputArray) {
    // Display array elements
        for (Comparable element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }
}

public class genericTypeErasure {
    public static void main(String[] args) {
        ArrayList<Integer> a1 = new ArrayList<Integer>();
        ArrayList<String> a2 = new ArrayList<String>();
        Class c1 = a1.getClass();
        Class c2 = a2.getClass();
        System.out.println(c1==c2); // 判定两个类型相同 
        System.out.println(c1); // class java.util.ArrayList
        try {
            a1.getClass()
                    .getMethod("add", Object.class) 
                    .invoke(a1, "a"); // 成功将字符串对象插入整数数组
            System.out.println(a1.size());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
} 

