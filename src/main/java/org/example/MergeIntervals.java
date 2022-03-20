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
