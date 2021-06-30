package com.study.practice.class_19.InnerClass.Example2;

class People {
    String name;
    People(String name) {
        this.name = name;
    }
    class Bike {
        String owner = name;
        People p = People.this;
        void printName() {
            System.out.println(name == p.name);
        }
    }
}

class SharedBikeUser {
    String name;
    SharedBikeUser(String name) {
        this.name = name;
    }
    static class Bike {
        // String owner = name; // compile error 拿不到instance的field
        String key;
        void lock() {
        }
    }
}


