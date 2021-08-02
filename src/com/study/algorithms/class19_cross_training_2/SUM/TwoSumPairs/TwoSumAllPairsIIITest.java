package com.study.algorithms.class19_cross_training_2.SUM.TwoSumPairs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TwoSumAllPairsIIITest {
    
    @Test
    void allPairs() {
        TwoSumAllPairsIII twoSumAllPairsIII = new TwoSumAllPairsIII();
        List<List<Integer>> res = twoSumAllPairsIII.allPairs(new int[]{1,1,1,1,1}, 2);
        System.out.println(Arrays.toString(res.toArray()));
    }
}