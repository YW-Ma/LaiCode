package com.interview.walmartlabs;

public class Singleton {
    // static single instance
    // static getter
    // private constructor
    private static final Singleton instance = new Singleton();
    private String name;
    private Singleton() {
        this.name = "MYW";
    }
    public static Singleton getInstance() {
        return instance;
    }
}
