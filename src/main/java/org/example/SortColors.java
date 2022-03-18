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
