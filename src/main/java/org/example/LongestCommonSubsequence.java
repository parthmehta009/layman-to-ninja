package org.example;

import java.util.Arrays;

public class LongestCommonSubsequence {

    public static void main(String[] args) {

        // input
        char[] str1 = "ACD".toCharArray();
        char[] str2 = "CED".toCharArray();

        // recursive solution
        System.out.println("Recursive solution: " + lcs_rec(0,0, str1, str2));

        // Dynamic programming solution
        int[][] dp_arr = new int[str1.length][str2.length];
        Arrays.stream(dp_arr).forEach(a -> Arrays.fill(a, -1));
        System.out.println("DP solution: " + lcs_memoization(0, 0, str1, str2, dp_arr));
        Arrays.stream(dp_arr).forEach(a -> Arrays.fill(a, -1));
        System.out.println("DP Top down: " + lcs_topdown(str1.length-1, str2.length-1, str1, str2, dp_arr));
    }

    private static int lcs_rec(int i, int j, char[] str1, char[] str2) {
        // base case
        if(i >= str1.length || j >= str2.length)
            return 0;

        // when both ith and jth char matches
        if(str1[i] == str2[j])
            return 1+lcs_rec(i+1, j+1, str1, str2);

        // when ith and jth char does not match
        int left = lcs_rec(i+1, j, str1, str2);
        int right = lcs_rec(i, j+1, str1, str2);
        return Math.max(left, right);
    }

    // This method solves problem in BottomUp approach as we go from 0..n
    private static int lcs_memoization(int i, int j, char[] str1, char[] str2, int[][] dp) {
        // base case
        if(i >= str1.length || j >= str2.length)
            return 0;

        // if dp arr state has changed, use that value
        if(dp[i][j] != -1)
            return dp[i][j];

        // when both ith and jth char matches
        if(str1[i] == str2[j])
            return dp[i][j] = 1 + lcs_memoization(i+1, j+1, str1, str2, dp);

        // when ith and jth char does not match
        int left = lcs_memoization(i+1, j, str1, str2, dp);
        int right = lcs_memoization(i, j+1, str1, str2, dp);
        return dp[i][j] = Math.max(left, right);
    }

    private static int lcs_topdown(int i, int j, char[] str1, char[] str2, int[][] dp) {
        // base case
        if(i < 0 || j < 0)
            return 0;

        // if dp arr state has changed, use that value
        if(dp[i][j] != -1)
            return dp[i][j];

        // when both ith and jth char matches
        if(str1[i] == str2[j])
            return dp[i][j] = 1 + lcs_memoization(i-1, j-1, str1, str2, dp);

        // when ith and jth char does not match
        int left = lcs_memoization(i-1, j, str1, str2, dp);
        int right = lcs_memoization(i, j-1, str1, str2, dp);
        return dp[i][j] = Math.max(left, right);
    }

}
