// Question:
// You are given an n x n integer matrix.
// You can perform the following operation any number of times:
// - Choose any two adjacent elements of the matrix and multiply each of them by -1.
//   (Two elements are adjacent if they share a border.)
//
// Your goal is to maximize the sum of the matrix's elements.
// Return the maximum possible sum after applying the operations optimally.

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long totalSum = 0;
        int minAbs = Integer.MAX_VALUE;
        int negativeCount = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int val = matrix[i][j];
                totalSum += Math.abs(val);
                minAbs = Math.min(minAbs, Math.abs(val));
                if (val < 0) {
                    negativeCount++;
                }
            }
        }

        // If number of negative elements is even,
        // all values can be made positive
        if (negativeCount % 2 == 0) {
            return totalSum;
        }

        // If odd, one smallest absolute value must remain negative
        return totalSum - 2L * minAbs;
    }
}


// Explanation:
// Each operation flips the signs of two adjacent cells.
// This means the parity (even/odd) of the number of negative elements
// never changes, no matter how many operations we perform.
//
// Strategy:
// 1) Take the absolute value of every element and sum them up.
//    This represents the maximum possible contribution of each cell.
// 2) Count how many negative numbers exist in the matrix.
// 3) Track the smallest absolute value present.
//
// If the number of negatives is even:
// - We can flip signs in pairs and make all values positive.
// - The answer is simply the sum of absolute values.
//
// If the number of negatives is odd:
// - One value must remain negative.
// - To minimize the loss, we keep the smallest absolute value negative.
// - Hence, subtract twice that smallest value from the total sum.
//
// Time Complexity: O(n^2)
// Space Complexity: O(1)
