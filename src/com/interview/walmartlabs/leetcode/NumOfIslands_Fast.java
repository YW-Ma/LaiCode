package com.interview.walmartlabs.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class NumOfIslands_Fast {
    int rows;
    int cols;
    
    // 用id代替cell，会更加高效。在add的时候visit也会更加高效
    
    public int numIslands(char[][] grid) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // if visited, then skip
                if (grid[i][j] != '1') {
                    continue;
                }
                // if not visited, do a DFS or BFS to mark the island as visited.
                counter++;
                // Queue<Cell> q = new ArrayDeque<>();
                Queue<Integer> q = new ArrayDeque<>();
                // q.offer(new Cell(i, j));
                q.offer(i * cols + j);
                grid[i][j] = '0';
                while (!q.isEmpty()) {
                    //Cell cur = q.poll();
                    int id = q.poll();
                    int r = id / cols;
                    int c = id % cols;
                    // try to offer 4-adjacent neighbors
                    if (r + 1 < rows && grid[r + 1][c] == '1') {
                        q.offer((r + 1) * cols + c);
                        grid[r + 1][c] = '0'; // 原来要把它放这里，才能防止time limit
                    }
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        q.offer((r - 1) * cols + c);
                        grid[r - 1][c] = '0';
                    }
                    if (c + 1 < cols && grid[r][c + 1] == '1') {
                        q.offer(r * cols + c + 1);
                        grid[r][c + 1] = '0';
                    }
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        q.offer(r * cols + c - 1);
                        grid[r][c - 1] = '0';
                    }
                }
            }
        }
        return counter;
    }
}


// class Cell { // 如果不让用cell，可以用一个id --> id / cols == row, id % cols == col
//     int row;
//     int col;

//     public Cell(int row, int col) {
//         this.row = row;
//         this.col = col;
//     }
// }
