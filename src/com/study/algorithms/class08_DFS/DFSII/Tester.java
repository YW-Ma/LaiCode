package com.study.algorithms.class08_DFS.DFSII;

import java.util.List;

public class Tester {
    public static void main(String[] args) {
        SubSetsII subSetsII = new SubSetsII();
        List<String> res = subSetsII.subSets("abbb");
        System.out.println(res);
    }
}
// abbb
//   ^

// a ""
// ab            a         b ""
// abb ab        a         bb b ""
// abbb abb ab   a         bbb bb b ""