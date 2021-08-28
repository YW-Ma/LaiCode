package com.study.algorithms.class07_Heap_and_Graph_Search.重听;

import java.util.List;
import java.util.PriorityQueue;

public class KthSmallestNumber_20210825 {
    // Breath First Search
    public int kthSmallest(int[][] matrix, int k) {
        // assume matrix and k is valid (not null, not empty, k >= 0)
        int rows = matrix.length;
        int cols = matrix[0].length;
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[rows][cols];
        
        pq.offer(new Cell(matrix[0][0], 0, 0));
        visited[0][0] = true;
        
        for (int i = 0; i < k - 1; i++) {
            // generate k-1 times (k times in total) so that the value we peek will be the k-th smallest.
            Cell node = pq.poll();
            if (node.row + 1 < rows && !visited[node.row + 1][node.col]) { // 注意，加一可能导致越界。
                pq.offer(new Cell(matrix[node.row + 1][node.col], node.row + 1, node.col));
                visited[node.row + 1][node.col] = true;
            }
    
            if (node.col + 1 < cols && !visited[node.row][node.col + 1]) {
                pq.offer(new Cell(matrix[node.row][node.col + 1], node.row, node.col + 1));
                visited[node.row][node.col + 1] = true;
            }
        }
        
        return pq.peek().value;
    }
    
    private class Cell implements Comparable<Cell>{
        int value;
        int row;
        int col;
        public Cell(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
        
        @Override
        public int compareTo(Cell that) {
            if (this.value == that.value) {
                return 0;
            } else if (this.value < that.value) { // the smaller, the prior.
                return -1;
            } else {
                return 1;
            }
        }
    }
}
