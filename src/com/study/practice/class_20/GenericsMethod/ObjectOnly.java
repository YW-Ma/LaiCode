package com.study.practice.class_20.GenericsMethod;

public class ObjectOnly {
    public static <E> E findMiddle(E[] array) {
        int mid = array.length / 2;
        E midElem = array[mid];
        return midElem;
    }

    public static void main(String[] args) {
        Integer[] array = {1, 3, 5};
        int mid = findMiddle(array); // unbox
        System.out.println(mid);
    }
}
