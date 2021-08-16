package com.study.algorithms.class20_cross_training_3;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedArray {

    static class Entry {
        // row number
        int x;
        // col number
        int y;
        // value
        int value;
        public Entry(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    // k 个 array里操作：sorted？ size？ length comparison
    public int[] merge(int[][] arrayOfArrays) {
        PriorityQueue<Entry> minHeap = new PriorityQueue<>(new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                if (o1.value == o2.value) {
                    return 0;
                }
                return o1.value < o2.value ? -1 : 1;
            }
        });

        // PQ 的作用？ 记录下一个需要加的值是哪个array的 （类似于实现谁小移谁）

        int length = 0;
        for (int i = 0; i < arrayOfArrays.length; i++) {
            int[] array = arrayOfArrays[i];
            length += array.length;
            if (array.length != 0) {
                // we use two index to record the position of each element:
                // 1. the index of the array in the arrayOfArrays,  --> x
                // 2. the index of the element in the array --> y
                minHeap.offer(new Entry(i, 0, array[0])); // 把第一列写好
            }
        }

        int[] result = new int[length];
        int cur = 0;
        while (!minHeap.isEmpty()) {
            Entry tmp = minHeap.poll();
            result[cur++] = tmp.value;
            if (tmp.y + 1 < arrayOfArrays[tmp.x].length) { // 谁小移谁（第x行最小，x行的y移动）
                tmp.y++;
                tmp.value = arrayOfArrays[tmp.x][tmp.y];
                minHeap.offer(tmp);
            }
        }
        return result;
    }
}
