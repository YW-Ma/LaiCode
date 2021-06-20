package com.study.algorithms.class13_DP_3.重听.ConsecutiveOnes;

public class Readme_利用母题设计M上下左右 {
    /* 其实相当于是一个特别的prefix：连续1的个数 num of consecutive 1s*/

    // 先看一下两个题目：

    // [1] 问被 1 包围的最大正方形
    // 子问题存下来没用
    // 应该存M上下左右


    // [2] 随手撒一把火柴
    // Assumption：（固定在2D Grid中）
    // 1. 火柴都是首尾相接（没有T型相接）
    // 2. 角度要么90度，要么一致（grid）
    //
    // 题目思考：
    // 相当于是 M左、M右、M上、M下类的举一反三，存储的也不是子问题
    // （另一个举一反三是问被 1 包围的最大正方形 -- 它的DP中，存下来的不是子问题解，而是这四个M （即原题最长连续1的数量）
}
