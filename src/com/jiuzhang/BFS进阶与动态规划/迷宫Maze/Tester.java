package com.jiuzhang.BFS进阶与动态规划.迷宫Maze;

public class Tester {
    public static void main(String[] args) {
        int[][] maze = new int[][]{{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}};
        int[] start = new int[]{4,3};
        int[] hole = new int[]{0,1};
        
        SPFA spfa = new SPFA();
        String res = spfa.findShortestWay(maze, start, hole);
        System.out.println(res);
    }
}
