package com.interview.walmartlabs;

public class FirstMissingPositive {
    public static void main(String[] args) {

    }

    public static int firstMissingPositive(int[] nums) {
        boolean[] exists = new boolean[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] <= exists.length) {
                exists[nums[i] - 1] = true;
            }
        }

        for (int i = 0; i < exists.length; i++) {
            if (!exists[i]) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
