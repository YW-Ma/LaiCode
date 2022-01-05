package com.jiuzhang.BFS进阶与动态规划.邮局的建立II;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class BFS {
    static final int WALL = 2;
    static final int HOUSE = 1;
    static final int EMPTY = 0;
    static final int[] deltaRow = new int[]{0, 1, 0, -1};
    static final int[] deltaCol = new int[]{1, 0, -1, 0};
    
    // input: 2D grid
    // output: the minimum distance sum of the distance from postal office to all HOUSE
    public static int shortestDistance(int[][] grid) {
        // 当空地数量远大于房子数量的时候，最好用这个方案 -- 枚举房子的位置 而不是空地的位置
        // 用BFS计算HOUSE到每个地方的distance
        // 由于有的空地是从某些房子不可抵达的，这种情况需要记录一下每个空地的可达房子数量。如果能走到这个空地的房子数量小于房子总数，则不能用这个空地。
        
        // 1. corner case: grid不合法
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // 2. 公共变量的准备（两个Map）
        HashMap<Integer, Integer> distanceSum = new HashMap<>();
        HashMap<Integer, Integer> reachableCount = new HashMap<>();
        int rows = grid.length;
        int cols = grid[0].length;
        
        // 3. 从所有的房子出发，用BFS探索完所有的空地，同时记录房子总数
        int houses = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果是房子，就调用bfs
                if (grid[i][j] == HOUSE) {
                    int index = i * cols + j;
                    bfs(grid, index, distanceSum, reachableCount);
                    houses++;
                }
            }
        }
        
        // 4. 对所有的空地，检查它对应房子总数是否正确，正确的话用它的sum更新最小值
        int minCost = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int id = i * cols + j;
                if (grid[i][j] == EMPTY && reachableCount.getOrDefault(id, 0) == houses) {
                    minCost = Math.min(minCost, distanceSum.get(id));
                }
            }
        }
        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }
    
    // 每个房子出发的BFS，需要传入distance和count两个map
    private static void bfs(int[][] grid, int index, HashMap<Integer, Integer> distanceSum, HashMap<Integer, Integer> reachableCount) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<Integer> queue = new ArrayDeque<>();
        Map<Integer, Integer> distance = new HashMap<>();
        queue.add(index);
        distance.put(index, 0);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int row = current / cols;
            int col = current % cols;
            
            // four directions
            for (int direction = 0; direction < 4; direction++) {
                int adjRow = row + deltaRow[direction];
                int adjCol = col + deltaCol[direction];
                int nextIndex = adjRow * cols + adjCol;
                if (!isValid(grid, adjRow, adjCol)) {
                    continue;
                }
                // for the unvisited empty space
                if (!distance.containsKey(nextIndex)) {
                    queue.offer(nextIndex);
                    distance.put(nextIndex, distance.get(current) + 1); // start from 0 (which is house), based on the previous element
                    distanceSum.put(nextIndex, distanceSum.getOrDefault(nextIndex, 0) + distance.get(nextIndex));
                    reachableCount.put(nextIndex, reachableCount.getOrDefault(nextIndex, 0) + 1); // based on the current element's previous value
                }
            }

        }
        
    }
    
    // helper function --> 判断一个neighbor是否可以放置
    // 只有边界内、未在本次BFS访问、而且内容为empty是可以放置的
    public static boolean isValid(int[][] grid, int row, int col) {
        if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0) {
            return false;
        }
        return grid[row][col] == EMPTY;
    }
}
