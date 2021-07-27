package com.study.algorithms.final2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DinnerTest {
    
    @Test
    void canFormInfiniteLoop() {
        Dinner dinner = new Dinner();
        System.out.println(dinner.canFormInfiniteLoop(new String[]{"ALICE", "CHARLES", "ERIC", "SOPHIA"}));
    }
}