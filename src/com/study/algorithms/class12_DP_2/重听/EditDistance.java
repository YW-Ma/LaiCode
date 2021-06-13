package com.study.algorithms.class12_DP_2.重听;

public class EditDistance {
    // 递归：
    //      如何保证问题规模在缩小
    //      问题的大小由长度确定，只要（insert target首字母），（delete 不相同的首字母），（change 首字母为target首字母）
    //      就可以保证子问题更小（串更短了）

    // DP：
    // 发现递归有很多子问题有重复
    // 设置M和M的填写方式：
    // (1) 由于一个问题由两个变量唯一确定（即source和target两个字符串），所以需要一个 2D Array 来存储子问题结果
    // (2) 由于编辑距离每次操作开头，所以子问题其实是靠近尾巴的那个（当一个string为空的时候）。即base case应填写2D Array的最右侧和最底层。

    // 依然是三个options，对应不同的右下方子问题。
    // M[i][j] represents the minimum number of operations to convert the letters from after i of s1, and after j of s2.

    // 不要参考课件的M矩阵定义，它是前i、j个字母。和recursion差别比较大，不方便理解。
}
