package com.interview.Google;

import java.util.HashSet;
import java.util.Set;

public class HotelRoom {
    // 0-9 一共10层的酒店，每层有A-Z 26个房间
    // + 一个房间，代表预约了这个房间
    // - 一个房间，代表释放这个房间的预约
    // 找到哪个房间被预约的次数最多

    // 输入是指令序列，输出的被预约次数最多的房间 OR 有多少个room至少被book过一次。
    // Input: ["+1A", "+3E", "-1A", "+4F", "+1A", "-3E"]
    // Output: "1A"
    // Explanation: 1A as it has been booked 2 times.

    public static int NumOfRoomBeenBooked(String[] inputs) {
        // 我做这个至少被book过一次的版本，用HashSet，最后求size就行了
        Set<String> set = new HashSet<>();
        for (String cmd : inputs) {
            if (cmd.charAt(0) == '-') {
                continue;
            }
            set.add(cmd.substring(1));
        }
        return set.size();
    }


    public static void main(String[] args) {
        String[] inputs = new String[]{"+1A", "+3E", "-1A", "+4F", "+1A", "-3E"};
        System.out.println(NumOfRoomBeenBooked(inputs));
    }
}
