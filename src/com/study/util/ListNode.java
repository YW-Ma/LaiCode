package com.study.util;

public class ListNode {
  public int value;
  public int val;
  public int key;
  public ListNode next;

  public ListNode(int value) {
    this.value = value;
    next = null;
  }

  @Override
  public String toString() {
    return "ListNode{" +
            "value=" + value +
            ", next=" + next +
            '}';
  }
}
