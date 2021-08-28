package com.study.practice.Implementation;

// leetcode 297
// laicode 613
class MyCircularDeque {
    int[] array;
    int head;
    int tail;
    int size;
    int k;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        array = new int[k];
        head = 0;
        tail = k - 1; // the next pos to be inserted in
        size = 0;
        this.k = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    // 在前面操作时，index 0 相当于 index k（理解成上个数组的第k）
    public boolean insertFront(int value) {
        if (size == k) return false;
        if (head == 0) head = k;
        array[--head] = value;
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    // 在后面操作时，index k - 1 相当于 index -1 (如果理解成到下个数组的第
    public boolean insertLast(int value) {
        if (size == k) return false;
        if (tail == k - 1) tail = -1;
        array[++tail] = value;
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (size == 0) return false;
        head++;
        if (head >= k) head = 0;
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (size == 0) return false;
        tail--;
        if (tail < 0) tail = k - 1;
        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (size == 0) return -1;
        return array[head];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (size == 0) return -1;
        return array[tail];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        if (size == 0) return true;
        return false;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        if (size == k) return true;
        return false;
    }
}