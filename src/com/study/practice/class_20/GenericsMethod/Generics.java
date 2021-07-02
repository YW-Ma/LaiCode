package com.study.practice.class_20.GenericsMethod;

public class Generics {
  // generic method printArray
  // 在return type之前，有一个 <XXX>, 因为最早要用到的地方，可能就是return type
  public static <E> void printArray(E[] inputArray) {
    // Display array elements
    for(E element : inputArray) {
      System.out.printf("%s", element);
    }
    System.out.println();
  }

  public static void main(String args[]) {
    // Create arrays of Integer, Double and Character
    Integer[] intArray = { 1, 2, 3, 4, 5 };
    Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
    Character[] charArray = { 'W', 'O', 'R', 'L', 'D' };

    System.out.println("Array integerArray contains:");
    printArray(intArray);   // pass an Integer array

    System.out.println("Array doubleArray contains:");
    printArray(doubleArray);   // pass a Double array

    System.out.println("Array characterArray contains:");
    printArray(charArray);   // pass a Character array
  }
}
