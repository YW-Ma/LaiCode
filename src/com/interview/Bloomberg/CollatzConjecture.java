package com.interview.bloomberg;

public class CollatzConjecture {
    // Collatz猜想：
    // The Collatz Conjecture says if you take a positive integer N and repeatedly set either N=N/2 (if it's even) or N=3N+1 (if it's odd), N will eventually be 1.
    // 5 -> 16 -> 8 -> 4 -> 2 -> 1 (5 steps).
    // Given N, how many steps does it take to reach 1?
    
    // 观察数据，实际上只要变成偶数，那么剩下的次数就是 log2(n)了，比如16->8->4->2->1 是4 steps, log2(16) = 4
    
    public static void main(String[] args) {
        System.out.println(collatz1(5));
        System.out.println(collatz2(5));
    }
    
    public static int collatz1(int num) {
        int step = 0;
        while (num != 1 && num > 0) {
            if (num % 2 == 0) {
                num = num / 2;
            } else {
                num = 3 * num + 1;
            }
            step++;
        }
        return step;
    }
    
    public static int collatz2(int num) {
        int step = 0;
        while (num != 1 && num % 2 != 0) {
            num = 3 * num + 1;
            step++;
        }
        return step + log2(num);
    }

    private static int log2(int N) {
        // 注意Java的log是ln (base e), 要用换底公式： log10(n)/log10(2) = log2(n)  change the base of logarithms to 2.
        return (int) (Math.log(N) / Math.log(2));
    }
}
