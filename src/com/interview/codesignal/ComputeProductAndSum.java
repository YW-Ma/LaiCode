package com.interview.codesignal;

public class ComputeProductAndSum {
    public static void main(String[] args) {
        int num = 123456;
        int prod = 1;
        int sum = 0;
        while (num > 0) {
            prod = prod * (num % 10);
            sum = sum + (num % 10);
            num = num / 10;
        }
        
        System.out.println(prod - sum);
    }
}
