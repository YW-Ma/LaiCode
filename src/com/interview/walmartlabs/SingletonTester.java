package com.interview.walmartlabs;

public class SingletonTester {
    public static void main(String[] args) {
        Singleton c1 = Singleton.getInstance();
        Singleton c2 = Singleton.getInstance();
        // 适用场景：some expensive resources, or resources needs to be global unique
        // link: HttpRequest
        // Logger
    }
}
