package com.study.practice.class09_bit_representation.重听;

public class AllUniqueChars {
    /*
    *   Determine if the characters of a given string are all unique.

        Assumptions
            We are using ASCII charset, the value of valid characters are from 0 to 255
            The given string is not null

        Examples
            all the characters in "abA+\8" are unique
            "abA+\a88" contains duplicate characters
    *
    * */

    public boolean allUnique(String src) {
        // ASCII --> 0 - 255
        // 256 = 32 * 8
        int[] dict = new int[8];
        char[] array = src.toCharArray();
        for (char c : array) {
            int row = c / 32;
            int col = c % 32;
            if (((dict[row] >> col) & 1) == 1) {
                return false;
            }
            dict[row] |= 1 << col;
        }
        return true;
    }
}
