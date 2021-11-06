package com.interview.bloomberg;

import java.util.*;

public class TopKFrequent {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{3,0,1,0}, 1)));
    }
    public static int[] topKFrequent(int[] nums, int k) {
        // Step 1: count and get a freqMap --> 然后直接在entrySet上操作（用来minHeap） 或者 存到List<int[]> values（用来quickSelect）
        // Step 2: quick select O(N)avg O(N^2)worst, or minHeap O(Nlogk)
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int v : nums) {
            freqMap.put(v, freqMap.getOrDefault(v, 0) + 1);
        }
        // Quick Select
        List<int[]> values = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            values.add(new int[]{entry.getKey(), entry.getValue()});
        }
        quickSelect(values, 0, values.size() - 1, k - 1); // k is 1-based. so I minus 1 to change it to index, which is 0-based.
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = values.get(i)[0]; // .get(i) will get a tuple: [key, value], we need key here. so .get(i)[0]
        }
        return res;
        
        // PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> freqMap.get(a) - freqMap.get(b)); // store the top k frequent elements
        // for (int key : freqMap.keySet()) {
        //     if (minHeap.size() < k) {
        //         minHeap.offer(key);
        //     } else if (freqMap.get(key) > freqMap.get(minHeap.peek())) { // 这里不要写错了。。是两个的freq对比。都是freqMap.get的。
        //         minHeap.poll();
        //         minHeap.offer(key);
        //     }
        // }
        // int[] res = new int[minHeap.size()];
        // for (int i = minHeap.size() - 1; i >= 0; i--) {
        //     res[i] = minHeap.poll();
        // }
        // return res;
    }
    
    private static void quickSelect(List<int[]> values, int left, int right, int k) {
        // choose a pivot
        // partition the array using this pivot
        // find out if the pivot now locates at k-th position (globally)
        // if so, return
        // if not, recursively do so in left side, if pivot > k, otherwise in right side.
        
        int pivotIndex = partition(values, left, right);
        if (pivotIndex == k) {
            return;
        } else if (pivotIndex > k) {
            quickSelect(values, left, pivotIndex - 1, k);
        } else {
            quickSelect(values, pivotIndex + 1, right, k);
        }
    }
    
    private static int partition(List<int[]> values, int left, int right) {
        int pivotIndex = getRandomIndex(left, right);
        int pivot = values.get(pivotIndex)[1];
        swap(values, pivotIndex, right);
        pivotIndex = right;  // 更新为pivot所在的新位置，注意不是right-1而是right。
        // [-,-,-,-,-,-,-]
        //              pv
        //  l               all elements before l is larger than pv.
        //            r     all elements after r is smaller than pv.
        //  finally, l will exceed r, swap l with last eleemnt(pv), and return l as result (now [0,l] is >= pv)
        int l = 0;
        int r = right - 1;
        while (l <= r) {
            if (values.get(l)[1] >= pivot) {
                l++;
            } else {
                swap(values, l, r);
                r--;
            }
        }
        
        swap(values, l, pivotIndex);
        return l;
    }
    
    private static int getRandomIndex(int left, int right) {
        Random random = new Random();
        return left + random.nextInt(right - left + 1);
    }
    
    private static void swap(List<int[]> values, int a, int b) {
        int[] tmp = Arrays.copyOf(values.get(a), 2);
        values.set(a, values.get(b));
        values.set(b, tmp);
    }
}
