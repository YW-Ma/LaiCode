package com.study.practice.class_19.Enum;

public enum VehicleSize {
  Compact(1), Regular(2), Large(3);
  private final int size; // enum可以有自己fields

  VehicleSize(int size) {
    this.size = size;
  } // enum可以有自己methods

  public int getSize() {
    return size;
  }
}
