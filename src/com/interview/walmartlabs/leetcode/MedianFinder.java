package com.interview.walmartlabs.leetcode;

import java.util.*;

public class MedianFinder {
    List<Integer> list;

    public MedianFinder() {
        this.list = new ArrayList<>();
    }

    public void addNum(int num) {
        // binary search find position
        int left = 0;
        int right = list.size();
        // find the first one larger than num.
        // 0 2 4 6 8    . find 3
        //     l
        //     r
        //   m
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) >= num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // left = right = smallest larger.
        list.add(left, num);
    }

    public double findMedian() {
        // get median
        int size = list.size();
        if (size % 2 == 1) {
            return list.get(size / 2);
        } else {
            return (double)(list.get(size / 2) + list.get((size - 1) / 2)) / 2;
        }
    }
}
