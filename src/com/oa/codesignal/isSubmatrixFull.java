package com.oa.codesignal;

import java.util.Arrays;

public class IsSubmatrixFull {
    public static boolean[] isSubmatrixFull_Ref(int[][] numbers) {
        int[] num = new int[10];
        int row = 3;
        int col = numbers[0].length;
        boolean[] res = new boolean[col-2];
        int count = 0;
        for(int i=0; i<3; ++i){
            for(int j=0; j<3; ++j){
                int tmp = numbers[i][j];
                if(num[tmp] == 0){
                    count++;
                }
                num[tmp]++; // num 似乎是记录每个value出现的次数的。
            }
        }
        res[0] = false;
        if(count == 9)
            res[0] = true;


        for(int i=1; i<col-2; ++i){
            int tmp = 0;
            for(int j=0; j<3; ++j){ // tmp 用来拉取某个九宫格window左侧←的三个element
                tmp = numbers[j][i-1];
                num[tmp]--; // 减去这个值
                if(num[tmp] == 0){
                    count--; // 对应的count--
                }
            }

            for(int j=0; j<3; ++j){ // tmp 用来拉取某个九宫格window中，最右侧→的element
                tmp = numbers[j][i+2];
                if(num[tmp] == 0){
                    count++; // 相当于把剪掉的那个加回来。
                }
                num[tmp]++;
            }
            // 其实原理很简单，把window移动后，把左边移出去的的影像去除，然后添加新的一列。检查是否是9

            if(count == 9)
                res[i] = true;
            else
                res[i] = false;
        }
        return res;
    }
    
    public static void main(String[] args) {
        boolean[] res = isSubmatrixFull(new int[][]{{1,2,3,1,1}, {4,5,6,4,4}, {7,8,9,7,7}});
        System.out.println(Arrays.toString(res));
    }
    
    
// input: 3xn int array
// output: 1x(n-2) boolean array

// get three col -> check
// remove the left-most col, add the right-most col. check again
// use an int array to record the number of 1-9 elements in a 3x3 window
    // array[1] = 3 means, there are three 1 in 3x3 window
// we use a counter to track how many unique 1-9 nums in the window,
    // we will increase counter, if we get a number that not appeared (i.e. array[n] == 0)
    // we will decrease counter, if the array[n] drop from 1 to 0.
    public static boolean[] isSubmatrixFull(int[][] matrix) {
        // corner cases
        // ...
        
        boolean[] res = new boolean[matrix[0].length - 2];
        int[] nums = new int[10]; // num[i] represents how many number of i appears in the window,  num[0] is invalid.
        int counter = 0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int n = matrix[j][i];
                if (nums[n] == 0) {
                    counter++;
                }
                nums[n]++;
            }
        }
        if (counter == 9) {
            res[0] = true;
        } else {
            res[0] = false;
        }
        
        for (int i = 1; i < matrix[0].length - 2; i++) {
            // remove left-most col
            for (int j = 0; j < 3; j++) {
                int n = matrix[j][i - 1];
                if (nums[n] == 1) {
                    counter--;
                }
                nums[n]--;
            }
            
            // add right-most col
            for (int j = 0; j < 3; j++) {
                int n = matrix[j][i + 2];
                if (nums[n] == 0) {
                    counter++;
                }
                nums[n]++;
            }
            
            // check counter
            if (counter == 9) {
                res[i] = true;
            } else {
                res[i] = false;
            }
        }
        
        return res;
    }
}
