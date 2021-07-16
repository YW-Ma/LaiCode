package com.study.algorithms.class23_graph_search_3.重听;
//
//"681 Seven Puzzle
//Request edit access
//
//
//Share
//FileEditViewToolsHelp
//To enable screen reader support, press ⌘+Option+Z To learn about keyboard shortcuts, press ⌘slash
//Seven Puzzle
//Use the fewest steps to convert the given starting board to the final state (i.e. [0,1,2,3,4,5,6,7]). In
//each step, you can swap 0 with any adjacent number.
//https://app.laicode.io/app/problem/681

// 类似数字华容道，有一个0代表空格，别人可以和他交换
// 1. 给定目标位置（比如 按顺序 0 1 2 3
//                          4 5 6 7
// 2. 问最少步骤怎么求？ -->  求最少步骤:BFS1 (求路径要BFS2)
// 开始model：
// - 什么是点？ （应该不是格子，完全没法做） （应该用"状态" 做为点），n个格子，最多有n!个状态（全排列）
// - 什么是边？ 状态是点，那么能转换到别的状态的操作是边。 比如上图的移动1和移动4就是两个边
// -        每个点最多产生4个边，zero的上下左右。 但是有一些情况会out of bound。

import java.util.*;

public class SevenPuzzle {
    
    public int myNumOfSteps(int[] values) {
        Queue<Board> queue = new ArrayDeque<>();
        HashMap<Board, Integer> boardStep = new HashMap<>();
    
        Board start = new Board(new int[]{0, 1, 2, 3, 4, 5, 6, 7}); // Node is status of board （优化版本，往回走）
        queue.offer(start);
        boardStep.put(start, 0); // 终点出发, 获得整个HashMap
        
        while (!queue.isEmpty()) {
            Board board = queue.poll();
            int step = boardStep.get(board); // 获取generate的时候插入的steps
            
            int[] zeroPos = board.findZero();
            int zeroI = zeroPos[0];
            int zeroJ = zeroPos[1];
            
            for (int[] dir : DIRS) {
                int i = zeroI + dir[0];
                int j = zeroJ + dir[1];
                if (!board.outOfBound(i, j)) {
                    board.swap(zeroI, zeroJ, i, j);
                    if (!boardStep.containsKey(board)) { // 查重
                        Board newBoard = board.clone();
                        queue.offer(newBoard);
                        boardStep.put(newBoard, step + 1); // generate
                    }
                    board.swap(zeroI, zeroJ, i, j);
                }
            }
        }
        return boardStep.getOrDefault(new Board(values), -1); // 捞需要的那个结果，可以随便捞。 上面是预处理
    }
    
    final static int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int numOfSteps(int[] values) {
        Queue<Board> queue = new ArrayDeque<>();
        Map<Board, Integer> boardStep = new HashMap<>(); // HashMap会存储所有情况到final的开销（因为是从final触发） 记录从start到当前步骤的最小步数。
        
        /** 一般做最小步数，都需要HashMap。存储Board到Steps Num。 两个作用：1. 判重复  2. 从起点到当前的最小步数*/
        Board start = new Board(new int[]{0, 1, 2, 3, 4, 5, 6, 7}); // Node is status of board. 并且是优化版本（从final往回走）
        queue.offer(start);
        boardStep.put(start, 0);
        
        while (!queue.isEmpty()) {
            Board board = queue.poll(); // expand: 找zero
            int step = boardStep.get(board); // 获取generate的时候插入的steps
            
            int[] zeroPos = board.findZero();
            int zeroI = zeroPos[0];
            int zeroJ = zeroPos[1];
            
            for (int[] dir : DIRS) {  // possible Edges
                int i = zeroI + dir[0];
                int j = zeroJ + dir[1];
                if (!board.outOfBound(i, j)) {
                    board.swap(zeroI, zeroJ, i, j);
                    if (!boardStep.containsKey(board)) { // generate前，查重【HashMap// 作用一】
                        Board newBoard = board.clone();
                        queue.offer(newBoard); // generate
                        boardStep.put(newBoard, step + 1); // 记录步数【HashMap作用二】
                    }
                    board.swap(zeroI, zeroJ, i, j);
                }
            }
        }
        return boardStep.getOrDefault(new Board(values), -1);
    }
    
    //// assuming the board is always 2 by 4
    //Time:O(|number of unique boards|)
    //Space: O(|number of unique boards| * 8)
    //Seven Puzzle
    //Turn on screen reader support"
    
    
    static class Board {
        public final static int R = 2;
        public final static int C = 4;
        
        public Board() {
        }
        
        public Board(int[] values) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    board[i][j] = values[i * C + j];
                }
            }
        }
        
        public void swap(int i1, int j1, int i2, int j2) {
            int temp = board[i1][j1];
            board[i1][j1] = board[i2][j2];
            board[i2][j2] = temp;
        }
        
        public int[] findZero() {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] == 0) {
                        return new int[]{i, j};
                    }
                }
            }
            return null;
        }
        
        public boolean outOfBound(int i, int j) {
            return i < 0 || i >= R || j < 0 || j >= C;
        }
        
        @Override
        public int hashCode() {
            int code = 0;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    code = code * 10 + board[i][j];
                }
            }
            return code;
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Board)) {
                return false;
            }
            Board b = (Board) o;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] != b.board[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
        
        @Override
        public Board clone() {
            Board c = new Board();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    c.board[i][j] = board[i][j];
                }
            }
            return c;
        }
        
        private int[][] board = new int[R][C];
    }
}

