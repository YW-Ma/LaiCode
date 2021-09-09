package com.oa.codesignal;

public class Tester {
    public static void main(String[] args) {
        CompareStringWithFrequency compare = new CompareStringWithFrequency();
        boolean res = compare.compareStrings("babzccc", "abbzczz");
        System.out.println(res);
    }
}
