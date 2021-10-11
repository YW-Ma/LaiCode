package com.interview.walmartlabs;

public class Palindrome {
    public static boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) {
            // 个位不可以是0(0除外)， 负数不可对。
            return false;
        }

        int lowerHalf = 0;
        while (x > lowerHalf) {
            lowerHalf *= 10;
            lowerHalf += x % 10;
            x /= 10;
        }

        if (x == lowerHalf || x == lowerHalf / 10) {
            return true;
        }
        return false;
    }
}
