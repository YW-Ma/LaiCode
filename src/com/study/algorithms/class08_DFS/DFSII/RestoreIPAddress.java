package com.study.algorithms.class08_DFS.DFSII;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
    public static void main(String[] args) {
        Restore("10809010");
    }
    
    // IP address has 3 point
    // if we want to put a point: we need to make sure the segment before it is valid.
    // for the last segment (base case), we will check if it is valid.
    // 123.222.234.225
    //             012
    //             ^ ^   2-0 = 2
    
    // 递归树： 一共有4层，每层添加1个、2个、3个数字。
    // 答案方法：把for循环展开，添加1个、2个、3个数字的方法是拆开来的，这样可以避免一个特别复杂的getValidSegment
    
    public static List<String> Restore(String ip) {
        List<String> results = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        char[] array = ip.toCharArray();
        helper(3, 0, array, sb, results);
        return results;
    }
    
    private static void helper(int pointsLeft, int startIdx, char[] array, StringBuilder sb, List<String> results) {
        // index is the starting position of current segmentation.
        // base case
        if (pointsLeft == 0) {
            String seg = getValidSegment(array, startIdx, array.length - 1);
            if (seg != null) {
                sb.append(seg);
                results.add(sb.toString());
                sb.delete(sb.length() - (array.length - startIdx), sb.length()); //delete [start, end)
            }
            return;
        }
        
        // recursion rule:
        for (int i = startIdx; i <= startIdx + 2; i++) {
            String seg = getValidSegment(array, startIdx, i);
            if (seg != null) {
                sb.append(seg).append('.');
                helper(pointsLeft - 1, i + 1, array, sb, results);
                sb.delete(sb.length() - (i + 2 - startIdx), sb.length()); //delete [start, end)
            }
        }
    }
    
    private static String getValidSegment(char[] array, int left, int right) { // 如果用int返回值则有一个bug，如果number是000，那么只会append 0而不是000. 还是应该用String输出。
        // left and right are inclusive bound of a seg
        // 通过int来判断范围
        // 通过stringbuilder来获得seg（规避 000 --> 0 这个bug，这个bug会导致后面删除的时候多删一些字符，然后数组越界)
        if (right >= array.length) {
            return null;
        }
        if (left > right || right - left >= 3) { // only three digits is allowed
            return null;
        }
        if (left + 1 <= right && array[left] == '0') { // cannot start with '0' if there are more than two digits.
            return null;
        }
        int pos = 1;
        int seg = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = right; i >= left; i--) {
            sb.append(array[i]);
            seg += pos * (array[i] - '0');
            pos *= 10;
        }
        if (seg < 0 || seg > 255) {
            return null;
        }
        return sb.reverse().toString(); //从右向左添加的，记得反过来一下
    }
}
