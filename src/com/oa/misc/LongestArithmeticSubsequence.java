package com.oa.misc;

import java.util.*;

public class LongestArithmeticSubsequence {
    public static void main(String[] args) {
        int res = longestArithmeticSubsequence(new int[]{3, 5, 1, 3, 5, 7});
        System.out.println(res);
    }
    
    // 3 5 1 3 5     7
    // 假设已经考虑过了 3 5 1 3 5 现在来了7
    // 那么DP里面，diff2对应的是
    //   val 3 5 1 3 5
    // dif-2 1 2 1 2 3,  可以看出来两个5的dif-2是不一样的，在7中利用的方法是用j扫描 取dp[j][2] + 1的最大的那个即可。
    //
    // 所以DP是在不同外层for loop之间传递了信息（把之前轮中5的2、3这样的信息，传递给了7，让7能得知自己应该是4）
    public static int longestArithmeticSubsequence(int[] array) {
        // corner case:
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return 1;
        }
        // 不想用HashMap的话，可以用下面这个。预判一下diff的取值范围。
//        int max = Integer.MIN_VALUE;
//        int min = Integer.MAX_VALUE;
//        for (int i : array) {
//            if (max < i) {
//                max = i;
//            }
//            if (min > i) {
//                min = i;
//            }
//        }
//        int range = max - min; // to make sure no negative value will be generated.
        
        List<Map<Integer, Integer>> dp = new ArrayList<>(); // DP, 由于diff不好知道有多少，所以改用ArrayList了
        // dp base rule: 以第一个元素结尾的情况下，只有一个空hashMap，没有任何diff key
        dp.add(new HashMap<>());
        
        // dp induction rule: 以元素i结尾的情况下，遇到了一个diff，就试图继承这个j的值（没有查到则是2），然后用这个值来更新自己的map。
        // dp[i][diff] = max(dp[i][diff], dp[j][diff] + 1),   if j has key:diff
        //             = max(dp[i][diff], 2),                 if j doesn't have key:diff    (arr[j] - arr[i] = diff)
        int globalMax = 2;
        for (int i = 1; i < array.length; i++) {
            Map<Integer, Integer> map = new HashMap<>(); // 存储以当前i元素为结尾的，各个diff的数列的长度。 DP体现在继承之前元素结尾各diff的长度。
            for (int j = 0; j < i; j++) {
                int diff = array[i] - array[j];
                int newLength = dp.get(j).containsKey(diff) ? dp.get(j).get(diff) + 1 : 2; // 试探一下如果继承，length是多少。
                globalMax = Math.max(globalMax, newLength); // globalMax永远要最大的那个
                if (!map.containsKey(diff)) { // 本轮中还没遇到过这个diff: 果断继承
                    map.put(diff, newLength);
                } else { // 本轮中碰到过：选大的，不一定继承
                    map.put(diff, Math.max(map.get(diff), newLength));
                }
            }
            dp.add(map);
        }
        return globalMax;
    }
}
