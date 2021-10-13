package com.interview.misc;

public class StringAPIs {
    public static void main(String[] args) {
        String str = "123456";
        String subStr1 = str.substring(3);
        String subStr2 = str.substring(3, str.length());
        int value = Integer.parseInt(str);
        System.out.println("str is " + str);
        System.out.println("str.substring(3) is " + subStr1); // start from 3 to end
        System.out.println("str.substring(3, str.length()) is " + subStr1); // start from 3 to str.length() - 1
        System.out.println("Integer.parseInt(str) is " + value); // parse string and get an integer
    
        String str2 = "abc12";
        System.out.println("str is " + str2);
        System.out.println("Integer.parseInt(str2) is " + Integer.parseInt(str2)); // parse string and get an integer
    }
}
