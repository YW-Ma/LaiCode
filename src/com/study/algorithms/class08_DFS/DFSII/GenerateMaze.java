package com.study.algorithms.class08_DFS.DFSII;

public class GenerateMaze {
    public static void main(String[] args) {
        Dir[] dirs = Dir.values();
        System.out.println(dirs);
    }
    
    // This is an enum
    // We use enum to represent predefined constants.
    enum Dir {
        NORTH(-1, 0), SOUTH(1, 0), WEST(0, -1), EAST(0, 1);
        
        int deltaX;
        int deltaY;
        Dir (int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }
        
        // move certain times of deltaX
        public int moveX (int x, int times) {
            return x + times * deltaX;
        }
        // move certain times of deltaY
        public int moveY (int y, int times) {
            return y + times * deltaY;
        }
    }
}
