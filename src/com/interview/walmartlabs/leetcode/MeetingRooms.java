package com.interview.walmartlabs.leetcode;

import java.util.*;

public class MeetingRooms {
    public int minMeetingRooms(int[][] intervals) {
        // sort all intervals according to start time
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        // maintain a minHeap according to end time (peek get the earliest ending meeting)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        
        // phase 1: put first interval into PriorityQueue
        minHeap.offer(intervals[0]);
        // phase 2: for each interval, compare the starting time with the ending time of the root of minHeap, if can put into it, then replace the content(poll + offer), otherwise (offer) a new meeting root
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= minHeap.peek()[1]) {
                minHeap.poll();
            }
            minHeap.offer(intervals[i]);
        }
        return minHeap.size();
    }
    // TC: O(NlogN), N is the num of meetings.
}
