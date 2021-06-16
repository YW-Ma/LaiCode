package com.study.practice.class16_charset_and_encoding;

public class IntegerPitfalls {

    public static void main(String[] args) {
        // intern pool (-128 ~ 127)                --> true
        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2);

        // no intern pool (只要new了就不行了)        -->  true
        Integer i3 = new Integer(10);
        Integer i4 = new Integer(10);
        System.out.println(i3 == i4);

        // NPE (auto unboxing)                     --> NPE  (一侧是primitive type，则wrapping class type这边会auto unboxing)
        Integer i5 = null;
        System.out.println(i5 == 1);

        // null 可以当做object，不会auto unboxing   --> true
        Integer i7 = null;
        System.out.println(i7 == null); // 但是不能 .equals()

        return;
    }
}
