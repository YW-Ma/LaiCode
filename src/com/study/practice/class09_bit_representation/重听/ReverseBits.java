package com.study.practice.class09_bit_representation.重听;

public class ReverseBits {
    // Reverse bits of a 32-bit integer.
    //
    // Note: In different programming languages, integers might be implemented differently in terms of number of bits, signed, unsigned, etc. However it should not affect your implementation on this problem. In java, the type of input is long, but you just need to work on the last 32-bit and consider it as an unsigned 32-bit integer.
    //
    // Example 1:
    //
    // Input: 1234 (0b'00000000000000000000010011010010)
    //
    // Output: 1260388352 (0b'01001011001000000000000000000000)

    // 有两种思路可以解决问题，一个是merge sort思路，一个是string reversal思路。下面只写了string reversal的思路：
    public long reverseBits(long n) {
        int high = 31;
        int low = 0;
        while (high > low) {
            n = swap(n, high, low);
            high--;
            low++;
        }
        return n;
    }

    private long swap(long n, int high, int low) {
        // 如果两边一样，则不能xor，而是直接返回（XOR是toggle）
        if ((n >> high & 1L) == (n >> low & 1L)) {
            return n;
        }
        // 要用到xor，而不是傻乎乎地真的去交换
        n ^= 1L << high;
        n ^= 1L << low;
        return n;
    }
}
