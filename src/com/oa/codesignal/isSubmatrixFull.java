package com.oa.codesignal;

public class IsSubmatrixFull {
    public static boolean[] isSubmatrixFull(int[][] numbers) {
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
}
