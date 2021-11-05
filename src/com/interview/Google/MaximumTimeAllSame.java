package com.interview.Google;

public class MaximumTimeAllSame {
    // 给定一个时间，时间里可能有?
    // 把?更换成最大的可能时间
    // 今年的题目中所有的?只能更换为相同的数字

    // 考点就是：__ : __
    //   第一位依赖第二位，如果第二位大于等于4则只能2，否则可以0 1 2，
    //   第二位依赖第一位取值，为0、1的时候可以是0-9， 为2的时候只能在0-3
    //   第三位是0-5，
    //   第四位是0-9.
    // 难点就是前两位。

    // 最大指的是时间上最大，比如23:59是最大的，他比 19:59 大，尽管后者sum更大。

    public static String giveMeMaxTime(String t) {
        // 用char array处理会好一些
        char[] time = t.toCharArray();
        // 0,1 是前两位   3是:    4,5是后两位
        int possibleMaxTime = Integer.MAX_VALUE;
        if (time[0] == '?') { // 0-1 if time[1] is 4-9,  0-2 if time[1] is 0-9
            if (time[1] - '0' >= 4 && time[1] - '0' <= 9) {
                possibleMaxTime = 1;
            } else { // 0-3 or ?
                possibleMaxTime = 2;
            }
            return replaceMarker(time, possibleMaxTime);
        }
        if (time[1] == '?') { // 0 - 3 if time[0] is 2,   0-9 if time[0] is 0,1
            if (time[0] == '2') {
                possibleMaxTime = 3;
            } else {
                possibleMaxTime = 9;
            }
        }
        if (time[3] == '?') { // 0 - 5
            possibleMaxTime = Math.min(possibleMaxTime, 5);
        }
        if (time[4] == '?') { // 0 - 5
            possibleMaxTime = Math.min(possibleMaxTime, 9);
        }
        return replaceMarker(time, possibleMaxTime);
    }

    private static String replaceMarker(char[] time, int possibleMaxTime) {
        if (possibleMaxTime == Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < time.length; i++) {
            if (time[i] == '?') {
                time[i] = (char)('0' + possibleMaxTime);
            }
        }
        return new String(time);
    }

    public static void main(String[] args) {
        System.out.println(giveMeMaxTime("23:5?"));// 23:59
        System.out.println(giveMeMaxTime("2?:22"));// 23:22
        System.out.println(giveMeMaxTime("0?:??"));// 05:55
        System.out.println(giveMeMaxTime("1?:??"));// 15:55
        System.out.println(giveMeMaxTime("?4:??"));// 14:11
        System.out.println(giveMeMaxTime("?3:??"));// 23:22
        System.out.println(giveMeMaxTime("??:??"));// 22:22
        System.out.println(giveMeMaxTime("?4:5?")); //14:51
        System.out.println(giveMeMaxTime("?4:??")); //14:11
        System.out.println(giveMeMaxTime("?3:??")); //23:22
        System.out.println(giveMeMaxTime("23:5?")); //23:59
        System.out.println(giveMeMaxTime("2?:22")); //23:22
        System.out.println(giveMeMaxTime("0?:??")); //05:55
        System.out.println(giveMeMaxTime("1?:??")); //15:55
        System.out.println(giveMeMaxTime("?4:0?")); //14:01
        System.out.println(giveMeMaxTime("?9:4?")); //19:41
    }
}
