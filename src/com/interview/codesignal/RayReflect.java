package com.interview.codesignal;

public class RayReflect {
    public static void main(String[] args) {
        boolean res = rayReflect(6,10,0,0,5,1);
        System.out.println(res);
    }
    
// RayReflect
public static boolean rayReflect(int m, int n, int startX, int startY, int tarX, int tarY) {
    // m rows, n cols
    // start from (x,y) direction is (+1, +1)
    int x = startX;
    int y = startY;
    boolean isDown = true;
    boolean isRight = true;
    while (true) {
        int tx = x + (isRight ? 1 : -1);
        int ty = y + (isDown ? 1 : -1);
        if (ty == m) {
            isDown = false;
            continue;
        }
        if (ty == -1) {
            isDown = true;
            continue;
        }
        if (tx == n) {
            isRight = false;
            continue;
        }
        if (tx == -1) {
            isRight = true;
            continue;
        }
        
        if (tx == tarX && ty == tarY) {
            return true;
        }
        if (tx == startX && ty == startY) {
            return false;
        }
        x = tx;
        y = ty;
    }
}
}
