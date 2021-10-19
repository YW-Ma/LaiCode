package com.interview.walmartlabs.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        // sort all intervals based on starting time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // smaller start time -> higher priority
        // create a List of int[], put first one in it
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        // for each new interval, merge if start <= end, add if else.
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= list.get(list.size() - 1)[1]) {
                list.get(list.size() - 1)[1] = Math.max(intervals[i][1], list.get(list.size() - 1)[1]);
            } else {
                list.add(intervals[i]);
            }
        }
        
        // return merged intervals
        return list.toArray(new int[list.size()][]);
    }
    // O(nlogn) due to sorting.
}
