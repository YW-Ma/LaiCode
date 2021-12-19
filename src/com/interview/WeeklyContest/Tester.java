package com.interview.WeeklyContest;

import java.util.List;

public class Tester {
    public static void main(String[] args) {
        TargetIndices t = new TargetIndices();
        List<Integer> res = t.targetIndices(new int[]{0, 1,2,2,3,5}, 2);
        System.out.println(res);
    }
}
