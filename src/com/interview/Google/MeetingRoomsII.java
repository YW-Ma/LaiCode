package com.interview.Google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingRoomsII {
    //    https://leetcode.com/problems/meeting-rooms-ii/solution/
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] intervals = {{1,6}, {1,10}, {5,12}, {15,20}};
        s.minMeetingRooms(intervals);
    }
}
/*
1       5                        15
A----------|
B------------------|
        C---------------|
                                  D----------|
           6      10   12                   20
	       j
* */
class Event {
    int time;
    String type;
    
    public Event(int time, String type) {
        this.time = time;
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "Event{" +
                "time=" + time +
                ", type='" + type + '\'' +
                '}';
    }
}
class Solution {
    // 注意事项：
    // if time is same, end must before start   [1,5] [5,10] can only use one meeting room.
    // 排序，event的时间相同的时候，start排在end的后面。
    
    public int minMeetingRooms(int[][] intervals) {
        List<Event> events = new ArrayList<>();
        // add start, end events chronologically
        for (int[] entry : intervals) {
            events.add(new Event(entry[0], "start"));
            events.add(new Event(entry[1], "end"));
        }
        events.sort((a, b) -> {
            if (a.time < b.time) {
                return -1;
            } else if (a.time > b.time) {
                return 1;
            } else {
                if (a.type == "end") {
                    return -1;
                }
                return 1;
            }
        });
        
        int usedRoom = 0;
        int maxUsedRoom = 0;
        for (Event e : events) {
            if (e.type == "end") {
                usedRoom--;
            } else {
                usedRoom++;
            }
            maxUsedRoom = Math.max(usedRoom, maxUsedRoom);
        }
        return maxUsedRoom;
    }
}
