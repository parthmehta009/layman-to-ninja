package org.example;

import java.util.Arrays;

public class FibonacciNumber {

    public static void main(String[] args) {
        int n = 7;
        // recursive solution
        System.out.println(fib_recursive(n));

        // Dynamic programming
        int[] dp_arr = new int[n+1];
        Arrays.fill(dp_arr, -1);
        System.out.println(fib_memoization(n, dp_arr));
    }

    public static int fib_recursive(int n) {
        // base case
        if(n <= 1) return n;

        // recursive call
        return fib_recursive(n-1) + fib_recursive(n-2);
    }

    public static int fib_memoization(int n, int[] dp) {
        // base case
        if(n <= 1) return n;

        // if dp arr has value; use that
        if(dp[n] != -1) return dp[n];

        // Compute and Memoize the answer
        dp[n] = fib_memoization(n-1, dp) + fib_memoization(n-2, dp);
        return dp[n]; // first memoize; then return
    }

}
