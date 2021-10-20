package com.interview.walmartlabs.leetcode;

import java.util.*;

public class ThreeSum_HashSetDedup {
    /*
    sol1:  排序去重+三指针方案（方便地跳过重复值，以避免重复）
            O(n^2) 相当于每个外层循环调用了一个two-sum-II
            
            去重：在循环变量i++以后，用while循环跳过 nums[i-1]==nums[i]
                如果是for循环这种后++的，就nums[i]==nums[i+1]
                内外层的左边都要去重，第三个hi就不需要去重了。
    
    
    sol2:  排序去重+HashMap方案 (这里可以用set，因为只需要字面值)
            O(n^2) 相当于每个外层循环调用了一个two-sum-one-pass
            HashSet在内侧，只是用来记忆都有哪些值的。依然要对循环变量内外都去重
    
    sol3: HashSet去重+HashMap方案 (这里可以用set，因为只需要字面值)
            O(n^2)
            关键点：
                - 不排序
                - HashSet_1 负责跳过外层循环查过的value （额外的）
                - HashSet_2 负责去重并记录List<Integer>
                    三元组（三元组需要排序，否则无法去重）
                        Collections.sort(triple);
                    相当于是以前的那个List
                - HashSet_3 在内循环负责每个two-sum
                    相当于是以前的那个HashSet
                - new ArrayList(set_1); 可以获得结果，因为initialization的时候可以丢入一个Collection
    */
    // 这是第三个方法，用一个HashSet来去重。不过时间开销依然是O(n^2)
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>(); // 去重结果
        Set<Integer> dups = new HashSet<>(); // 跳过见过的外层循环值，降低实际开销
        for (int i = 0; i < nums.length; i++) {
            if (dups.contains(nums[i])) {
                continue;
            }
            // two-sum start
            Set<Integer> seen = new HashSet<>();
            for (int j = i + 1; j < nums.length; j++) {
                int compliment = -nums[i] - nums[j];
                if (seen.contains(compliment)) {
                    // find an answer
                    List<Integer> triple = Arrays.asList(nums[i], nums[j], compliment);
                    Collections.sort(triple);
                    res.add(triple);
                }
                // record current number
                seen.add(nums[j]);
            }
            
            // two-sum end
            dups.add(nums[i]);
        }
        
        return new ArrayList<>(res);
    }
}
