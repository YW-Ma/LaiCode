package com.interview.Google;

public class MaximumTimeCanDifferent {
    public static String giveMeMaxTime(String t) {
        // 用char array处理会好一些
        char[] time = t.toCharArray();
        // 0,1 是前两位   3是:    4,5是后两位
        int possibleMaxTime = Integer.MAX_VALUE;
        if (time[0] == '?') { // 0-1 if time[1] is 4-9,  0-2 if time[1] is 0-9
            if (time[1] - '0' >= 4 && time[1] - '0' <= 9) {
                time[0] = '1';
            } else { // 0-3 or ? in second place,  23:59 is the largest.
                time[0] = '2';
            }
        }
        if (time[1] == '?') { // 0 - 3 if time[0] is 2,   0-9 if time[0] is 0,1
            if (time[0] == '2') {
                time[1] = '3';
            } else {
                time[1] = '9';
            }
        }
        if (time[3] == '?') { // 0 - 5
            time[3] = '5';
        }
        if (time[4] == '?') { // 0 - 5
            time[4] = '9';
        }
        return new String(time);
    }

    public static void main(String[] args) {
        System.out.println(giveMeMaxTime("23:5?"));//23:59
        System.out.println(giveMeMaxTime("2?:22"));//23:22
        System.out.println(giveMeMaxTime("0?:??"));//09:59
        System.out.println(giveMeMaxTime("1?:??"));//19:59
        System.out.println(giveMeMaxTime("?4:??"));//14:59
        System.out.println(giveMeMaxTime("?3:??"));//23:59
        System.out.println(giveMeMaxTime("??:??"));//23:59
        System.out.println(giveMeMaxTime("?4:5?"));//14:59
        System.out.println(giveMeMaxTime("?4:??"));//14:59
        System.out.println(giveMeMaxTime("?3:??"));//23:59
        System.out.println(giveMeMaxTime("23:5?"));//23:59
        System.out.println(giveMeMaxTime("2?:22"));//23:22
        System.out.println(giveMeMaxTime("0?:??"));//09:59
        System.out.println(giveMeMaxTime("1?:??"));//19:59
        System.out.println(giveMeMaxTime("?4:0?"));//14:09
        System.out.println(giveMeMaxTime("?9:4?"));//19:49
    }
}
