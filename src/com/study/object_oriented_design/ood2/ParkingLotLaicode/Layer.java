package com.study.object_oriented_design.ood2.ParkingLotLaicode;

import com.study.object_oriented_design.ood2.ParkingLotLaicode.Vehicles.Vehicle;
import com.study.object_oriented_design.ood2.ParkingLotLaicode.Vehicles.VehicleSize;

import java.util.ArrayList;
import java.util.List;

public class Layer {
  final List<ParkingSpot> spots;
  Layer(int num) {
    spots = new ArrayList<>();
    for (int i = 0; i < num; i++) {
      if (i < num / 2) {
        spots.add(new ParkingSpot(VehicleSize.Large));
      } else {
        spots.add(new ParkingSpot(VehicleSize.Compact));
      }
    }
  }

  public boolean park(Vehicle v) {
    for (ParkingSpot spot : spots) {
      if (spot.park(v)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasSpot(Vehicle v) {
    for (ParkingSpot spot : spots) {
      if (spot.fit(v.getSize())) {
        return true;
      }
    }
    return false;
  }

  public Vehicle leave(String plate) {
    for (ParkingSpot spot : spots) {
      if (spot.getCurrentVehicle().plate.equals(plate)) {
        return spot.leave(plate);
      }
    }
    return null;
  }
}
