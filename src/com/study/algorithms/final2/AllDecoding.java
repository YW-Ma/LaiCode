package com.study.algorithms.final2;

public class AllDecoding {
    // "1121"
    // ->
    // [ "AABA", 1 1 2 1
    //  "AAU",   1 1 21
    //  "ALA",   1 12 1
    //  "KBA",   11 2 1
    //  "KU"     11 21
    // ]
    //  --> DP?
    //  --> DFS try to cut in each position
    
    // 一开始的思路：（未剪枝，感觉很棘手）
    //                              1121()                             0
    //            /                      |            \
    //      1|121(A)                11|21(KU)*      112|1()             1   2   3
    //      /          \               /
    //  1|21(AAU)*   12|1(ALA)*     2|1 (KBA)*                          2   3   3
    //     /
    //   2|1(AABA)*                                                     3   3
    
    
    // 晚上交流后的思路：
    // 加上剪枝，只需要每次看1digit或者2digit，然后判断是否合法：
    
    /*   每层只表达当前层的决策
    *                  123
    *                 /  \
    *               1     12
    *              /\      /  \
    *            2   23    3   3_ (越界） 即branch2的越界检查
    *         / \     *    *
    *        3  3_ (越界)
    *        *
    *
    * 综上，输出 1\2\3,  1\23,  12\3 三个情况
    * */
    
    /*
    *                   1001
    *                   /   \
    *                   1   0 (非法) 即各branch的数值检查
    *                  / \
    *                 0   00 (都非法)
    *
    *               因此没有输出
    * */
    
    // print all decodes:
    public void allDecodes(String str) {
        StringBuilder sb = new StringBuilder(); // holding results;
        helper(str, 0, sb);
    }
    
    private void helper(String str, int index, StringBuilder sb) { // 入参index标记了下一个要被decode的digit的位置。
        if (index >= str.length()) { // 下一个字母 == length. 代表已经全部decode完成了。（下标从0开始）
            System.out.println(sb.toString());
            return;
        }
        
        // recursion rule:
        int num = atoi(str.charAt(index));          // 分支1，decode index.  下一个要被decode是 index+1
        if (0 < num && num <= 9) {
            sb.append(itoa(num));
            helper(str, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (index + 1 < str.length()) { // 分支2要被保护起来，因为 index + 1是可能越界的。
            num = atoi(str.charAt(index)) * 10 + atoi(str.charAt(index + 1));  // 分支2，decode index和index+1.   下一个要被decode的是index+2
            if (10 <= num && num <= 26) {
                sb.append(itoa(num));
                helper(str, index + 2, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    
    private int atoi(char c) {
        return c - '0';
    }
    private char itoa(int i) {
        return (char)(i + ('A' - 1));
    }
}
