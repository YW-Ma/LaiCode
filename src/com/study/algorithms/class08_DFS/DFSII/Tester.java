package com.study.algorithms.class08_DFS.DFSII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tester {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.subList(2,4).clear();
    }
}
// abbb
//   ^

// a ""
// ab            a         b ""
// abb ab        a         bb b ""
// abbb abb ab   a         bbb bb b ""