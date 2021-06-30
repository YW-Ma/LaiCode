package com.study.practice.JUnitTest;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculateMedianTest {
  CalculateMedian cm;
  @Before
  public void setUp() {  // Before是在每个Test前运行，是每个Test自己独立的。
    cm = new CalculateMedian();
  }

  @Test
  public void getMedian_normal() {
    int[] arr = {1, 2, 3};
    double res = cm.getMedian(arr);
    assertEquals(res, 2, 0.00001); // 让assert（断言）判断
  }

  @Test
  public void getMedian_onlyOne() {
    int[] arr = {1};
    double res = cm.getMedian(arr);
    assertEquals(res, 1, 0.00001); // 让assert（断言）判断
  }

  @Test
  public void getMedian_evenSorted() {
    int[] arr = {1,2,3,4};
    double res = cm.getMedian(arr);
    assertEquals(res, 2.5, 0.00001); // 让assert（断言）判断
  }

  @Test
  public void getMedian_oddSorted() {
    int[] arr = {1,2,3,4,5};
    double res = cm.getMedian(arr);
    assertEquals(res, 3, 0.00001); // 让assert（断言）判断
  }

  @Test
  public void getMedian_OddUnsorted() {
    int[] arr = {1,5,3};
    double res = cm.getMedian(arr);
    assertEquals(res, 3, 0.00001); // 让assert（断言）判断
  }

  @Test
  public void getMedian_EvenUnsorted() {
    int[] arr = {1,5,2,3};
    double res = cm.getMedian(arr);
    assertEquals(res, 2.5, 0.00001); // 让assert（断言）判断
  }

  @Test
  public void getMedian_empty() {
    int[] arr = {};
    double res = cm.getMedian(arr);
    assertEquals(res, 0, 0.00001); // 让assert（断言）判断
  }

  @Test
  public void getMedian_MAX() {
    int[] arr = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
    double res = cm.getMedian(arr);
    assertEquals(res, Integer.MAX_VALUE, 0.00001); // 让assert（断言）判断
  }
}
