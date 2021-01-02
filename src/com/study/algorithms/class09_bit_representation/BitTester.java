package com.study.algorithms.class09_bit_representation;

public class BitTester {
  // 获知第i位是否是1
  // method 1 - x & (1<<k) - not recommended
  // 将1左移k位，构造一个第k位是1的int。与x按位与。与0比较并判断
  public int bitTester_Deprecated(int x, int k) {
    // 优先级：==比位运算优先，所以要注意括号
    if ((x & (1 << k)) == 0) {
      return 0;
    } else {
      return 1;
    }
  }

  // method 2 - (x>>k) & 1 - recommended
  // x右移k位，与1按位与，可以得知第k位是什么！
  // 不需要if else，简单明了
  public int bitTester(int x, int k) {
    return (x >> k) & 1;
  }
}
