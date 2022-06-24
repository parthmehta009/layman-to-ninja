package org.example;

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
