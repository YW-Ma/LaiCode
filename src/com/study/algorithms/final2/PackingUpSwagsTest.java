package com.study.algorithms.final2;

import static org.junit.jupiter.api.Assertions.*;

class PackingUpSwagsTest {
    
    @org.junit.jupiter.api.Test
    void getPacksNum() {
        PackingUpSwags packingUpSwags = new PackingUpSwags();
        System.out.println(packingUpSwags.getPacksNum(11));
        assert packingUpSwags.getPacksNum(10) == 2;
    }
}