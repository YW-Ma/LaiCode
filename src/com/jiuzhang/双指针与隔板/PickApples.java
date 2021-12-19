package com.jiuzhang.双指针与隔板;

public class PickApples {
    public static void main(String[] args) {
        int[] A = new int[]{4,5,4,5,6,4,7,10,9,1};
        int k = 1;
        int l = 4;
        int max = PickApples(A, k, l);
        System.out.println(max);
        // 有数组A，一个人拿连续K个元素、一个人拿连续L个元素，要求不能overlap，求最大的所有被拿走的元素的和
    }

    // 应该小写method首字母，但是lintcode函数签名大写所以我也大写。
    public static int PickApples(int[] A, int K, int L) {
        // 一个人拿K个连续，一个人拿L个连续，互相不overlap，求最大
        // 如果做不到就返回-1

        // Solution: 挡板法
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            int leftKMax = findMax(A, K, 0, i);
            int rightLMax = findMax(A, L, i, A.length);

            int leftLMax = findMax(A, L, 0, i);
            int rightKMax = findMax(A, K, i, A.length);

            if (leftKMax != -1 && rightLMax != -1) {
                max = Math.max(max, leftKMax + rightLMax);
            }
            if (leftLMax != -1 && rightKMax != -1) {
                max = Math.max(max, leftLMax + rightKMax);
            }
            System.out.println(max);
        }
        // if never updated: then no valid case
        if (max == Integer.MIN_VALUE) {
            return -1;
        }
        return max;

    }

    private static int findMax(int[] A, int k, int left, int right) {
        // in [left, right) add k consecutive elements, return the maxApples

        // corner case:  there are (right - left) elements in this part
        if (k > right - left) {
            return -1;
        }

        // Solution: 滑动窗口
        // initial situation:
        int apples = 0;
        for (int i = 0; i < k; i++) {
            apples += A[i + left];
        }

        // moving situations:   [left, left + k)
        int maxApples = apples;
        int l = left;
        int r = left + k;
        while (r < right) {
            apples += A[r];
            apples -= A[l];
            maxApples = Math.max(maxApples, apples);
            r++;
            l++;
        }
        return maxApples;
    }
}
