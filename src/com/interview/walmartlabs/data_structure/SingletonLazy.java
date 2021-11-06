package com.interview.walmartlabs.data_structure;

public class SingletonLazy {
    private static SingletonLazy instance;
    private SingletonLazy() {
        num = 10;
    }
    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
    // other fields
    private int num;
}
