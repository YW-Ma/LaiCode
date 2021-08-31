package com.study.algorithms.class09_stringII.重复练习;

public class ReverseWords {
    // “I love Google” → “Google love I”
    public String reverseWords(String input) {
        if (input == null || input.length() == 0) {
            return input;
        }

        char[] array = input.toCharArray();
        // step 1, reverse all elements
        reverse(array, 0, array.length - 1);

        // step 2, reverse each words.
        int leftBorder = 0;
        for (int i = 0; i <= array.length; i++) {
            if (i == array.length || array[i] == ' ') { // 【review】 elgooG evol I 的最后一个单词I，后面并没有空格哦。
                reverse(array, leftBorder, i - 1);
                leftBorder = i + 1;
            }
        }
        return new String(array);
    }

    private void reverse(char[] array, int l, int r) {
        while (l < r) {
            char tmp = array[l];
            array[l] = array[r];
            array[r] = tmp;
            l++;
            r--;
        }
    }
}
