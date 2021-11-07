package com.interview.bloomberg;

import java.util.*;

public class SubarraySum {
    // 一个需要用到 map的value的例子：
    // num        1 -1  0
    // presum  0  1  0  0
    // 如果只储存到set   set{0,1}      counter = 0+0+1+1   --> 错误
    // 如果储存map    map{0:1,  1:1}  counter = 0+0+1 然后更新
    //               map{0:2,  1:1}  counter = 0+0+1+2  即在preSum的最后一个零那里，可以有两种开头满足，即{1,-1,0} 和 {0}   --> 正确
    public int subarraySum(int[] nums, int k) {
        // corner case: ...
        
        int counter = 0;
        // preSum[j] - preSum[i - 1] == k    -->    [i, j] 的和为k --> 注意这里是“子数组”
        // preSum[j] - k == preSum[i - 1]    --> 找以前的value为 preSum[j] - k 的地方，那个地方就是我们要的另一半
        // 在preSum里面做一个"two sum"即可
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        
        Map<Integer, Integer> map = new HashMap<>(); // k + preSum[i]  to  counts of this value
        for (int i = 0; i <= nums.length; i++) {
            // check
            if (map.containsKey(preSum[i] - k)) { // value is starting index
                counter += map.get(preSum[i] - k);  // 同一个结尾i，有不同的开头满足加起来等于k。所以要记录count
            }
            // add
            map.put(preSum[i], map.getOrDefault(preSum[i], 0) + 1);
        }
        return counter;
    }
}
