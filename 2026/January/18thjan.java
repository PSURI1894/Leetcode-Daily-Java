// Question:
// A k x k magic square is a k x k grid filled with integers such that:
// - The sum of every row is equal
// - The sum of every column is equal
// - The sums of both diagonals are equal
//
// A 1 x 1 grid is always a magic square.
//
// Given an m x n integer grid, return the size (side length k)
// of the largest magic square that can be found in the grid.

class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Prefix sums for rows and columns
        int[][] rowPS = new int[m][n + 1];
        int[][] colPS = new int[m + 1][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPS[i][j + 1] = rowPS[i][j] + grid[i][j];
                colPS[i + 1][j] = colPS[i][j] + grid[i][j];
            }
        }

        // Try all square sizes from largest to smallest
        for (int k = Math.min(m, n); k > 1; k--) {
            for (int i = 0; i + k <= m; i++) {
                for (int j = 0; j + k <= n; j++) {

                    int target = rowPS[i][j + k] - rowPS[i][j];
                    boolean valid = true;

                    // Check all rows
                    for (int r = i; r < i + k; r++) {
                        if (rowPS[r][j + k] - rowPS[r][j] != target) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) continue;

                    // Check all columns
                    for (int c = j; c < j + k; c++) {
                        if (colPS[i + k][c] - colPS[i][c] != target) {
                            valid = false;
                            break;
                        }
                    }
                    if (!valid) continue;

                    // Check diagonals
                    int diag1 = 0, diag2 = 0;
                    for (int d = 0; d < k; d++) {
                        diag1 += grid[i + d][j + d];
                        diag2 += grid[i + d][j + k - 1 - d];
                    }

                    if (diag1 == target && diag2 == target) {
                        return k;
                    }
                }
            }
        }

        return 1; // At least 1x1 is always valid
    }
}

/*
Explanation:
- Any single cell forms a valid 1x1 magic square.
- We use prefix sums to compute row and column sums efficiently.
- We check all possible k x k subgrids, starting from the largest k.
- For each subgrid:
  - Verify all row sums are equal.
  - Verify all column sums are equal.
  - Verify both diagonals have the same sum.
- The first valid square found is the largest one, so we return immediately.

Time Complexity: O(min(m, n)^3)
Space Complexity: O(m * n)
*/