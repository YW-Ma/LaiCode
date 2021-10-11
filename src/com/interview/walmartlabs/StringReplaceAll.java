package com.interview.walmartlabs;

public class StringReplaceAll {
    public static void main(String[] args) {
        String str = "cbccbb";
        String replaced = str.replaceAll("cb", "a");
        String replacedRegex = str.replaceAll("[^c]+b", "a");
        System.out.println(str);
        System.out.println(replaced);
        System.out.println(replacedRegex);
    }
}
