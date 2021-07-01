package com.study.practice.class_19.Iterator;

import java.util.LinkedList;
import java.util.ListIterator;

public class ListIterators {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10; i ++ ){
            list.add(i);
        }

        // 在这里拿到list的listIterator
        for (ListIterator<Integer> it = list.listIterator(); it.hasNext(); ) {
            System.out.println("One step forward: " + it.next());  // 走一步
            if (it.hasNext()) {
                System.out.println("Another step forward:  " + it.next());  // 还能走就走一步然后退一步
                System.out.println("One step backward: " + it.previous());
            }
        }
    }
}
