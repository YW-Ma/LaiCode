package com.oa.misc;

/*
* change subarray to subsequence.
* */

import java.util.*;

public class EvenSubseq {
    public static void main(String[] args) {
        evenSubseq(new int[]{1,2,3,4}, 1);
    }


    public static int evenSubseq(int[] numbers, int k) { // 不需要考虑有没有duplicate nums，HashSet自动去重。
        // HashSet
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        List<Integer> cur = new ArrayList<>();
        helper(numbers, 0, 0, k, cur, set);
        return set.size();
    }


    /*
    * Parameters:
        * num: input array
        * idx: the index of element to be evaluated in the current layer
        * oddCounter & oddLimit: counter for odd nums in List cur and the maximum num of odd allowed
        * cur: current subarray
        * set: store all valid distinct subarray
    * */
    public static void helper(int[] num, int idx, int oddCounter, int oddLimit, List<Integer> cur, Set<List<Integer>> set) {
        // cut invalid branch
        if (oddCounter > oddLimit) {        // oddCounter > oddLimit时，禁止add to set 或者 产生新的branch。 直接返回。
            return;
        }
        // base case
        if (idx == num.length) {            // oddCounter == oddLimit时，依然可以在后面添加even number。因此不需要返回，也就不写在base case里面。
            if (cur.size() != 0) {
                set.add(new ArrayList<>(cur));
            }
            return;
        }
        // recursion rule
        // branch 1
        cur.add(num[idx]);
        if (num[idx] % 2 != 0) { // odd
            helper(num, idx + 1, oddCounter + 1, oddLimit, cur, set);
        } else { // even
            helper(num, idx + 1, oddCounter, oddLimit, cur, set);
        }
        cur.remove(cur.size() - 1);

        // branch 2
        helper(num, idx + 1, oddCounter, oddLimit, cur, set);
    }
}
