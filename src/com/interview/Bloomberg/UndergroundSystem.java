package com.interview.bloomberg;
// 1. checkIn(int id, string stationName, int t)
//  编号为 id 的乘客在 t 时刻进入地铁站 stationName 。
//  一个乘客在同一时间只能在一个地铁站进入或者离开。

//2. checkOut(int id, string stationName, int t)
//  编号为 id 的乘客在 t 时刻离开地铁站 stationName 。

//3. getAverageTime(string startStation, string endStation)
//  返回从地铁站 startStation 到地铁站 endStation 的平均花费时间。
//  平均时间计算的行程包括当前为止所有从 startStation 直接到达 endStation 的行程。
//  调用 getAverageTime 时，询问的路线至少包含一趟行程。

//链接：https://leetcode-cn.com/problems/design-underground-system

// 相当于是OOD了

import java.util.HashMap;
import java.util.Map;

class Record {
    public int id;
    public String stationName;
    public int t; // time
    public Record(int id, String stationName, int t) {
        this.id = id;
        this.stationName = stationName;
        this.t = t;
    }
}

public class UndergroundSystem {
    private Map<Integer, Record> customers;
    private Map<String, int[]> trips; // "stationA,stationB"  --> [total cost, total count]
    public UndergroundSystem() {
        customers = new HashMap<>();
        trips = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) { // O(1)   O(0.75 * id length)
        // register that  id, enter station Name, at t
        customers.put(id, new Record(id, stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {  // O(1)
        // register that station a -> station b cost t2-t1.  remove id from customer map
        Record enterRecord = customers.get(id);
        String tripKey = enterRecord.stationName + "," + stationName;
        int[] trip = trips.getOrDefault(tripKey, new int[]{0, 0});
        trip[0] += t - enterRecord.t;
        trip[1] += 1;
        trips.put(tripKey, trip);
        customers.remove(id);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        // cost sum / count, notice: change to float number
        String tripKey = startStation + "," + endStation;
        int[] trip = trips.get(tripKey);
        if (trip == null) {
            return 0;
        }
        return (double)(trip[0]) / trip[1];
    }
}
