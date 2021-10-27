package com.interview.walmartlabs.data_structure;

public class Singleton {
    private final static Singleton INSTANCE = new Singleton();
    private Singleton() {
        num = 1;
    }
    public static Singleton getInstance() {
        return INSTANCE;
    }
    // other fields
    private int num;
}
