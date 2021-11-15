package com.interview.Bloomberg;

public class InterleavingString {
    // 先尝试了Brute Force
    public boolean isInterleave_DFS(String s1, String s2, String s3) {
        // // brute force - wrong
        // int r1 = 0;
        // int r2 = 0;
        // for (int i = 0; i < s3.length(); i++) { // 这个代码是有问题的，举例来说， cc 和 ca  可以产生 ccac, 但是这个程序是认为产生不了的。
        //     if (r1 < s1.length() && s1.charAt(r1) == s3.charAt(i)) {
        //         r1++;
        //     } else if (r2 < s2.length() && s2.charAt(r2) == s3.charAt(i)) {
        //         r2++;
        //     } else {
        //         break;
        //     }
        // }
        // if (r1 == s1.length() && r2 == s2.length()) {
        //     return true;
        // } else {
        //     return false;
        // }


        // Brute force DFS: 既然每次既可能走s1，也可能走s2，那就都尝试一下
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        char[] a3 = s3.toCharArray();
        // corner case:
        if (a1.length + a2.length != a3.length) {
            return false;
        }
        return dfs(a1, a2, a3, 0, 0, 0);
    }

    private boolean dfs(char[] a1, char[] a2, char[] a3, int i, int j, int k) {
        // base case:
        if (k == a3.length) {
            return true;
        }
        // recursion rule:
        // 只有能走的时候，才会进入这个分支返回。 如果走不通就另一个选择。
        if (i < a1.length && a1[i] == a3[k] && dfs(a1, a2, a3, i + 1, j, k + 1)) {
            return true;
        }
        if (j < a2.length && a2[j] == a3[k] && dfs(a1, a2, a3, i, j + 1, k + 1)) {
            return true;
        }
        return false;
    }


    // DFS爆栈了，需要优化：要么改成迭代，要么改成DP
    // 可以用DP，因为重复的地方是典型的 i+1, j+1 的两种可能性（先i+1,再j+1和反过来，被分配到了两个分支上）
    // 合并到DP[i][j] 代表[i,) [j,) 可以叉成结果。
    // DP[i][j] = i匹配 & DP[i+1][j]  ||  j匹配 & DP[i][j+1]
    // 每次参考的是右、下。
    // 只有最后一个字符的时候是base rule。

    // 转化一下，如果DFS是从后面开始判断，那么DP可以从头开始，
    // DP[i][j]定义为前i个和前j个字符是否能凑成s3的前 i+j 个字符
    // 这样可以取值 0 到 length
    public boolean isInterleave(String s1, String s2, String s3) {
        char[] a1 = s1.toCharArray();
        char[] a2 = s2.toCharArray();
        char[] a3 = s3.toCharArray();
        // corner case:
        if (a1.length + a2.length != a3.length) {
            return false;
        }
        // DP:
        boolean[][] dp = new boolean[a1.length + 1][a2.length + 1];
        // base rule:
        // 只有一个字符的时候、没有字符的时候。

        // induction rule:
        //             1中第i个字母，或2中第j个字母匹配，并且在加入这个字母之前的那个状态，是dp中的合法状态。 既然要凑第 i+j-1 个，那么s1和s2要么出 i/j, 要么出i/j - 1个。
        // dp[i][j] = 相当于上文 j+1, k+1 分支   (a1[i-1] == a3[i+j-1])  && 并且 a1前i-1个 和 a2前j个    可以凑成a3 dp[i-1][j]   ||
        //             ^  不过这里反过来了，是从前向后构建，而不是拆解。 所以相当于(i,j,k) 依赖于 (i-1, j, k-1) 分支。
        //            相当于上文 i+1, k+1 分支   (a2[j-1] == a3[i+j-1])  && 并且 a1前i个   和 a2前j-1个  可以凑成a3 dp[i][j-1]
        //             ^  不过这里反过来了，是从前向后构建，而不是拆解。 所以相当于(i,j,k) 依赖于 (i, j-1, k-1) 分支。
        // 依赖i-1和j-1
        //

        // O(n*m)
        for (int i = 0; i <= a1.length; i++) {
            for (int j = 0; j <= a2.length; j++) { // 不能直接从1开始，那样会漏掉第一行和第一列。导致后续依赖的都是false，以及漏掉一个字符串为空的情况
                if (i == 0 && j == 0) {
                    dp[0][0] = true; // base rule
                }
                if (i >= 1 && a1[i - 1] == a3[i + j - 1] && dp[i - 1][j]) { // 会负责j=0，即第一列的编写。 含义是只采用s1里面的字符。作为启动项，dp[0][1] 和 dp[1][0]至少有一个要是true才行
                    dp[i][j] = true;
                }
                if (j >= 1 && a2[j - 1] == a3[i + j - 1] && dp[i][j - 1]) {
                    dp[i][j] = true; // 如果a2的第j个字母匹配，并且a1前i个和a2前j-1个可以凑成a3，那么就可以凑成a3
                }
            }
        }

        // 返回前size个能否凑成
        return dp[a1.length][a2.length];
    }
}
