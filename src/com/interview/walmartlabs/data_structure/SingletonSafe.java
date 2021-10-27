package com.interview.walmartlabs.data_structure;

public class SingletonSafe {
    // thread safe & lazy
    private static volatile SingletonSafe instance; // initialization and assignment can be rearrange without volatile
    private int num;
    private SingletonSafe() {
        num = 10;
    }
    public static SingletonSafe getInstance() {
        if (instance != null) { // short path
            return instance;
        }
        synchronized(Singleton.class) {
            if (instance == null) {
                instance = new SingletonSafe();
            }
        }
        return instance;
    }
}
