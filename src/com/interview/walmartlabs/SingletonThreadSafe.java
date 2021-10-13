package com.interview.walmartlabs;

public class SingletonThreadSafe {
    // static single instance
    // lazy initialization
    // thread safe

    private static volatile SingletonThreadSafe instance;
    String name;
    private SingletonThreadSafe() {
        name = "MYW";
    }
    public static SingletonThreadSafe getInstance() {
        if (instance != null) { // 在这里开一个捷径，这样read的时候可以不走synchronized。
            return instance;
        }
        synchronized (instance) {  // critical section  -->  但是全锁住太慢了。
            if (instance == null) {
                    instance = new SingletonThreadSafe(); // three steps: memory space, initialization, assignment.  can be rearranged by compiler. using volatile to avoid it.
            }
        }
        return instance;
    }
}
