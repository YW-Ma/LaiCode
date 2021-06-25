package com.study.object_oriented_design.ood2.ParkingLotLaicode;

import com.study.object_oriented_design.ood2.ParkingLotLaicode.Vehicles.Vehicle;
import com.study.object_oriented_design.ood2.ParkingLotLaicode.Vehicles.VehicleSize;

public class ParkingSpot {
  final VehicleSize size;
  Vehicle currentVehicle;

  ParkingSpot(VehicleSize size) {
    this.size = size;
  }

  public boolean fit(VehicleSize size) {
    if (currentVehicle != null) {
      return false;
    }
    return size.getSize() <= this.size.getSize();
  }

  public boolean park(Vehicle v) {
    if (this.currentVehicle != null || !fit(v.getSize())) {
      return false;
    }
    this.currentVehicle = v;
    return true;
  }

  public Vehicle leave(String plate) {
    if (this.currentVehicle == null || !this.currentVehicle.plate.equals(plate)) {
      return null;
    }
    Vehicle v = this.currentVehicle;
    this.currentVehicle = null;
    return v;
  }

  public Vehicle getCurrentVehicle() {
    return this.currentVehicle;
  }
}
