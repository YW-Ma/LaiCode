package com.oa.codesignal;

public class ReverseInteger {
    // 同时练习一下reverse integer
    // 【注意】 坑点在于可能引发overflow。 要和对方沟通overflow的处理。
    // Integer.MAX_VALUE = 2^31 - 1 = 2147483647  个位是7
    // Integer.MIN_VALUE = -2^31 = -2147483648    个位是8
    public static int reverse(int x) {
        // 123 --> 321
        // 120 --> 21
        // 0 --> 0
        // -123 --> -321
        
        int res = 0;
        while (x != 0) {
            int digit = x % 10;
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > 7)) return 0;  // handle overflow
            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && digit < -8)) return 0;
            res = res * 10 + digit;
            x /= 10;
        }
        return res;
    }
    // Time Complexity: O(log_10(x))
}
