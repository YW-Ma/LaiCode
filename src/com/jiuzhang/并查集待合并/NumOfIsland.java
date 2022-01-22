package com.jiuzhang.并查集待合并;

import java.util.ArrayList;
import java.util.List;

public class NumOfIsland {
    /*
    * 给定 n, m, 分别代表一个二维矩阵的行数和列数, 并给定一个大小为 k 的二元数组A. 初始二维矩阵全0.
    *
    * 二元数组A内的k个元素代表k次操作, 设第i个元素为 (A[i].x, A[i].y), 表示把二维矩阵中下标为A[i].x行A[i].y列的元素由海洋变为岛屿.
    * --> 力扣上二元组不再有wrapper class了
    * 问在每次操作之后, 二维矩阵中岛屿的数量. 你需要返回一个大小为k的数组.
     * */
    
    public static void main(String[] args) {
        NumOfIsland numOfIsland = new NumOfIsland();
        int[][] operations = new int[][]{{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
        List<Integer> res = numOfIsland.numIslands2(3, 3, operations);
        System.out.println(res);
    }
    
    public List<Integer> numIslands2(int cols, int rows, int[][] operators) {
        List<Integer> res = new ArrayList<>();
        // corner case:
        if (operators == null) {
            return res;
        }
        
        UnionFind uf = new UnionFind();
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] operation : operators) {
            int col = operation[0];
            int row = operation[1];
            int id = row * cols + col; // unique id for a cell
            uf.add(id);
            for (int i = 0; i < directions.length; i++) {
                int[] dir = directions[i];
                if (row + dir[0] < 0 || row + dir[0] >= rows || col + dir[1] < 0 || col + dir[1] >= cols) {
                    continue;
                }
                int neighborId = (row + dir[0]) * cols + (col + dir[1]);
                if (uf.fathers.containsKey(neighborId)) {
                    uf.merge(neighborId, id);
                }
            }
            res.add(uf.numOfSet);
        }
        return res;
    }
}

// 输入数据
//  3
//  3
//  [[0,1],[1,2],[2,1],[1,0],[0,2],[0,0],[1,1]]
// 输出数据
//  [1,2,3,4,2,2,1]
// 期望答案   ^
//  [1,2,3,4,3,2,1]
//           ^

/*
*   [ ][+][+]   -->  3个连通域，我的算法给出的是两个。 原因是没有处理越界的情况。 越界了以后的id错误地指向了别的位置merge起来了。
*   [+][ ][+]
*   [ ][+][ ]
* */