package com.interview.codesignal;
import java.util.Arrays;
public class SortBoudnaryElements {
    public static void main(String[] args) {
        int[][] res = boundaySort(new int[][]{
                {1, 2, 3, 4, 0},
                {1, 1, 1, 1, 2},
                {1, 2, 2, 2, 4},
                {1, 9, 3, 1, 7}});
        
        System.out.println(Arrays.toString(res));
    }
    /*  Input matrix:
                 n cols
             0       n-1
            {1 2 3 4 0},
            {1 1 1 1 2}, <-- (n-1) + row #
            {1 2 2 2 4},    m rows
            {1 9 3 1 7}  <-- (n + m - 2) - 1 + col
        Output:
            0 1 1 1 1
            9 1 1 1 1
            7 2 2 2 2
            4 4 3 3 2
        Explanation:
            For given matrix, border elements are:
            (1, 2, 3, 4, 0, 2, 4, 7, 1, 3, 9, 1, 1, 1)  --> clockwise
            After sorting in clockwise order:
            (0, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 7, 9)

     */
    
    
    // Boundary element sort
    public static int[][] boundaySort(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] boundary = new int[2 * n + 2 * m - 4];
        int idx = 0; // idx in the boundary.
        
        // get the boundary (not necessary to be in clock-wise order, since we will do the sort)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    boundary[idx++] = grid[i][j];
                }
            }
        }
        
        // sort
        Arrays.sort(boundary);
        
        // fill back
        for (int i = 0; i < m; i++) {
            // first row:
            if (i == 0) {
                for (int j = 0; j < n; j++) {
                    grid[i][j] = boundary[j];
                }
            }
            // middle rows:
            if (i > 0 && i < m - 1) {
                grid[i][n - 1] = boundary[n - 1 + i];
                grid[i][0] = boundary[(2 * n + 2 * m - 4) - i];
            }
            // last row:
            if (i == m - 1) {
                for (int j = n - 1; j >= 0; j--) {
                    grid[i][j] = boundary[(n + m - 2) - 1 + (n - j)];
                }
            }
        }
        return grid;
    }
    
    
}
