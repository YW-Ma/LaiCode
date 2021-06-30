package com.study.object_oriented_design.ood2.ParkingLotLaicode;

import com.study.object_oriented_design.ood2.ParkingLotLaicode.Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

//Please design a simplified parking lot that have multiple levels and support different vehicle sizes.
//Notice that, this should be a very civilized parking lot, which means:
//For example, [compact cars should first try to park at compact spots], [then large spots.]
//You can use Log.print(String) to print a log.
//
//E.g.
//Log.print("hello world!");   /* output: hello world! */
//String str = "hello Laicode";
//Log.print(str);   /* output: hello Laicode */
//
//String str = "I love Laicode " + 2;
//Log.print(str);   /* output: I love Laicode 2 */
//Notice: Log.print currently takes only String as input parameter.
//
//It is strongly recommended to use only ASCII characters in log.
public class ParkingLot {
  List<Layer> layers;
  ParkingLot(int numOfLayers) {
    layers = new ArrayList<>();
    for (int i = 0; i < numOfLayers; i++) {
      layers.add(new Layer(100)); // 假设一层是100车位
    }
  }

  public boolean park(Vehicle v) {
    for (Layer layer : layers) {
      if (layer.park(v)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasSpot(Vehicle v) {
    for (Layer layer : layers) {
      if (layer.hasSpot(v)) {
        return true;
      }
    }
    return false;
  }

  public Vehicle leave(String plate) {
    for (Layer layer : layers) {
      Vehicle v = layer.leave(plate);
      if (v != null) {
        return v;
      }
    }
    return null;
  }
}
