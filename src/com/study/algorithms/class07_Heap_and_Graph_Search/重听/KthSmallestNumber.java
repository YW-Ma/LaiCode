package com.study.algorithms.class07_Heap_and_Graph_Search.重听;

import java.util.*;

public class KthSmallestNumber {
    // a matrix of size nxm
    // each row are sorted, each column are sorted (ascending)
    // find the kth smallest in it

    // assume: integer, not empty, get the value of kth smallest, k>=0

    /*
    *[[1,3,5,7],
    * [2,4,6,8],
    * [3',5,7,9]]
    * */

    // queue: [1]
    // 1  [2,3]
    // 2  [3,3',4]
    // 3  [3',4,5]
    // 3' <-- fourth smallest.

    // since there may be duplicate numbers, we need a wrapper class.
    class Cell implements Comparable<Cell>{
        int value;
        int row;
        int col;

        Cell(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Cell other) {
            if (this.value == other.value) {
                return 0;
            }
            return this.value < other.value ? -1 : 1; // -1的排在前面。
        }
    }

    public int kthSmallest(int[][] matrix, int k) {
        Queue<Cell> pq = new PriorityQueue<Cell>(); // 如果不提供compareTo，那么会 o1.toString().compareTo()   可以在这里提供new Comparator, 或者在Cell里implements一个Comparable。
        pq.add(new Cell(matrix[0][0], 0, 0));
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        visited[0][0] = true; // visit是在generate的时候，还是expand的时候？ （为了防止反复generate，应该在generate的时候）
        for (int i = 0; i < k - 1; i++) {
            // expand
            Cell node = pq.poll();

            // generate (去重可以不用hashSet，直接用一个boolean matrix即可，因为我们知道可以用col+row唯一定位。)
            int col = node.col;
            int row = node.row;
            if (row + 1 < matrix.length && !visited[row + 1][col]) { // 这是会挂的，因为row+1，col+1都可能超过范围了。
                pq.offer(new Cell(matrix[row + 1][col], row + 1, col));
                visited[row + 1][col] = true;
            }
            if (col + 1 < matrix[0].length && !visited[row][col + 1]) {
                pq.offer(new Cell(matrix[row][col + 1], row, col + 1));
                visited[row][col + 1] = true;
            }
        }
        // 现在，pop了k-1次了，下一个就是第k大的。
        return pq.peek().value;
    }
}


// aaabbbaaaa
// 0001233333
// 7654444321
