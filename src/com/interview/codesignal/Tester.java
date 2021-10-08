package com.interview.codesignal;

public class Tester {
    public static void main(String[] args) {
        CompareStringWithFrequency compare = new CompareStringWithFrequency();
        boolean res = compare.compareStrings("babzccc", "abbzczz");
        System.out.println(res);

        BrokenKeyboard brokenKeyboard = new BrokenKeyboard();
        int counter = brokenKeyboard.brokenKeyBoard("world! Hello, world! hee oo", new char[]{'h','e','l','o'});
        System.out.println(counter);
    }
}
