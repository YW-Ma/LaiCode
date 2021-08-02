package com.study.algorithms.class19_cross_training_2.SUM.TwoSumPairs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TwoSumAllPairsIITest {
    
    @Test
    void allPairs() {
        TwoSumAllPairsII twoSumAllPairsII = new TwoSumAllPairsII();
        List<List<Integer>> res = twoSumAllPairsII.allPairs(new int[]{1,1,1,1,1}, 2);
        System.out.println(Arrays.toString(res.toArray()));
    }
}