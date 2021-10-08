package com.interview.codesignal;

public class WaysOfSplitting {
    public static void main(String[] args) {
        System.out.println();
    }
    
    /*
    Given a string S, Count number of ways of splitting S into 3 non-empty a,b,c
    such that a+b, b+c, c+a are all different.
    
    ex. input:xzxzx    output: 5,
        x, z, xzx    a+b == xz,  b+c == zxzx,   c+a == xzxx
        x, zx, zx
        xz, x, zx
        xz, xz, x
        xzx, z, x
        
        直接暴力就行了，选定第二个str的开头，尝试第三个str的开头。 然后移动第二个str的开头。
        for-for-loop
        n   (n --> 1)
        n * (1 + n) / 2 --> O(n^2)
    * */
    
    public int waysOfSplitting(String str) {
        return 0;
    }
}
