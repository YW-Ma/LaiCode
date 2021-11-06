package com.interview.walmartlabs;

public class FindMedianOfSortedArrays {
    public float findMedianSortedArrays(int[] nums1, int[] nums2) {
        // corner case:
        if (nums1 == null && nums2 == null) {
            throw new IllegalArgumentException();
        }
        if (nums1 == null || nums2 == null) {
            return nums1 == null ? (median(nums2)) : (median(nums1));
        }
        // 拆成两个题目：
        // 先看怎么找"第k小的数"，然后再看"中位数是第几小的数"
        // function: find the k-th element from the sorted array a and b: 已经写出来了是findKthElement
        
        // 下面看怎么找"中位数"
        int m = nums1.length;
        int n = nums2.length;
        // if odd: k = (m + n + 2) / 2;
        // if even: left_k = (m + n + 1) / 2;  right_k = (m + n + 2) / 2;
        // return findKthElement(nums1, 0, m - 1, nums2, 0, n - 1, k); // find k-th element.
        return -1;
    }
    
    // 拆出的子问题：find kth smallest element in two sorted array.
    private float findKthElement(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2, int k) {
        // 语义：在nums1[s1:e1], nums2[s2:e2]中寻找第k大的元素返回
        // 而且要保证len1 <= len2 这样方便后面的处理（有数组空了一定是nums1）
        int len1 = e1 - s1 + 1;
        int len2 = e2 - s2 + 1;
        if (len1 > len2) { //swap
            return findKthElement(nums2, s2, e2, nums1, s1, e1, k);
        }
        // 现在保证len1 < len2
        if (len1 == 0) {
            return nums2[s2 + (k - 1)];
        }
        // 如果k是1，就选小的那个
        if (k == 1) {
            return Math.min(nums1[s1], nums2[s2]);
        }
        // recursion:
        // 1. 求出i和j中，本轮需要对比的元素，即 第k/2个 元素（如果越界就取 第len个 ）
        //                                 对应下标 k/2-1           len-1
        // 2. 两个都是前k/2个，合起来最多也是只有大的那个是第k个，所以小的那个不可能是第k个，可以直接从搜索空间排除掉
        int i = s1 + Math.min(len1, k / 2) - 1;
        int j = s2 + Math.min(len2, k / 2) - 1;
        if (nums1[i] < nums2[j]) {
            return findKthElement(nums1, i + 1, e1, nums2, s2, e2, k - (Math.min(len1, k / 2)));
        } else {
            return findKthElement(nums1, s1, e1, nums2, j + 1, e2, k - (Math.min(len2, k / 2)));
        }
    }
    
    private float median(int[] arr) {
        if (arr.length == 0) {
            throw new IllegalArgumentException();
        }
        if (arr.length % 2 == 0) {
            return (arr[arr.length / 2] + arr[arr.length / 2 + 1]) / 2;
        } else {
            return arr[arr.length / 2];
        }
    }
}
