// Question:
// Given an m x n matrix mat and an integer threshold,
// return the maximum side length of a square such that the sum of all elements
// inside the square is less than or equal to threshold.
// If no such square exists, return 0.

class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;

        // Prefix sum matrix
        // ps[i][j] stores sum of submatrix from (0,0) to (i-1,j-1)
        int[][] ps = new int[m + 1][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ps[i + 1][j + 1] =
                        mat[i][j]
                        + ps[i][j + 1]
                        + ps[i + 1][j]
                        - ps[i][j];
            }
        }

        int left = 0, right = Math.min(m, n);
        int ans = 0;

        // Binary search on side length
        while (left <= right) {
            int mid = (left + right) / 2;
            boolean found = false;

            for (int i = 0; i + mid <= m && !found; i++) {
                for (int j = 0; j + mid <= n; j++) {
                    int sum =
                            ps[i + mid][j + mid]
                            - ps[i][j + mid]
                            - ps[i + mid][j]
                            + ps[i][j];

                    if (sum <= threshold) {
                        found = true;
                        break;
                    }
                }
            }

            if (found) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }
}

/*
Explanation:
- We precompute a 2D prefix sum array so that any k x k square sum
  can be calculated in O(1).
- The feasibility of a square size k is monotonic:
  if size k works, all sizes smaller than k also work.
- This allows us to apply binary search on the side length.
- For each candidate k, we scan all possible k x k squares and
  check if any has sum ≤ threshold.
- The largest valid k encountered is returned.

Time Complexity: O(m * n * log(min(m, n)))
Space Complexity: O(m * n)
*/
