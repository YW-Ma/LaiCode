package com.study.algorithms.class18_cross_training_1.twoD;

public class rotateMatrix {
  // 要in-place
  // 不要上下、左右颠倒，那个方法没有通用的知识。（而且比如旋转1格，那就不管用了）
  // 应该是recursion，每次的逻辑是within a layer（一圈）的。

  // (i,j) 顺时针90度， 绘图理解应该是到(j, length - 1 - i)
  // 旋转的时候应该是，应该是把spiral中四个for中同一位置的，进行一次替换。
  // 每层用一个for来实现。

//  To enable screen reader support, press Ctrl+Alt+Z To learn about keyboard shortcuts, press Ctrl+slash
//  Rotate Matrix By 90 Degree Clockwise
//  Rotate an N * N matrix clockwise 90 degrees.
//          https://app.laicode.io/app/problem/125

  public class RotateMatrix {
    // Method 1: Split into levels and for each level split it into
    // four partitions.
    public void rotate(int[][] matrix) {
      // Assumptions: matrix is not null and has size of N * N, N >= 0.
      int n = matrix.length;
      if (n <= 1) {
        return;
      }
      int round = n / 2;
      for (int level = 0; level < round; level++) {
        int left = level;
        int right = n - 2 - level;
        for (int i = left; i <= right; i++) {
          int tmp = matrix[left][i];
          matrix[left][i] = matrix[n - 1 - i][left];
          matrix[n - 1 - i][left] = matrix[n - 1 - left][n - 1 - i];
          matrix[n - 1 - left][n - 1 - i] = matrix[i][n - 1 - left];
          matrix[i][n - 1 - left] = tmp;
        }
      }
    }

//    Time: O(n^2)
//    Space: O(1)


    // Method 2: Rotate a point by 90 degree clockwise ==
    // 1. Mirror the point according to y axis, then
    // 2. Mirror the point according the line of y = x.
    public void rotateII(int[][] matrix) {
      int n = matrix.length;
      if (n <= 1) {
        return;
      }
      mirrorY(matrix, n);
      mirrorYEX(matrix, n);
    }

    // mirror the point by y axis.
    private void mirrorY(int[][] matrix, int n) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n / 2; j++) {
          swap(matrix, i, j, i, n - 1 - j);
        }
      }
    }

    // mirror the point by the line of y = x.
    private void mirrorYEX(int[][] matrix, int n) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j + i < n - 1; j++) {
          swap(matrix, i, j, n - 1 - j, n - 1 - i);
        }
      }
    }

    private void swap(int[][] matrix, int iRow, int iCol, int jRow, int jCol) {
      int tmp = matrix[iRow][iCol];
      matrix[iRow][iCol] = matrix[jRow][jCol];
      matrix[jRow][jCol] = tmp;
    }
  }

//  Time: O(n^2)
//  Space: O(1)
//  Rotate Matrix By 90 Degree Clockwise
//  Turn on screen reader support
}
