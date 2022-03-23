package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PrintAllPermutation {
    public static void main(String[] args) {
        permute(Arrays.asList(1,2), 0);
        permute_list(new ArrayList<>(), new ArrayList<>(Arrays.asList(1,2)));
        permute_str("", "ABC");
    }

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

    private static void permute_str(String prefix, String str) {
        int n = str.length();
        if(n==0) System.out.println(prefix);
        else {
            for(int i=0; i<n; i++)
                permute_str(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
        }
    }

    private static void permute_list(List<Integer> prefixList, List<Integer> list) {
        int n = list.size();
        if(n == 0) System.out.println(Arrays.toString(prefixList.toArray()));
        else {
            for(int i=0; i<n; i++) {
                List<Integer> newPrefList = new ArrayList<>(prefixList);
                List<Integer> newList = new ArrayList<>(list);

                newPrefList.add(newList.get(i));
                newList.remove(i);

                permute_list(newPrefList, newList);
            }
        }
    }
}
