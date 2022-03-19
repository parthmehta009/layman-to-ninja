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
