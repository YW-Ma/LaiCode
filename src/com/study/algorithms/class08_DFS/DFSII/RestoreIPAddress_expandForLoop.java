package com.study.algorithms.class08_DFS.DFSII;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress_expandForLoop {
    // 答案方法：把for循环展开，添加1个、2个、3个数字的方法是拆开来的，这样可以避免一个特别复杂的getValidSegment
    // 以后遇到了“尝试1个、2个...k个字符”的时候，可以试试不用for循环而是直接展开写k个branch。
    public static void main(String[] args) {
        Restore("10809010");
    }
    
    public static List<String> Restore(String ip) {
        List<String> results = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] array = ip.toCharArray();
        helper(0, 0, array, sb, results);
        return results;
    }
    
    private static void helper(int level, int startIdx, char[] ip, StringBuilder sb, List<String> results) {
        // index is the starting position of current segmentation.
        // base case
        if (level == 4) {
            if (sb.length() == ip.length + 4) { // 说明把四个dot都加上了（记得删掉最后一个）
                results.add(sb.substring(0, sb.length() - 1)); // 删掉最后一个dot
            }
            return;
        }
        
        // recursion rule: expand for loop
        if (startIdx < ip.length) {
            sb.append(ip[startIdx]).append('.'); // 可以串起来写
            helper(level + 1, startIdx + 1, ip, sb, results);
            sb.delete(sb.length() - 2, sb.length());
        }
        if (startIdx + 1 < ip.length) {
            // two digits, cannot start with 0
            char a = ip[startIdx];
            char b = ip[startIdx + 1];
            if (a != '0') {
                sb.append(a).append(b).append('.'); // 可以串起来写
                helper(level + 1, startIdx + 2, ip, sb, results);
                sb.delete(sb.length() - 3, sb.length());
            }
        }
        if (startIdx + 2 < ip.length) {
            // three digits, cannot start with 0, cannot exceed 255. --> 可以写成 1xx || < 255
            char a = ip[startIdx];
            char b = ip[startIdx + 1];
            char c = ip[startIdx + 2];
            int value = 100 * (a - '0') + 10 * (b - '0') + (c - '0');
            if (a != '0' && value <= 255) {
                sb.append(a).append(b).append(c).append('.'); // 可以串起来写
                helper(level + 1, startIdx + 3, ip, sb, results);
                sb.delete(sb.length() - 4, sb.length());
            }
        }
    }
}
