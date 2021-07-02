package com.study.practice.class_20.GenericsClass;

class MyPair<K, V> {

    private K key;
    private V value;

    public MyPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyPair [key=" + key + ", value=" + value + "]";
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

class Util {
    public static <K, V> boolean myequal(MyPair<K, V> p1,
                                         MyPair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }
}

public class GenericMethod2 {
    public static void main(String[] args) {
        MyPair<String, Integer> p1 = new MyPair<>("a", 1);
        MyPair<String, Integer> p2 = new MyPair<String, Integer>("b", 2);
        MyPair<String, Integer> p3 = new MyPair<>("a", 1);
        MyPair<Character, Integer> p4 = new MyPair<>('a', 1); // 用来试错

        System.out.println(p1 + " compares with " + p2 + " : " +
                Util.myequal(p1, p2));
        System.out.println(p1 + " compares with " + p3 + " : " +
                Util.myequal(p1, p3));
//        System.out.println(p1 + " compares with " + p4 + " : " +
//                Util.myequal(p1, p4));
    }
} 
