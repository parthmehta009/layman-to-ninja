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
