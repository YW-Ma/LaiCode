package com.study.practice.class_20.GenericsClass;

public class GenericClassExample {
    public static void main(String[] args) {
        Node<Integer> node = new Node<>(1);
        System.out.println(node.toString());
        node.setValue(2);
        int result = node.getValue(); // unboxing
    }
}

class Node<T> {
    private T value;

    public Node(T v) {
        value = v;
    }

    public String toString() {
        return value.toString();
    }

    public T getValue() { // 这里面的methods，就不再需要写<T>了，但是如果要多个的话，也可以有
        T result = value;
        return result;
    }

    public void setValue(T v) {
        value = v;
    }
}

