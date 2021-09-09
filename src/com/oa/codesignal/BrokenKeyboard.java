package com.oa.codesignal;

// 题目是键盘坏了，只剩下一组valid letters中的字母按键和所有的数字和符号案件能用，
// 同时shift键是好的， 所以可以切换大小写。
// 问一组words中的单词有几个可以用当前坏掉的键盘打出来

// 输入一组words和一组valid letters，判断有多少个words是valid。
// 判断条件是words里的所有 upper and lower letter必须在valid letters里面。
// 如果word里面有special character不用管。
// 注意valid letter只有小写，但是words里面有大写的也算valid。

// 比如
// words = [hEllo##, This^^], valid letter = [h, e, l, 0, t, h, s];
// "hello##" 就是valid，因为h，e，l，o都在valid letter 里面，
// “This^^” 不valid, 因为i不在valid letter里面

import java.util.HashSet;

// input: a = "Hello, my dear friend!", b = ['h', 'e', 'l', 'o', 'm']
public class BrokenKeyboard {
    public int brokenKeyBoard(String str, char[] letters) {
        // preprocess str into word array
        String[] array = str.split(" "); // 按照空格分开

        // preprocess letters into HashSet
        boolean[] letterSet = new boolean[256];
        for (char ch : letters) {
            letterSet[ch] = true;
        }

        // check each string one by one
        int counter = 0;
        for (String word : array) {
            word = word.toLowerCase();
            boolean isValid = true;
            for (char ch : word.toCharArray()) {
                if (Character.isLetter(ch) && !letterSet[ch]) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                counter++;
            }
        }
        return counter;
    }
}
