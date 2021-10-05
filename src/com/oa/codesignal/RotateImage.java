package com.oa.codesignal;

public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix);
        System.out.println(matrix);
    }
    
    // From Laioffer --> no extra space needed, we change four corresponding elements a time.
    // and scan from the out-most layer to the heart.
    
    /*
    *  side = 3
    *  5  1  9  11 < (0,3)
    *  2  4  8  10 <
    *  13 3  6  7  < (2,3)
    *  15 14 12 16
    *      ^  ^  ^
    * * */
    
    public static void rotate(int[][] matrix) { // n x n matrix
        // for each iteration: get current side length --> swap --> shrink side length by 2
        int n = matrix.length;
        int side = n - 1;
        int startRow = 0;
        int startCol = 0;
        while (side >= 1) {
            for (int i = 0; i < side; i++) {
                int tmp = matrix[startRow][startCol + i];
                matrix[startRow][startCol + i] = matrix[startRow + side - i][startCol];
                matrix[startRow + side - i][startCol] = matrix[startRow + side][startCol + side - i];
                matrix[startRow + side][startCol + side - i] = matrix[startRow + i][startCol + side];
                matrix[startRow + i][startCol + side] = tmp;
//                int first = matrix[startRow][startCol + i];
//                int second = matrix[startRow + i][startCol + side];
//                int third = matrix[startRow + side][startCol + side - i];
//                int fourth = matrix[startRow + side - i][startCol];
//                matrix[startRow + i][startCol + side] = first;
//                matrix[startRow + side][startCol + side - i] = second;
//                matrix[startRow + side - i][startCol] = third;
//                matrix[startRow][startCol + i] = fourth;
            }
            side -= 2;
            startCol++;
            startRow++; // 别忘了修改了
        }
        // base case: sidelength = 1 or 0, side = 0 or -1 (side length is n-1)
        //            luckily, in both cases, we don't need to further update it.
        return;
    }
}
