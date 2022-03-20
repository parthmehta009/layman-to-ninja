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
