package org.example;

import java.util.Arrays;

public class FindRepeatingAndMissingNumber {

    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 2, 1, 1};
        printTwoNumberBySorting(arr);
        printTwoNumberByHashFrequency(arr);
        printTwoNumberByEquation(arr);
        printTwoNumberByXOR(arr);
    }

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

}
