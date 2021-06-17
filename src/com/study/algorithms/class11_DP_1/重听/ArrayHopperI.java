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

    // [1,0,0,100,1]
    //  T T F F   F
    //        ^ 这里是F，就算可以跳100步，也因为走不到这里，而没有用。

    // base rule: M[0] = T
    // induction rule: M[i] = true,  if there exists a j where j < i and (M[j] == true && A[j] >= i)
    //                        false, otherwise.

    // code其实就是for循环i一个一个检查能否跳到结尾。因为，如上图，下一次尝试的，肯定是下一个index。
    // Time: O(n^2)
    // Space: O(n)
    public boolean canJump1(int[] array) {
        boolean[] can = new boolean[array.length]; // can[i] --> from stat can jump to i or not
        // base rule:
        can[0] = true;
        // induction rule:
        for (int i = 0; i < array.length; i++) {
            if (!can[i]) {                                  // 【1】当前是true，才能跳，否则找下一个true的
                continue;
            }
            for (int j = 1; j <= array[i]; j++) {
                if (i + j >= array.length) {
                    return true;
                }
                can[i + j] = true;
            }
        }
        return can[array.length - 1];
    }


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
    public boolean canJump2(int[] array) {
        boolean[] can = new boolean[array.length]; // can[i] --> can jump to end or not
        // base rule:
        can[array.length - 1] = true;
        // induction rule:
        for (int i = array.length - 2; i >= 0; i--) {
            for (int j = array[i]; j >= 1; j--) {
                if ((i + j >= array.length) || can[i + j]) {    // 从我这里开始往后摸，摸到一个true的，就说明我也可以跳到结尾了。
                    can[i] = true;
                    break;
                }
            }
        }
        return can[0]; // 返回开头能不能到结尾
    }
}
