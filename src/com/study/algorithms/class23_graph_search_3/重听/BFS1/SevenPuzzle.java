package com.study.algorithms.class23_graph_search_3.重听.BFS1;

public class SevenPuzzle {
    class State {
        // a state of board
        private int[] values;
        private int h;
        private int zeroPos;
        
        public State(int[] values) {
            this.values = values;
            this.h = 0;
            for (int t = 0; t < 8; t++) {
                zeroPos = t;
            }
        }
    }
}
