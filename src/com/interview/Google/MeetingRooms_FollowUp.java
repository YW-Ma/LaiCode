package com.interview.Google;

import java.util.ArrayList;
import java.util.List;

public class MeetingRooms_FollowUp {
    public static void main(String[] args) {
        TrackRoom s = new TrackRoom();
        String[] names = {"Alice", "Bob", "Cat", "Dog"};
        int[][] intervals = {{1,6}, {1,10}, {5,12}, {15,20}};
        s.minMeetingRooms(names, intervals);
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
class EventName {
    int time;
    String type;
    String name; // user name
    
    public EventName(int time, String type, String name) {
        this.time = time;
        this.type = type;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "{" + time + ", " + name + ", " + type + "}\n";
    }
}
class TrackRoom {
    // 注意事项：
    // if time is same, end must before start   [1,5] [5,10] can only use one meeting room.
    // 排序，event的时间相同的时候，start排在end的后面。
    
    public int minMeetingRooms(String[] names, int[][] intervals) {
        List<EventName> events = new ArrayList<>();
        // add start, end events chronologically
        for (int i = 0; i < intervals.length; i++) {
            int[] entry = intervals[i];
            String name = names[i];
            events.add(new EventName(entry[0], "start", name));
            events.add(new EventName(entry[1], "end", name));
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
        
        System.out.println(events);
        
        int usedRoom = 0;
        int maxUsedRoom = 0;
        for (EventName e : events) {
            if (e.type == "end") {
                usedRoom--;
            } else {
                usedRoom++;
            }
            maxUsedRoom = Math.max(usedRoom, maxUsedRoom);
        }
        // 新的思路是：
        //           多个同类事件在同时发生，需要合并
        //           除了空变非空不打印，其他event都要打印
        //           否则，那么每次event都输出一个
        return maxUsedRoom;
    }
}
