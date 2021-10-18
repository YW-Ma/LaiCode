package com.interview.walmartlabs.leetcode;

import java.util.*;

public class SlidingWindowMaximum {
    // 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
    // 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    // 返回每个滑动窗口中的最大值。
    
    // 1. maxHeap, O(nlogn), 直接加入maxHeap。然后获得maxHeap的第一个有效最大值。最差情况会把n个都插入priority queue，所以O(nlogn)
    // 2. Deque 单调队列，O(n), 放入的时候注意顺序，
    //                          1. deque的队尾保证是最大的，每次和队尾比较。
    //                          2. 由于移动了一下，所以先把队首弹出去
    //                          3. 如果队尾比我小，那么就把我加进去（我是最大的了）否则弹出队尾比较下一个队尾 -- 如果空了就只能把我加上去了
    //                          "如果后浪比你年轻(idx新)还比你强(val大)，那你就可以爬了，这就是这个操蛋的世界" -- leetcode评论区
    // 3. 预处理（前后缀与每k个的块）
    //      为了求出 nums[i] 到 nums[i+k-1] 的最大值，有以下两种情况
    //      1. i是k的倍数：  取出这个分组的最大值即可
    //      2. i不是k的倍数：max{上个分组的后缀最大值，这个分组的前缀最大值}
    //      所以需要准备：每个分组的最大值，前缀的最大值，后缀的最大值
    // 但是并没有必要，因为TC还是O(n)的，反而space变高了，从O(k) 变成O(n)了
    
    public int[] maxSlidingWindow_naive(int[] nums, int k) {
        int len = nums.length;
        // num 大到小排列，如果num相同，则按照index从大到小排列。 这样peek()获得的值最可能作为当前最大值。（index新则不容易被淘汰）
        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
        // 先放进去k个
        for (int i = 0; i < k; i++) {
            maxHeap.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[len - k + 1];
        // 记录前k个的最大值
        ans[0] = maxHeap.peek()[0];
        // 获得下一个，直接加入maxHeap。然后获得maxHeap的第一个有效最大值。
        for (int i = k; i < len; i++) {
            maxHeap.offer(new int[]{nums[i], i});
            while (maxHeap.peek()[1] <= i - k) {
                maxHeap.poll();
            }
            ans[i - k + 1] = maxHeap.peek()[0];
        }
        return ans;
    }
    
    // 不再需要 (val, index)二元组，因为我们这次手动排序，只需要存储index随时可以获取val。
    public int[] maxSlidingWindow(int[] nums, int k) {
        // size k deque
        Deque<Integer> deque = new ArrayDeque<>(k);
        // 按顺序放进去前k个
        deque.offerLast(0);
        for (int i = 1; i < k; i++) {
            // 弹掉无效队尾，然后加入自己 -- 要用while，因为可能会有多个无效队尾
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        // 记录最大值
        int[] ans = new int[nums.length - k + 1];
        ans[0] = nums[deque.peekFirst()];
        
        for (int i = k; i < nums.length; i++) {
            // 获得下一个，弹掉无效的队尾（比自己还小），然后把自己加进去
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 弹掉无效的队首，然后队首就是最大值
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
