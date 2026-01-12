// Question:
// Given a rows x cols binary matrix filled with '0's and '1's,
// find the largest rectangle containing only '1's and return its area.
//
// Each rectangle must be made up entirely of contiguous '1's.

import java.util.Stack;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        for (char[] row : matrix) {
            // Build histogram heights for this row
            for (int i = 0; i < cols; i++) {
                if (row[i] == '1') {
                    heights[i]++;
                } else {
                    heights[i] = 0;
                }
            }

            // Largest Rectangle in Histogram
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i <= cols; i++) {
                int currHeight = (i == cols) ? 0 : heights[i];

                while (!stack.isEmpty() && currHeight < heights[stack.peek()]) {
                    int height = heights[stack.pop()];
                    int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                    maxArea = Math.max(maxArea, height * width);
                }

                stack.push(i);
            }
        }

        return maxArea;
    }
}


// Explanation:
// This problem is solved by converting each row of the matrix into a histogram.
//
// Idea:
// - heights[j] stores the number of consecutive '1's above (including current row)
//   for column j.
//
// For each row:
// 1) Update the histogram heights.
// 2) Compute the largest rectangle area in the histogram using a monotonic stack.
//
// Stack logic:
// - The stack stores indices of bars in increasing height order.
// - When a smaller bar is encountered, we pop from the stack and calculate
//   the rectangle area using the popped height.
// - Width is determined by the current index and the new top of the stack.
//
// A sentinel bar of height 0 is added at the end to flush remaining bars.
//
// Time Complexity: O(rows * cols)
// Space Complexity: O(cols)