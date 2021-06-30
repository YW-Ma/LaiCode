package com.study.practice.class_19.InnerClass.Example1;

class OuterClass{
    private static String msg1 = "Static Message";
    private String msg2 = "Non-Static Message";

    public static class NestedStaticClass{
        // Only static members of Outer class is directly accessible in nested
        // static class
        public void printMessage() {
            System.out.println("Message from nested static class: " + msg1);
            System.out.println("Use an instance to get msg2: " + new OuterClass().msg2);
            // System.out.println("Message from nested static class: " + msg2); --> Non-static field 'msg2' cannot be referenced from a static context
        }

        public static void printOut1() {}
        public void printOut2() {}
    }

    public class InnerClass{
        // Both static and non-static members of Outer class are accessible in
        // this Inner class
        public void display(){
            System.out.println("Message from non-static nested class: "+ msg1);
            System.out.println("Message from non-static nested class: "+ msg2);
        }

        // Can you declare a static method here?
        //     static method can only be declared in a (static class) or a (top-level class)
        //    【static属于InnerClass这个类，但是InnerClass已经是个"特性"了，不可以再给予一个"共性"。】
        // public static void printOut1() {} // compiler error: 不能给有特性的东西加入共性
        public void printOut2() {}

        public OuterClass getOuterClass () {
            return OuterClass.this;
        }
    }
    public static void printOut1() {}
    public void printOut2() {}
}
