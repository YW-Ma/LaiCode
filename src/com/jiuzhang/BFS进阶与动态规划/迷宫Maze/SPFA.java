package com.jiuzhang.BFS进阶与动态规划.迷宫Maze;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

class Cell { // 记录一个path对应的dist，后续更新的话，在path上面追加，并且更新dist
    int dist; // distance
    int position; // current position
    String path; // path till now
    
    public Cell(int dist, int position, String path) {
        this.dist = dist;
        this.position = position;
        this.path = path;
    }
}

class Pair {
    int dist;
    String path;
    
    public Pair(int dist, String path) {
        this.dist = dist;
        this.path = path;
    }
    
    public boolean lessThanOrEqualTo(Pair p) {
        if (this.dist != p.dist) {
            return this.dist < p.dist; // 调用者是否比参数小
        }
        return this.path.compareTo(p.path) <= 0;
    }
}

class MazeGridType {
    static int SPACE = 0;
    static int WALL = 1;
}

public class SPFA {
    // weight不一样，所以需要用SPFA
    // 球球从给定的起点出发，可以up, down, left, right
    // 会一直滚动直到撞墙
    // 停下来以后才能选择下一个方向
    // 请问最短的到达终点的方式
    // 如果是普通的方案，就用BFS，但是这里每次移动(u,d,l,r)的weight不同，所以用SPFA
    
    // 输入：grid、起点、终点
    // 输出：一组由 u d l r 组成的path
    static final int[] deltaRow = new int[]{-1, 1, 0, 0};
    static final int[] deltaCol = new int[]{0, 0, -1, 1};
    static final String[] directionName = new String[]{"u", "d", "l", "r"};
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // corner case:
        if (ball.length == 0 || hole.length == 0 || maze.length == 0 || maze[0].length == 0) {
            return "impossible";
        }
        
        int rows = maze.length;
        int cols = maze[0].length;
        int targetIndex = hole[0] * cols + hole[1];
        // Queue永远是记录node的位置的。还可以记录别的信息（比如distance，用于PQ）
        Queue<Cell> queue = new PriorityQueue<>((a, b) -> {
            if (a.dist != b.dist) {
                return a.dist - b.dist;
            }
            return a.path.compareTo(b.path);
        });
        queue.offer(new Cell(0, ball[0] * cols + ball[1], ""));
        // Distance永远也是需要的，用来判断一个node是否走过（这里的node不是grid的格子了，而是一个可以进行决策的撞墙点）
        HashMap<Integer, Pair> distance = new HashMap<>();
        distance.put(ball[0] * cols + ball[1], new Pair(0, ""));
        // distance的一般是记录distance，这里实际上还包括了path，所以要用pair。如果不要求path就原版就行了
        // Queue里面其实可以只放position，但是由于用了priority queue，所以只能重复放置一份distance & path
        
        while (!queue.isEmpty()) {
            Cell current = queue.poll();
            int dist = current.dist;
            String path = current.path;
            int row = current.position / cols;
            int col = current.position % cols;
            // 对当前撞墙点的"邻居"进行判断，所谓邻居，是需要一直走到一个方向的
            for (int direction = 0; direction < 4; direction++) {
                int next = kickBall(row, col, direction, maze, hole[0], hole[1]);
                int newRow = next / cols;
                int newCol = next % cols;
                // 判断是否是需要入队（distance中存储的那个更大（distance或者字典序），才需要入队）
                String newPath = path + directionName[direction];
                int newDist = dist + Math.abs(newRow - row) + Math.abs(newCol - col);
                if (distance.containsKey(next) && distance.get(next).lessThanOrEqualTo(new Pair(newDist, newPath))) {
                    continue;
                }
                queue.offer(new Cell(newDist, next, newPath));
                distance.put(next, new Pair(newDist, newPath));
            }
            // 就算是找到了可以掉到洞里的路径，也需要继续寻找（存在更短的可能）
        }
        // 返回到达目的地的最短路径
        if (distance.containsKey(targetIndex)) {
            return distance.get(targetIndex).path;
        }
        return "impossible";
    }
    
    public int kickBall(int row, int col, int direction, int[][]maze, int targetRow, int targetCol) {
        // kickBall需要把hole即target传入， 否则会一脚踢过头。
        int rows = maze.length;
        int cols = maze[0].length;
        int diffRow = deltaRow[direction];
        int diffCol = deltaCol[direction];
        // 没撞墙且没有到目的地
        while (!isWall(row, col, maze)) {
            if (row == targetRow && col == targetCol) {
                return row * cols + col;
            }
            row += diffRow;
            col += diffCol;
        }
        // 会停在非法的点！ 要返回上一个点！
        return (row - diffRow) * cols + (col - diffCol);
    }
    
    private boolean isWall(int row, int col, int[][] maze) {
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return true;
        }
        return maze[row][col] == MazeGridType.WALL;
    }
}
