package com.study.practice.meeting;

import java.util.*;

public class Tetris {
    public static void main(String[] args) {
        fallDown(new char[][]{{'.', 'F', 'F'}, {'#', '.', 'F'}, {'#', '.', '.'}});
    }
    public static char[][] fallDown(char[][] pattern) {
        // corner case:
        // ...

        // generate how many steps can drop for each F
        int rows = pattern.length;
        int cols = pattern[0].length;
        List<List<Integer>> fLocations = new ArrayList<>(); // ?API
        int minDrop = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char ch = pattern[i][j];
                if (ch != 'F') {
                    continue;
                }
                fLocations.add(Arrays.asList(i, j));
                // for each ‘F’, generate how many steps it can drop
                int dotCounter= 0;
                // maxHeight = (num of dot between it and the nearest #)
                for (int h = i; h < rows; h++) {
                    if (pattern[h][j] == '#') {
                        break;
                    }
                    if (pattern[h][j] == '.') {
                        dotCounter++;
                    }
                }
                minDrop = Math.min(minDrop, dotCounter);
            }
        }

        // generate the output
        char[][] pad = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                pad[i][j] = '.';
                if (pattern[i][j] == '#') {
                    pad[i][j] = pattern[i][j];
                }
            }
        }
        for (List<Integer> loc : fLocations) {
            pad[loc.get(0) + minDrop][loc.get(1)] = 'F';
        }
        return pad;
    }


}
