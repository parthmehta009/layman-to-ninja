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

Exxplnation video: [https://www.youtube.com/watch?v=32Ll35mhWg0&ab_channel=takeUforward](https://www.youtube.com/watch?v=32Ll35mhWg0&ab_channel=takeUforward)

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

Explnation video: [https://www.youtube.com/watch?v=oaVa-9wmpns&t=412s&ab_channel=takeUforward](https://www.youtube.com/watch?v=oaVa-9wmpns&t=412s&ab_channel=takeUforward)

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

<details>
<summary>
Find the repeating and the missing number
</summary>

##### Description

> Given an unsorted array of size n. Array elements are in the range from 1 to n. One number from set {1, 2, ???n} is missing and one number occurs twice in the array. Find these two numbers.
> 
> Example :
> 
> Input: arr[] = {4, 3, 6, 2, 1, 1}
> 
> Output: Missing = 5, Repeating = 1

GFG Problem link: [https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/](https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/)

Explanation video: [https://www.youtube.com/watch?v=5nMGY4VUoRY&ab_channel=takeUforward](https://www.youtube.com/watch?v=5nMGY4VUoRY&ab_channel=takeUforward)

##### Solution

Method 1 : Sorting

Approach:
Sort the input array. Traverse the array and check for missing and repeating.

Time Complexity: `O(nLogn)`

Note that after sorting, to find missing and repeating number in single pass is not that simple and intuitive. Do check for corner cases. Check the implementation and verify if that indeed find correct result.

![Repeating and missing number algo - check resource folder](https://share.sketchpad.app/22/89a-49a9-d4d5c0.png "Repeating and MissingNumber_sort image")

Code:

Input: `int[] arr = {4, 3, 6, 2, 1, 1};`

```Java
    private static void printTwoNumberBySorting(int[] arr) {
        System.out.println("Print by sorting");
        Arrays.sort(arr);
        for(int idx=1,arr_idx=0 ; arr_idx < arr.length;) {
            if(arr[arr_idx] != idx) {
                if(arr[arr_idx] < idx) {
                    System.out.println("Repeating Number: " + arr[arr_idx]);
                    arr_idx++;
                } else {
                    System.out.println("Missing Number: " + idx);
                    idx++;
                }
            } else {
                idx++;
                arr_idx++;
            }
        }
    }
```

Method 2: Print by Hash Frequency- counting

Time Complexity: O(n)

Auxiliary Space: O(n)

```Java
    private static void printTwoNumberByHashFrequency(int[] arr) {
        System.out.println("Print by hash frequency");
        int[] freq_arr = new int[arr.length+1];
        Arrays.fill(freq_arr, 0);
        for(int num: arr) {
            freq_arr[num]++;
        }
        for(int i=1; i<freq_arr.length; i++) {
            if(freq_arr[i] == 2)
                System.out.println("Repeating Number: " + i);
            if(freq_arr[i] == 0)
                System.out.println("Missing Number: " + i);
        }
    }
```

Method 3: Solve by equation

Approach:

- Let `x` be the missing and `y` be the repeating element.
- Get the sum of all numbers using formula `S = n(n+1)/2`
- Get sum of all number squares `S^2 = n(n+1)(2n+1)/6`
- The above two steps give us two equations, we can solve the equations and get the values of `x` and `y`.

In actual `arr` sum `x` will not be there and `y` will be there twice.

suppose give `arr` = `{2, 3, 3, 1, 4}` => 3 is repeating and 5 is missing 

So, `x - y` = `S - arr_sum` ((1+2+3+4+5) - (2+3+3+1+4))
`x - y` = 5 - 3 = 2

`x^2 - y^2` = `S^2 - arr_square_sum` ((1^2+2^2+3^2+4^2+5^2) - (2^2+3^2+3^2+1^2+4^2))
`(x+y)(x-y)` = 5^2 - 3^2 = 25 - 9 = 16

we can replace `x-y` value in above equation

`x+y` = 16/2 = 8

now,

`x-y` = 2

`x+y` = 8

`2x` = 10, `x` = 5, `y` = 3

Time Complexity: `O(n)`

Code

```Java
    private static void printTwoNumberByEquation(int[] arr) {
        System.out.println("Print by equation");
        int n = arr.length;
        long sum = ((long) n *(n+1))/2;
        long square_sum = (n*(n+1)*((2L *n)+1))/6;

        long arr_sum = Arrays.stream(arr).sum();
        long arr_square_sum = Arrays.stream(arr).map(num -> num * num).sum();

        long x_min_y = sum - arr_sum;
        long x_plus_y = (square_sum - arr_square_sum)/x_min_y;

        long x = (x_plus_y + x_min_y)/2;
        long y = x - x_min_y;
        System.out.println("Repeating Number: " + y);
        System.out.println("Missing Number: " + x);
    }
```

Method 4 : Use XOR operation

> XOR operation return `true` only if either condition is true, not both true and not both false. So, 1 and 0 gives 1 other is 0 and 1 gives 1
>
> Also note, number's XOR with self gives `0` as all bit cancel each other. `3^3 = 0`

Approach:

- Let x and y be the desired output elements.
- Calculate XOR of all the array elements.

`XOR = arr[0]^arr[1]^arr[2]???..arr[n-1]`

- XOR the result with all numbers from 1 to n

`XOR = XOR^1^2^???..^n`

- In the result `XOR`, all elements would nullify each other except `x` and `y`. All the bits that are set in `XOR` will be set in either `x` or `y`. So if we take any set bit (We have chosen the rightmost set bit in code) of `XOR` and divide the elements of the array in two sets ??? one set of elements with same bit set and other set with same bit not set. By doing so, we will get `x` in one set and `y` in another set. Now if we do XOR of all the elements in first set, we will get `x`, and by doing same in other set we will get `y`.
- We put array elements in 2 buckets based on set bit and then again put all elements from 1...n in 2 buckets. So, each bucket cancel all elements except `x` and `y`. As same number XOR cancel each other and gives 0

First 2 XOR operation is just to find set bit position and we took right most set bit

Time Complexity: `O(5n)` as we iterate 5 times i.e `O(n)`

```Java
    private static void printTwoNumberByXOR(int[] arr) {
        System.out.println("Print by XOR");
        int xor_all = 0;

        // get XOR of all arr elements
        for (int i : arr) {
            xor_all ^= i;
        }

        // XOR the above with numbers from 1 to n
        for(int i=1; i<=arr.length; i++) {
            xor_all ^= i;
        }

        int set_bit_no = xor_all & -xor_all;
        int x = 0;
        int y = 0;

        // now divide arr elements in two baskets based on set bit
        for (int j : arr) {
            if ((j & set_bit_no) != 0) {
                x ^= j;
            } else {
                y ^= j;
            }
        }

        for(int i=1; i<=arr.length; i++) {
            if((i & set_bit_no) != 0) {
                x ^= i;
            } else {
                y ^= i;
            }
        }

        // to figure out which one is missing and which one is repeating
        int x_count = 0;
        for(int num: arr) {
            if(num == x) {
                x_count++;
            }
        }
        if(x_count == 2) {
            System.out.println("Repeating Number: " + x);
            System.out.println("Missing Number: " + y);
        } else {
            System.out.println("Repeating Number: " + y);
            System.out.println("Missing Number: " + x);
        }
    }
```

</details>

<details>
<summary>Merge two sorted array in same two array</summary>

##### Description

> Given two sorted arrays, we need to merge them in O((n+m)*log(n+m)) time with O(1) extra space into a sorted array, when n is the size of the first array, and m is the size of the second array.
> 
> Exmple:
> 
> int[] arr1 = {34,35,36,37};
> 
> int[] arr2 = {3,4,5};
> 
> Output: 
> 3 4 5 34
> 
> 35 36 37

GFG problem link: [https://www.geeksforgeeks.org/efficiently-merging-two-sorted-arrays-with-o1-extra-space/](https://www.geeksforgeeks.org/efficiently-merging-two-sorted-arrays-with-o1-extra-space/)

Explanation video: [https://www.youtube.com/watch?v=hVl2b3bLzBw&t=431s&ab_channel=takeUforward](https://www.youtube.com/watch?v=hVl2b3bLzBw&t=431s&ab_channel=takeUforward)

##### Solution

Approach 1 : Selection sort on both array. As both are sorted; compare element from first array with first element of second array and swap if required and sort second array after every swap to keep both array sorted all the time.

Approach 2 : Shellsort - h-sorted array. Here we used h = n/2 and for odd n value we kept h = n/2 + n%2. Shellsort is extension of Insertion sort only, here we try to compare with h distance element instead of next element in array to reduce inversion pair in array. 

##### Code

```Java
package org.example;

public class MergeTwoArrWithConstantSpace {

    public static void main(String[] args) {
        int[] arr1 = {34,35,36,37};
        int[] arr2 = {3,4,5};
        sortTwoArrWithInsertionSort(arr1, arr2);
        printArray(arr1, arr2);
        int[] arr3 = {34,35,36,37};
        int[] arr4 = {3,4,5};
        sortTwoArrWithGapMethod_shellsort(arr3, arr4);
        printArray(arr3, arr4);
    }

    private static void sortTwoArrWithGapMethod_shellsort(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;

        int h = (n+m)/2 + (n+m)%2;
        int i, j;
        while(h > 0) { // h-sort the array to swap long distance element

            // compare ele in first arr
            for(i = 0; i+h < n; i++) {
                if(arr1[i] > arr1[i+h]) {
                    int tmp = arr1[i];
                    arr1[i] = arr1[i+h];
                    arr1[i+h] = tmp;
                }
            }

            // compare ele in second arr
            for(j = h>n ? h-n : 0; i < n && j < m; i++,j++) {
                if(arr1[i] > arr2[j]) {
                    int tmp = arr1[i];
                    arr1[i] = arr2[j];
                    arr2[j] = tmp;
                }
            }

            // if we haven't checked all second arr elements
            if(j < m) {
                for(j = 0; j+h < m; j++) {
                    if(arr2[j] > arr2[j+h]) {
                        int tmp = arr2[j];
                        arr2[j] = arr2[j+h];
                        arr2[j+h] = tmp;
                    }
                }
            }
            h = h == 1 ? 0 : h/2 + h%2; // return 0 for h == 1 else infinite loop
        }
    }

    private static void sortTwoArrWithInsertionSort(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int m = arr2.length;

        // apply Insertion sort logic
        for(int i = 0; i < n; i++) {
            if(arr1[i] > arr2[0]) { // comparing only with first ele as both arrays are sorted
                int tmp = arr1[i];
                arr1[i] = arr2[0];
                arr2[0] = tmp;
            }

            // sort second array if not sorted
            for(int j=1; j < m && arr2[j] < arr2[j-1]; j++) {
                int tmp = arr2[j];
                arr2[j] = arr2[j-1];
                arr2[j-1] = tmp;
            }
        }
    }

    private static void printArray(int[] arr1, int[] arr2) {
        // print the sorted arr 1
        for(int num: arr1) {
            System.out.print(num + " ");
        }
        System.out.println();
        // print the sorted arr 2
        for(int num: arr2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
```
</details>

<details>
<summary>
Maximum Subarray Sum - Kadane's Algo
</summary>

##### Description

> Given an integer array `nums`, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
>
> A subarray is a contiguous part of an array.
> 
> Exmaple
> 
> Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
> 
> Output: 6
> 
> Explanation: [4,-1,2,1] has the largest sum = 6.

Leetcode problem link: [https://leetcode.com/problems/maximum-subarray/](https://leetcode.com/problems/maximum-subarray/)

Explanation video : [https://www.youtube.com/watch?v=w_KEocd__20&t=167s&ab_channel=takeUforward](https://www.youtube.com/watch?v=w_KEocd__20&t=167s&ab_channel=takeUforward)

##### Solution

![Kadane's Algorithm](src/main/resources/img/Kadanes-algo.jpeg "Kadane's Algorithm")

##### Code

```Java
package org.example;

public class MaximumSubArraySum {

    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("Maximum subarray sum, Kadane's method: " +
                maxSubArraySum_Kadanes_algo(arr));
        System.out.println("Brute force approach: " +
                maxSubArraySum_bruteforce(arr));
    }

    private static int maxSubArraySum_Kadanes_algo(int[] arr) {
        int sum = 0;
        int max = arr[0];
        for(int num: arr) {
            sum += num;
            if(sum > max) max = sum;
            if(sum < 0) sum = 0;
        }
        return max;
    }

    private static int maxSubArraySum_bruteforce(int[] arr) {
        int max = 0;
        for(int i=0; i<arr.length; i++) {
            int sum = 0;
            for(int j=i; j<arr.length; j++) {
                sum += arr[j];
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}
```
</details>

<details>
<summary>Merge Overlapping Intervals</summary>

##### Description
> Given an array of `intervals` where `intervals[i] = [start, end]`, merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1

> Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
> 
> Output: [[1,6],[8,10],[15,18]]
> 
> Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2

> Input: intervals = [[1,4],[4,5]]
> 
> Output: [[1,5]]
> 
> Explanation: Intervals [1,4] and [4,5] are considered overlapping.

Leetcode problem link: [https://leetcode.com/problems/merge-intervals/](https://leetcode.com/problems/merge-intervals/)

Explanation video : [https://www.youtube.com/watch?v=2JzRBPFYbKE&ab_channel=takeUforward](https://www.youtube.com/watch?v=2JzRBPFYbKE&ab_channel=takeUforward)

##### Code

```Java
package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public static void main(String[] args) {
        int[][] input = {{1,4}, {2,6}, {11,12}, {9,11}, {15,18}, {16,17}};
        int[][] output = mergeInterval(input);
        for(int[] arr : output) {
            System.out.print("(" + arr[0] + "," + arr[1] + ") ");
        }
    }

    private static int[][] mergeInterval(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        if(intervals == null || intervals.length == 0) {
            return result.toArray(new int[0][]);
        }

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int start = intervals[0][0];
        int end = intervals[0][1];

        for(int[] arr: intervals) {
            if(arr[0] <= end)
                end = Math.max(end, arr[1]);
            else {
                result.add(new int[]{start, end});
                start = arr[0];
                end = arr[1];
            }
        }
        result.add(new int[]{start, end}); // add the last merged pair
        return result.toArray(new int[0][]);
    }
}
```
</details>

<details>
<summary>Set Matrix Zeros</summary>

##### Description

> Given an `m x n` integer matrix `matrix`, if an element is `0`, set its entire row and column to `0`'s.
>
> Example:
> 
> Input: matrix = 
> 
> [[1,1,1],
> 
>  [1,0,1],
> 
>  [1,1,1]]
> 
> Output: 
> 
> [[1,0,1],
> 
> [0,0,0],
> 
> [1,0,1]]

Leetcode problem link: [https://leetcode.com/problems/set-matrix-zeroes/](https://leetcode.com/problems/set-matrix-zeroes/)

Explanation video: [https://www.youtube.com/watch?v=M65xBewcqcI&ab_channel=takeUforward](https://www.youtube.com/watch?v=M65xBewcqcI&ab_channel=takeUforward)

##### Solution

1) Brute-force approach:

- traverse the matrix and check if any element is `0` - set entire row and column elements as -1.
- do not change any other `0` to `-1` while marking row and column
- iterate again and update all `-1` entry to `0`
- we assume given input matrix contains only `+ve` values else instead of `-1` choose any other value which is not present in matrix

![Set Matrix Zero - Brute force solution](src/main/resources/img/SetMatrixZero_bruteForce.png "Brute-force Solution")

Time Complexity: `O(m*n) * O(m+n)`

Space Complexity: O(1)

2) Brute-force optimization approach(with extra space):

 - to optimize further, we take 2 arrays of size rows and columns respectively. 
 - approach will be similar to the solution 1, traverse the `matrix` and for every `0` entry in matrix update `row` and `column` array's respective entry with `-1`.
 - here, we choose `-1` and not `0` as to avoid `row` and `column` array initialization with dummy values. As Java initialize both with `0`s.
 - traverse matrix again and for every index check respective `row` and `column` array entry, if any of them contain value `-1`, set matrix value as `0`

![Set Matrix Zero - Brute force optimal](src/main/resources/img/SetMatrixZero-bruteforce-optimal.png "Brute-force optimization solution")

Time Complexity: `O(n*m) + O(n*m)` = `O(2*n*m)` = `O(n*m)`

Space Complexity: `O(n) + O(m)` for taking 2 extra array

3) Optimal Approach (take those extra space inside matrix 1st row,column):

![Set Matrix Zero - Optimal](src/main/resources/img/SetMatrixZero_optimal.png "Optimal Solution")

Time Complexity: `O(2*n*m)` as we traverse twice

Space Complexity: `O(1)`

##### Code

```Java
package org.example;

import java.util.Arrays;

public class SetMatrixZeroes {

    public static void main(String[] args) {
        int[][] arr_input1 = {
                {1,1,1},
                {1,0,1},
                {1,1,1}
        };
        int[][] arr_input2 = Arrays.stream(arr_input1).map(int[]::clone).toArray(int[][]::new);
        int[][] arr_input3 = Arrays.stream(arr_input1).map(int[]::clone).toArray(int[][]::new);
        setZerosBruteforce(arr_input1);
        printMatrix(arr_input1);
        setZeroBruteforce_optimal(arr_input2);
        printMatrix(arr_input2);
        setZeros(arr_input3);
        printMatrix(arr_input3);
    }

    private static void setZeros(int[][] matrix) {
        boolean top_corner_flag = false;
        for(int i=0; i<matrix.length; i++) {
            if(matrix[i][0] == 0) top_corner_flag = true;
            for(int j=1; j<matrix[i].length; j++)
                if (matrix[i][j] == 0)
                    matrix[i][0] = matrix[0][j] = 0;
        }

        // traverse in reverse order
        for(int i=matrix.length-1; i>=0; i--) {
            for (int j=matrix[i].length-1; j>=1; j--)
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            if(top_corner_flag) matrix[i][0] = 0;
        }
    }

    private static void setZeroBruteforce_optimal(int[][] matrix) {
        int[] row = new int[matrix.length];
        int[] column = new int[matrix[0].length]; // atleast 1 row will be there as per given constraints

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    row[i] = -1; column[j] = -1;
                }
            }
        }

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(row[i] == -1 || column[j] == -1)
                    matrix[i][j] = 0;
            }
        }
    }

    private static void setZerosBruteforce(int[][] matrix) {
        for(int i=0; i< matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j] == 0) {
                    for(int row=0; row<matrix.length; row++) {
                        if(matrix[row][j] != 0) matrix[row][j] = -1;
                    }
                    for(int col=0; col<matrix[i].length; col++) {
                        if(matrix[i][col] != 0) matrix[i][col] = -1;
                    }
                }
            }
        }

        for(int i=0; i<matrix.length; i++)
            for(int j=0; j<matrix[i].length; j++)
                if(matrix[i][j] == -1) matrix[i][j] = 0;
    }

    private static void printMatrix(int[][] arr) {
        for(int[] a: arr) {
            for(int num: a) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
```
</details>

<details>
<summary>Pascal's Triangle</summary>

##### Description

> Given an integer numRows, return the first numRows of Pascal's triangle.
>
> In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

![Pascal's Triangle](https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif "Pascal's Triangle wiki image")

##### Code

```Java
package org.example;

public class PascalTriangle {

    public static void main(String[] args) {
        int[][] pascal = pascalTriangle(5);
        for(int[] arr: pascal) {
            for(int ele: arr) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }

    private static int[][] pascalTriangle(int num) {
        int[][] result = new int[num][];
        int[] row, prev_row = new int[1];
        for(int i=0; i<num; i++) {
            row = new int[i+1];
            for(int j=0; j<=i; j++) {
                if(j==0 || j==i)
                    row[j] = 1;
                else
                    row[j] = prev_row[j-1] + prev_row[j];
            }
            prev_row = row;
            result[i] = row;
        }
        return result;
    }
}
```
</details>

<details>
<summary>Print All Permutation</summary>

##### Description

> Given input string or Input list or Input array - print all permutation of it.
>
> Example:
> Input: "ABC"
>
> Output:
> 
> ABC
ACB
BAC
BCA
CAB
CBA

##### Explanation:

Here is how we do it manually for input "DOG".
- Let???s take the first letter of our string input it in our memory: `D`
- Now, let???s take the remaining letters: `O`, `G`
- Let???s make one word by taking the remaining letters, and then add them to our first letter, one by one: `D` + `OG`, and `D` + `GO`. So we now have 2 permutations: `DOG` and `DGO`.
- Let???s move on to the second letter: `O`, and take the remaining letter and set them aside: `D` and `G`.
- Let???s make another permutation of those: `O` + `DG` and `O` + `GD`. Now we have 4 permutations: `DOG`, `DGO`, `ODG`, `OGD`.
- Now let???s tackle the last letter: `G`. And set aside the remaining letters: `D` and `O`.
- Let???s make permutation of that: `G` + `DO` and `G` + `OD`. Now we have 6 permutations: `DOG`, `DGO`, `ODG`, `OGD`, `GDO`, `GOD`.
- We???d now just have to return all these combinations in an array.

Above explanation quoted from blog: [https://medium.com/swlh/step-by-step-guide-to-solving-string-permutation-using-recursion-in-javascript-a11d098d5b83](https://medium.com/swlh/step-by-step-guide-to-solving-string-permutation-using-recursion-in-javascript-a11d098d5b83)

Let's implement same in Java for String input. As Strings are immutable in Java so any concat operation on String will generate new String. So, in case of Recursive call we can treat it as local parameter variable. Check below 2 implementation, #1 with String input and #2 with List<Integer>. In #2 we need create new object of `List<Integer>` for every recursive call.

1) String implementation

```Java
permute_str("", "ABC");
```

```Java
private static void permute_str(String prefix, String str) {
    int n = str.length();
    if(n == 0) System.out.println(prefix);
    else {
        for(int i=0; i<n; i++)
            permute_str(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
    }
}
```

2) List<Integer> as param (can be any list of object)

```Java 
permute_list(new ArrayList<>(), new ArrayList<>(Arrays.asList(1,2)));
```

```Java
private static void permute_list(List<Integer> prefixList, List<Integer> list) {
    int n = list.size();
    if(n == 0) System.out.println(Arrays.toString(prefixList.toArray()));
    else {
        for(int i=0; i<n; i++) {
            // create new lists for next iteration
            List<Integer> newPrefList = new ArrayList<>(prefixList);
            List<Integer> newList = new ArrayList<>(list);

            newPrefList.add(newList.get(i));
            newList.remove(i);

            permute_list(newPrefList, newList);
        }
    }
}
```

Above #2 is same as #1 implementation, we take one element common and call recursive method to fetch permutation of `n-1` elements. Note, we created new list for each recursive call. 

This implementation are not the most efficient one in terms of time and space complexity but that is not our intention for above code. Above is to develop intuition about recursive call inside loop iteration.

Also read below stackoverflow answer:

> To use recursion effectively in design, you solve the problem by assuming you've already solved it. The mental springboard for the current problem is "if I could calculate the permutations of `n-1` characters, then I could calculate the permutations of `n` characters by choosing each one in turn and appending the permutations of the remaining `n-1` characters, which I'm pretending I already know how to do".
> 
> Then you need a way to do what's called "bottoming out" the recursion. Since each new sub-problem is smaller than the last, perhaps you'll eventually get to a sub-sub-problem that you REALLY know how to solve.
> 
> In this case, you already know all the permutations of ONE character - it's just the character. So you know how to solve it for `n=1` and for every number that's one more than a number you can solve it for, and you're done. This is very closely related to something called mathematical induction.

Link to answer: [https://stackoverflow.com/a/7540364](https://stackoverflow.com/a/7540364)

Above 2 implementation if we stare at the code for some time eventually it will be obvious. But if you google for 'print all permutation' implementation, most quoted solution is as below:

```Java 
permute(Arrays.asList(1,2), 0);
```

```Java
private static void permute(List<Integer> list, int k) {
    for(int i=k; i<list.size(); i++) {
        Collections.swap(list, i, k);
        permute(list, k+1);
        Collections.swap(list, k, i);
    }
    if(k == list.size()-1) {
        System.out.println(Arrays.toString(list.toArray()));
    }
}
```

Above code is doing same as the algorithm steps mentioned above, but it does that by swapping elements on the same list (we could use Array also).

Another thing to note here, `swap()` call before permutation is to actually swap the given list element and `swap()` call after permutation call is to undo the above first `swap()` done before so that for next iteration - **for the same recursive call level** - we get back the input list as given on the initial invocation.

##### Recursive call walkthrough with println statements

    ============WE ARE IN RECURSION========= with k = 0======
    ===========ITERATION=========== i = 0
    
        ============WE ARE IN RECURSION========= with k = 1======
        ===========ITERATION=========== i = 1

            ============WE ARE IN RECURSION========= with k = 2======
            Out of the for loop...
            ********Remove Call Stack=******** with k = 2***********

        ============WE ARE BACK IN RECURSION========= with k = 1======
        
        Out of the for loop...
        Printing Arr : ########## OUTPUT ###########
        [1, 2] 
        ********Remove Call Stack=******** with k = 1***********
        
    ============WE ARE BACK IN RECURSION========= with k = 0======
    
    ===========ITERATION=========== i = 1
    BEFORE permute: swap i: 1, k: 0 - arr :[2, 1]
        ============WE ARE IN RECURSION========= with k = 1======
        ===========ITERATION=========== i = 1
        
            ============WE ARE IN RECURSION========= with k = 2======
            Out of the for loop...
            ********Remove Call Stack=******** with k = 2***********
            
        ============WE ARE BACK IN RECURSION========= with k = 1======

        Out of the for loop...
        Printing Arr : ########## OUTPUT ###########
        [2, 1]
        ********Remove Call Stack=******** with k = 1***********

    ============WE ARE BACK IN RECURSION========= with k = 0======
    AFTER permute: swap i: 1, k: 0 - arr :[1, 2]
    Out of the for loop...
    ********Remove Call Stack=******** with k = 0***********

Above walk-through is for input `[1,2]`. Note how we are swapping elements before generating output and later swap it back before next iteration, which you can see at last line, code reverts `[2,1]` to `[1,2]`. That can be observed, and it will more obvious if you run code with input `[1,2,3]`.

Recursion is something that computers can do but Human can not and that's why it is very difficult to wrap our head around some code like above. At least I could not. We can prove it works, and we can debug and check that it works but when writing any new solution for any problem it is not very intuitive to come up with recursive solution.

Code with above println statements:

```Java 
permute(Arrays.asList(1,2), 0);
```

```Java
private static void permute(List<Integer> list, int k) {
    System.out.println("============WE ARE IN RECURSION========= with k = " + k + "======");
    for(int i=k; i<list.size(); i++) {
        System.out.println("===========ITERATION=========== i = " + i);
        Collections.swap(list, i, k);
        System.out.println(i != k ?
                "BEFORE permute: swap i: " + i + ", k: " + k + " - arr :" + Arrays.toString(list.toArray())
                : "");
        permute(list, k+1);
        System.out.println("============WE ARE BACK IN RECURSION========= with k = " + k + "======");
        Collections.swap(list, k, i);
        System.out.println(i != k ?
                "AFTER permute: swap i: " + i + ", k: " + k + " - arr :" + Arrays.toString(list.toArray())
                : "");
    }
    System.out.println(" Out of the for loop...");
    if(k == list.size()-1) {
        System.out.println(" Printing Arr : ");
        System.out.println(Arrays.toString(list.toArray()));
    }
    System.out.println("********Remove Call Stack=******** with k = " + k+"***********\n");
}
```
</details>

<details>
<summary>Next Permutation</summary>

##### Description

> A **permutation** of an array of integers is an arrangement of its members into a sequence or linear order.
> 
> For `arr = [1, 2, 3]`, the following are considered permutations of `arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1]`.
> 
> The **next permutation** of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container
> 
> Given an array of integers `nums`, find the next _permutation_ of `nums`.
> 
> **Example 1**:
> 
> Input: nums = [1,2,3]
> 
> Output: [1,3,2]
> 
> **Example 2**:
> 
> Input: nums = [3,2,1]
> 
> Output: [1,2,3]
> 
> **Example 3**:
> 
> Input: nums = [1,1,5]
> 
> Output: [1,5,1]

Leetcode problem link: [https://leetcode.com/problems/next-permutation/](https://leetcode.com/problems/next-permutation/)

Explanation Video: [https://youtu.be/LuLCLgMElus](https://youtu.be/LuLCLgMElus)

##### Solution

![Next Permutation](src/main/resources/img/next-permutation.jpeg "Next Permutation")

##### Code

```Java
public class NextPermutation {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 4, 2};
        System.out.println("Next permutation: ");
        nextPermute(arr);
        for(int a: arr) {
            System.out.print(a + " ");
        }
    }

    private static void nextPermute(int[] arr) {
        if(arr == null || arr.length <= 1) return;
        int i = arr.length-2;
        while (i >= 0 && arr[i] >= arr[i+1]) i--;
        if(i >= 0) {
            int j = arr.length - 1;
            while (arr[j] <= arr[i]) j--;
            swap(arr, i , j);
        }
        reverse(arr, i+1, arr.length-1);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void reverse(int[] arr, int i, int j) {
        while(i < j) swap(arr, i++, j--);
    }
}
```
</details>

<details>
<summary>Count Inversions</summary>

##### Description

> Inversion Count for an array indicates ??? how far (or close) the array is from being sorted. If the array is already sorted, then the inversion count is 0, but if the array is sorted in the reverse order, the inversion count is the maximum.
>
> Formally speaking, two elements `a[i]` and `a[j]` form an inversion if `a[i] > a[j]` and `i < j`

Example

> Input: arr[] = {8, 4, 2, 1}
>
> Output: 6
>
> Explanation: Given array has six inversions:
>
> (8, 4), (4, 2), (8, 2), (8, 1), (4, 1), (2, 1).

GFG Problem link: [https://www.geeksforgeeks.org/counting-inversions/](https://www.geeksforgeeks.org/counting-inversions/)

Explanation Video: [https://youtu.be/kQ1mJlwW-c0](https://youtu.be/kQ1mJlwW-c0)

##### Code

```Java
import java.util.Arrays;

public class CountInversions {

    private static int[] aux; // auxiliary array for merges

    public static void main(String[] args) {
        int[] arr = {5, 3, 2, 4, 1};
        System.out.println("Inversion by brute-force: " + countInversionByBrute(arr));
        aux = new int[arr.length];
        System.out.println("Inversion by Merge Sort: " +
                countInversionByMergeSort(arr, 0, arr.length-1));
    }

    private static int countInversionByBrute(int[] arr) {
        int invCnt = 0;
        for(int i=0; i<arr.length-1; i++) {
            for(int j=i+1; j<arr.length; j++) {
                if(arr[i] > arr[j])
                    invCnt++;
            }
        }
        return invCnt;
    }

    private static int countInversionByMergeSort(int[] arr, int lo, int hi) {
        // sort arr[lo..hi]
        int invCnt = 0;
        if(hi <= lo) return invCnt;
        int mid = lo + (hi - lo)/2;
        invCnt += countInversionByMergeSort(arr, lo, mid);  // sort left half
        invCnt += countInversionByMergeSort(arr, mid+1, hi);    // sort right half
        invCnt += merge(arr, lo, mid, hi);  // merge results
        return invCnt;
    }

    private static int merge(int[] arr, int lo, int mid, int hi) {
        int invCnt = 0;
        int i = lo, j = mid+1; // left and right sub-arrya index

        aux = Arrays.copyOf(arr, arr.length);
        /*for(int k = lo; k <= hi; k++)
            aux[k] = arr[k];*/

        for(int k = lo; k <= hi; k++) {
            if      (i > mid)          arr[k] = aux[j++];
            else if (j > hi)           arr[k] = aux[i++];
            else if (aux[j] < aux[i])  {
                arr[k] = aux[j++];

                // here mid is left sub-array last ele, so + 1 is required
                invCnt += (mid - i) + 1;
            }
            else                       arr[k] = aux[i++];
        }
        return invCnt;
    }
}
```

Time Complexity: `O(nLogn)`

</details>