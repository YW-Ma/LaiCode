package com.study.object_oriented_design.ood2.ParkingLotLaicode.Vehicles;

public abstract class Vehicle {
  public final String plate;
  public abstract VehicleSize getSize();

  Vehicle(String plate) {
    this.plate = plate;
  }
}

class Truck extends Vehicle {
  Truck(String plate) {
    super(plate);
  }

  @Override
  public VehicleSize getSize() {
    return VehicleSize.Compact;
  }
}


class Car extends Vehicle {
  Car(String plate) {
    super(plate);
  }

  @Override
  public VehicleSize getSize() {
    return VehicleSize.Compact;
  }
}
