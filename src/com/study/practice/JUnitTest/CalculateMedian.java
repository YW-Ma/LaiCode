package com.study.practice.JUnitTest;

import java.util.Arrays;

public class CalculateMedian {
//  public int getMedian(int[] array) {
//    // 注意，这里充满了bug
//    int n = array.length;
//    return array[n / 2];
//  }

  public double getMedian(int[] array) {
    if (array == null || array.length == 0) {
      return 0;
    }
    int n = array.length;
    Arrays.sort(array);
    if (n % 2 == 1) {
      return array[n / 2];
    } else {
      return array[n / 2] / 2.0 + array[(n - 1) / 2] / 2.0;
    }
  }
}
