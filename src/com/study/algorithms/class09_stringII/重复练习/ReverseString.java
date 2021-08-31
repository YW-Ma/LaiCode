package com.study.algorithms.class09_stringII.重复练习;

public class ReverseString {
    public String reverse(String input) {
        // corner case
        if (input == null || input.length() == 0) {
            return input;
        }

        int left = 0;
        int right = input.length() - 1;
        char[] array = input.toCharArray();
        while (left < right) {
            swap(array, left, right);
            left++;
            right--;
        }
        return new String(array);
    }

    private void swap(char[] array, int left, int right) {
        char tmp = array[left];
        array[left] = array[right];
        array[right] = tmp;
    }
}
