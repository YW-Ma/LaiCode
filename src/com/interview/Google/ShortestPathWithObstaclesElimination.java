package com.interview.Google;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Node {
    int row;
    int col;
    int k;
    int step;
    public Node (int row, int col, int k, int step) {
        this.row = row;
        this.col = col;
        this.k = k;
        this.step = step;
    }
}

public class ShortestPathWithObstaclesElimination {
    //[[0,0,0,0,0,0,0,0,0,0],
    // [0,1,1,1,1,1,1,1,1,0],
    // [0,1,0,0,0,0,0,0,0,0],
    // [0,1,0,1,1,1,1,1,1,1],
    // [0,1,0,0,0,0,0,0,0,0],
    // [0,1,1,1,1,1,1,1,1,0],
    // [0,1,0,0,0,0,0,0,0,0],
    // [0,1,0,1,1,1,1,1,1,1],
    // [0,1,0,1,1,1,1,0,0,0],
    // [0,1,0,0,0,0,0,0,1,0],
    // [0,1,1,1,1,1,1,0,1,0],
    // [0,0,0,0,0,0,0,0,1,0]]
    //1
    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,0,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,0,0,0,0,0,0,0,0},
                {0,1,0,1,1,1,1,1,1,1},
                {0,1,0,0,0,0,0,0,0,0},
                {0,1,1,1,1,1,1,1,1,0},
                {0,1,0,0,0,0,0,0,0,0},
                {0,1,0,1,1,1,1,1,1,1},
                {0,1,0,1,1,1,1,0,0,0},
                {0,1,0,0,0,0,0,0,1,0},
                {0,1,1,1,1,1,1,0,1,0},
                {0,0,0,0,0,0,0,0,1,0}};
        int k = 1;
        System.out.println(shortestPath(grid, k));
    }
    
    public static int shortestPath(int[][] grid, int k) {
        if (isCornerCase(grid)) {
            return 0;
        }
        
        // from upper-left corner to lower-right
        // 1 for obstacle
        // 0 for empty space
        
        // Solution
        // 1. 使用BFS
        // 2. 注意行动方向依然是四个方向，而不是只探索右、下这两个方向（想想一个超级复杂的迷宫）
        // 3. step, k 都应该作为属性的一部分存储在queue里面。
        // 4. 依然需要visited数组，否则会出现死循环（四个方向） -->  但是并不是说visited了就不能更新，如果k更小是可以更新并重新进入这个点的
        // 5. 最好不要用level by level。不然不好管理step.
        
        int rows = grid.length;
        int cols = grid[0].length;
        
        // BFS, bring how many obstacles I can break with me.
        Queue<Node> q = new ArrayDeque<>(); // [row, col, k]
        q.offer(new Node(0, 0, k, 0));
        int[][] visited = new int[rows][cols];
        for (int[] row : visited) {   // 要么让默认值k是-1，要么让默认k为0但是等于的时候也可以走。 否则默认k为0，要是也只允许走过0个，那么就一步都走不出去了。
            Arrays.fill(row, -1);
        }
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.row == rows - 1 && cur.col == cols - 1) {
                return cur.step;
            }
            
            // try four direction
            tryDirection(grid, cur, -1, 0, q, rows, cols, visited);
            tryDirection(grid, cur, 1, 0, q, rows, cols, visited);
            tryDirection(grid, cur, 0, -1, q, rows, cols, visited);
            tryDirection(grid, cur, 0, 1, q, rows, cols, visited);

        }
        return -1; // 如果是走到这里了，说明直到empty都没有获得结果。所以-1
    }
    
    private static void tryDirection(int[][] grid, Node cur, int rowDir, int colDir, Queue<Node> q, int rows, int cols, int[][] visited) {
        if (cur.row + rowDir >= 0 && cur.col + colDir >= 0 && cur.row + rowDir < rows && cur.col + colDir < cols) {
            // empty
            if (grid[cur.row + rowDir][cur.col + colDir] == 0 && visited[cur.row + rowDir][cur.col + colDir] < cur.k) {
                q.offer(new Node(cur.row + rowDir, cur.col + colDir, cur.k, cur.step + 1));
                visited[cur.row + rowDir][cur.col + colDir] = cur.k;
            }
            // has obstacle but k >= 1
            else if (cur.k >= 1 && visited[cur.row + rowDir][cur.col + colDir] < cur.k - 1) { // visited里面存储了上次（或者从未）造访的时候，k的余额。 理论上 是本次比它大才更新。但是考虑到默认是0, 所以改成<=
                q.offer(new Node(cur.row + rowDir, cur.col + colDir, cur.k - 1, cur.step + 1));
                visited[cur.row + rowDir][cur.col + colDir] = cur.k - 1;
            }
        }
    }
    
    private static boolean isCornerCase(int[][] grid) {
        if (grid == null || grid[0] == null) {
            return true;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        if (rows == 0 || cols == 0) {
            return true;
        }
        return false;
    }
}
