package com.study.algorithms.class09_stringII.重复练习;

public class DecompressString {
    // 1 calculate how large will be put
    // y2s1g3a4
    // 2 + 1 + 3 + 4 + (count of 1) = 11
    // y2s1g3a4.length() = 8
    // 11 - 8 = 3 需要3个额外空间

    // 从右往左写入
    // y2s1g3a4___
    //        ^  ^
    // y2s1g3aaaaa
    //      ^^
    // y2s1gggaaaa
    //    ^
    //    ^
    // y2ssgggaaaa
    //  ^^
    // yyysgggaaaa
    //^^
    // 返回slow右侧的（第一个不要了诶）
    public String decompress(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }
        // build buffer
        char[] array = input.toCharArray();
        int bufferLength = getDecodedBuffer(array);
        char[] buffer = new char[bufferLength];
        for (int i = 0; i < array.length; i++) {
            buffer[i] = array[i];
        }

        // decode
        int writer = bufferLength - 1;
        int reader = array.length - 1;
        while (reader >= 0) {
            int digit = getDigit(buffer[reader]);
            reader--;
            char ch = buffer[reader];
            reader--;

            for (int i = 0; i < digit; i++) {
                buffer[writer - i] = ch;
            }
            writer -= digit;
        }


//        data – the array of characters
//        start – the starting offset in the character array
//        length – the number of characters to use
        return new String(buffer, writer + 1, bufferLength - 1 - writer); // char[], start, length
    }

    private int getDecodedBuffer(char[] array) {
        int length = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < '0' || array[i] > '9') {
                continue;
            }
            int digit = getDigit(array[i]); // 从前往后撸一遍。
            if (digit == 0 || digit == 1) {
                length += 2; // 至少要保留原来的长度
            } else {
                length += digit;
            }
        }
        return length;
    }

    // Assumption: There are no adjacent repeated characters with length > 9
    private int getDigit(char c) {
        return c - '0';
    }
}
