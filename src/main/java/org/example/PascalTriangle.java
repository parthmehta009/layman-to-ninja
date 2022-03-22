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
