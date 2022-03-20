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
