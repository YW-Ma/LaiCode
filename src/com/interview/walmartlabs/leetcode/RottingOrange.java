package com.interview.walmartlabs.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottingOrange {
    // O(size of grid)   logic is similar to level order traversal.
    public int orangesRotting(int[][] grid) {
        // 烂掉的橘子入queue，然后BFS即可
        Queue<Integer> q = new ArrayDeque<>();
        int rows = grid.length;
        int cols = grid[0].length;
        int freshOrange = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    q.offer(i * cols + j);
                }
                if (grid[i][j] == 1) {
                    freshOrange++;
                }
            }
        }
        
        if (freshOrange == 0) { // corner case, 不然会返回 -1分钟。
            return 0;
        }
        
        int minute = 0; // tracking minutes
        int size = q.size();
        while (!q.isEmpty()) {
            for (int i = 0; i < size; i++) {
                int id = q.poll();
                int row = id / cols;
                int col = id % cols;
                // expand to four neighbors
                if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                    q.offer((row - 1) * cols + col);
                    grid[row - 1][col] = 2;
                    freshOrange--;
                }
                if (row + 1 < rows && grid[row + 1][col] == 1) {
                    q.offer((row + 1) * cols + col);
                    grid[row + 1][col] = 2;
                    freshOrange--;
                }
                if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                    q.offer(row * cols + col - 1);
                    grid[row][col - 1] = 2;
                    freshOrange--;
                }
                if (col + 1 < cols && grid[row][col + 1] == 1) {
                    q.offer(row * cols + col + 1);
                    grid[row][col + 1] = 2;
                    freshOrange--;
                }
            }
            size = q.size();
            minute++;
        }
        
        return freshOrange == 0 ? minute - 1 : -1;
        
    }
}
