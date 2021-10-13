package com.interview.codesignal;

import java.util.Arrays;
import java.util.Comparator;

public class BeautyOfMatrix {
    // https://leetcode.com/discuss/interview-question/949185/Uber-or-SDE-1-or-US-or-CodeSignal-OA
    public static void main(String[] args) {
        Cell[] cells = new Cell[10];
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell();
        }
        Arrays.sort(cells);
        System.out.println(123);
    }
    static class Cell implements Comparable<Cell> {
        Integer beauty;
        Integer size;

        @Override
        public int compareTo(Cell o) {
            return this.beauty - o.beauty;
        }
        public Cell() {
            beauty = 0;
            size = 0;
        }
    }
}
