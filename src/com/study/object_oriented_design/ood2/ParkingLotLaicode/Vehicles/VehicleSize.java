package com.study.object_oriented_design.ood2.ParkingLotLaicode.Vehicles;

public enum VehicleSize {
  // 这里相当于让Java帮我们构造几个元素
  Compact(0), Large(1);

  // 下面相当于描述上述每个元素的结构：他们具有一个size，还能getSize。size是final从constructor传入。
  public final int size;
  private VehicleSize(int size) { // enum 的constructor必须是private。它会把enum每个元素自动创建一个，我们直接用。
    this.size = size;
  }
  public int getSize() {
    return size;
  }
}
