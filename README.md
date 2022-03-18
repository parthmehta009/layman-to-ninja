# layman-to-ninja
List of coding problems

## Practice Problems

<details><summary>Fibonacci Numbers</summary>

```Java
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

```
</details>

<details><summary>Longest Common Subsequence</summary>

##### Description

> A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
> 
> For example, `"ace"` is a subsequence of `"abcde"`.
> 
> A common subsequence of two strings is a subsequence that is common to both strings.
>
> **Example**<br>
> **Input:** text1 = "abcde", text2 = "ace"
> **Output:** 3  
> **Explanation:** The longest common subsequence is "ace" and its length is 3.

Leetcode Link : [https://leetcode.com/problems/longest-common-subsequence/](https://leetcode.com/problems/longest-common-subsequence/)

##### Recursive Tree

[![](https://mermaid.ink/img/pako:eNp1VNFOwyAU_RWCD9OIZtC9rCYmk0t_QB8xBluqi21Zus7EOP9d2kLHsHYP2849l3s4B_qNc1NonOK3Vu3e0RPIBtlncynxhsORC5D4asQeLBZD1PECjFvoDAALvJwBdKCcADEwXqb_eaX2e9AlMp-6rdQOlduqSi_W67Jcr0luKtOmF8vl8m6ky8aJRjc398dOfWi02CyO6OEvzHuYOvEnPDd1bZqxzMOqxJfX9EriaTEeLAaWDX9h0cNuBgy4OXTIlOjVHJpiagEaTZh6fOFMGix6jnCGzSwrvA2cjkGFmTCXUxACi3MSNA5KsCioLAoKknFU0JIELf6QRLlwOlPoXePM7yCKDJgXHdkvqNc-5wid6ernCOb3F0UQFaIAjihzNswMy9CM9uEoJN6raFMi8ZbFKlwhY5HdIvlHFkO-Y06Z39RqDCuML7TTDl555pybrgire9UUaG-QaW7t8-yPmG2xxT714f4iToAAJYIAI8J-M5L5C33Xs06f8RIjhAmudVurbWFfSN89Yi_Bu661xKn9Waj2Q2LZ_FjeYVeoToti25kWp6Wq9ppgdejM41eTT8DIgq2yb7faoT-_UNVYiw)](https://mermaid.live/edit#pako:eNp1VNFOwyAU_RWCD9OIZtC9rCYmk0t_QB8xBluqi21Zus7EOP9d2kLHsHYP2849l3s4B_qNc1NonOK3Vu3e0RPIBtlncynxhsORC5D4asQeLBZD1PECjFvoDAALvJwBdKCcADEwXqb_eaX2e9AlMp-6rdQOlduqSi_W67Jcr0luKtOmF8vl8m6ky8aJRjc398dOfWi02CyO6OEvzHuYOvEnPDd1bZqxzMOqxJfX9EriaTEeLAaWDX9h0cNuBgy4OXTIlOjVHJpiagEaTZh6fOFMGix6jnCGzSwrvA2cjkGFmTCXUxACi3MSNA5KsCioLAoKknFU0JIELf6QRLlwOlPoXePM7yCKDJgXHdkvqNc-5wid6ernCOb3F0UQFaIAjihzNswMy9CM9uEoJN6raFMi8ZbFKlwhY5HdIvlHFkO-Y06Z39RqDCuML7TTDl555pybrgire9UUaG-QaW7t8-yPmG2xxT714f4iToAAJYIAI8J-M5L5C33Xs06f8RIjhAmudVurbWFfSN89Yi_Bu661xKn9Waj2Q2LZ_FjeYVeoToti25kWp6Wq9ppgdejM41eTT8DIgq2yb7faoT-_UNVYiw)

##### Code

```Java
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
```

> **Recursive solution:** O(n.2^m), where m is the length of the first string and n is the length of the second string.<br>
> **Dynamic Programming:** O(n*m)
</details>

<details>
<summary>Find the Duplicate Number</summary>

##### Description
> Given array of `n+1` numbers contains `[1,n]` inclusive and there is only single repeated number
>
> Example:
> Input: nums = [1,3,4,2,2]
> Output: 2

Leetcode link: [https://leetcode.com/problems/find-the-duplicate-number/](https://leetcode.com/problems/find-the-duplicate-number/)

##### Code

```Java
package org.example;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class FindDuplicateNumber {

    public static void main(String[] args) {
        int[] arr = {1,3,5,2,8,4,7,6,7};
        System.out.println("Optimal solution with linkedlist " +
                "slow and fast pointer :" + findDuplicate(arr));
        System.out.println("Brute force solution with sorting: " + findDuplicate_brute_force(arr));
        System.out.println("Hash frequency count solution: " + findDuplicate_hash_frequency(arr));
    }

    private static int findDuplicate(int[] arr) {
        int slowPtr = arr[0];
        int fastPtr = arr[0];

        do {    // first collision in loop
            slowPtr = arr[slowPtr];
            fastPtr = arr[arr[fastPtr]];
        } while (slowPtr != fastPtr);

        // meet at the start of loop to find duplicate
        fastPtr = arr[0];
        while (slowPtr != fastPtr) {
            slowPtr = arr[slowPtr];
            fastPtr = arr[fastPtr];
        }
        return slowPtr;
    }

    private static int findDuplicate_brute_force(int[] arr) {
        Arrays.sort(arr);
        for(int i=0; i<arr.length-1; i++) {
            if(arr[i] == arr[i+1]) {
                return arr[i];
            }
        }
        throw new NoSuchElementException("No duplicate found");
    }

    private static int findDuplicate_hash_frequency(int[] arr) {
        int[] freq = new int[arr.length];
        Arrays.fill(freq, 0);
        for(int num: arr) {
            freq[num]++;
        }
        for(int i=0; i<freq.length; i++) {
            if(freq[i] == 2)
                return i;
        }
        throw new NoSuchElementException("No duplicate found");
    }
}
```
</details>

<details>
<summary>
Sort an array of 0's 1's & 2's</summary>

##### Description

> Given array of n elements with 0, 1 and 2. Sort the array in single pass.
> 
> This is variation of Dutch National Flag Algorithm using which we can find solution in O(N) time.
> 
> Example:
> 
> Input: nums = [2,0,2,1,1,0]
> 
> Output: [0,0,1,1,2,2]

Leetcode link: [https://leetcode.com/problems/sort-colors/](https://leetcode.com/problems/sort-colors/)

##### Solution

Dutch National Flag Algorithm

![Color Sort Image - check in resource folder](https://share.sketchpad.app/22/07a-1ebb-1aea9d.png "Color Sort Algo")

##### Code

```Java
package org.example;

import java.util.Arrays;

public class SortColors {

    public static void main(String[] args) {
        int[] arr = {0,0,1,2,0,1,0,1,1,1,2,1,0};
        sortByBruteForce(arr);
        System.out.println();
        sortByCounting(arr);
        System.out.println();
        sortByDutchNationalFlagAlgo(arr);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // single pass solution
    private static void sortByDutchNationalFlagAlgo(int[] arr) {
        int low = 0;
        int high = arr.length-1;
        int mid = 0;
        while(mid <= high) {
            switch (arr[mid]) {
                case 0 : {
                    swap(arr, low, mid);
                    low++;
                    mid++;
                    break;
                }
                case 1 : {
                    mid++;
                    break;
                }
                case 2 : {
                    swap(arr, mid, high);
                    high--;
                }
            }
        }
        for(int i: arr) {
            System.out.print(i + " ");
        }
    }

    private static void sortByBruteForce(int[] arr) {
        Arrays.sort(arr);
        for(int i: arr) {
            System.out.print(i + " ");
        }
    }

    // double pass solution O(2N)
    private static void sortByCounting(int[] arr) {
        int cnt_0s = 0;
        int cnt_1s = 0;
        int cnt_2s = 0;
        for(int i: arr) {
            if(i == 0) {
                cnt_0s++;
                continue;
            }
            if(i == 1) {
                cnt_1s++;
                continue;
            }
            cnt_2s++;
        }
        while(cnt_0s != 0 || cnt_1s != 0 || cnt_2s != 0) {
            if(cnt_0s != 0) {
                cnt_0s--;
                System.out.print(0 + " ");
                continue;
            }
            if(cnt_1s != 0) {
                cnt_1s--;
                System.out.print(1 + " ");
                continue;
            }
            cnt_2s--;
            System.out.print(2 + " ");
        }
    }
}
```
</details>