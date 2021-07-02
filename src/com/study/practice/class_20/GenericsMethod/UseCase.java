package com.study.practice.class_20.GenericsMethod;


public class UseCase {
    public static <E> E findMiddle(E[] array) {
        int mid = array.length / 2;
        E midElem = array[mid];
        return midElem;
    }

    public static void main(String args[]) {
        String[] array = {"Adam", "Bob", "Cathy"}; 
        String mid = findMiddle(array);
        System.out.println(mid); 
   }
}
