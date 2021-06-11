package com.study.algorithms.class11_DP_1.重听;

public class ArrayHopperI {
  /*
    Given an array of non-negative integers, you are initially positioned at the first index of the array.
    Each element in the array represents your  maximum  jump length at that position.
    【Determine if you are able to reach the last index.】 判断能不能跳到最后一个元素上。
  * */

    // Solution 1 （不推荐，为啥）
    // [2,3,1,1,4]
    //  T
    //  ^
    //    T T
    //    ^
    //      T T T  --> 到达终点了，但是不停下来，继续
    //      ^
    //        T
    //        ^
    //          T
    //          ^
    //             超出范围，停下来

    // base rule: M[0] = T
    // induction rule: M[i] = true,  if there exists a j where j < i and (M[j] == true && A[j] >= i)
    //                        false, otherwise.

    // code其实就是for循环i一个一个检查能否跳到结尾。因为，如上图，下一次尝试的，肯定是下一个index。
    // Time: O(n^2)
    // Space: O(n)


    // Solution 2 （推荐：原因是可以进一步求最小步数）
    // 也可以反着跳，从最后一个开始，看能不能找到第一个。依然是O(n^2)
    // [2,3,1,1,4]
    //          ^
    //    T   T
    //        ^
    //    T T
    //      ^
    //  T T       --> 触达起点了，但是不停下来
    //    ^
    //  T
    //  ^
    // 返回

    // code其实就是从后往前一个一个尝试
    // 找之前可达我这里的点
    // 最后看看开头是不是可达我这里的点。

}
