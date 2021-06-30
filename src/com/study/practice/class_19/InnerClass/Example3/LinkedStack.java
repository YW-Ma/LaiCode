package com.study.practice.class_19.InnerClass.Example3;

public class LinkedStack {
    // Our static member interface; body omitted here...
    public static interface Linkable {}

    // The head of the list
    private Linkable head;    // head是一个特性

    // Method bodies omitted here
    public void push(Linkable node) {}
    public Linkable pop() {return null;}

    // This method returns an Enumeration object for this LinkedStack
    public java.util.Enumeration enumerate() { return new Enumerator(); }

    // Here is the implementation of the Enumeration interface,
    // defined as a member class.
    protected class Enumerator implements java.util.Enumeration { // Enumerator想要获得head，那么就要inner class
        Linkable current;
        // The constructor uses the private head field of the containing class
        public Enumerator() { current = head; }
        public boolean hasMoreElements() {  return (current != null); }
        public Object nextElement() {
            if (current == null)
                throw new java.util.NoSuchElementException();
            Object value = current;
            current = current.getNext();
            return value;
        }
    }
}
