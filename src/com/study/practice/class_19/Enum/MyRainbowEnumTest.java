package com.study.practice.class_19.Enum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyRainbowEnumTest {

    @org.junit.jupiter.api.Test
    void useMethods_normal() {
        MyRainbowEnum myRainbowEnum = new MyRainbowEnum();
        myRainbowEnum.useMethods();
        System.out.println("------");
    }

    @Test
    void advancedEnum_normal() {
        MyRainbowEnum myRainbowEnum = new MyRainbowEnum();
        myRainbowEnum.advancedEnum();
    }
}