// Question:
// Given two integer arrays nums1 and nums2.
//
// Return the maximum dot product between non-empty subsequences of nums1 and nums2
// that have the same length.
//
// A subsequence is formed by deleting some (or none) elements without changing
// the order of the remaining elements.
//
// The dot product of two arrays of equal length is the sum of the products of
// corresponding elements.


class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        // dp[i][j] = maximum dot product using nums1[i:] and nums2[j:]
        int[][] dp = new int[n + 1][m + 1];

        // Initialize with very small values to enforce non-empty subsequences
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MIN_VALUE;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int product = nums1[i] * nums2[j];

                // Option 1: take both elements and start a new subsequence
                int takeBoth = product;

                // Option 2: extend an existing subsequence
                if (dp[i + 1][j + 1] > 0) {
                    takeBoth = product + dp[i + 1][j + 1];
                }

                // Option 3: skip one element
                int skipNums1 = dp[i + 1][j];
                int skipNums2 = dp[i][j + 1];

                dp[i][j] = Math.max(takeBoth, Math.max(skipNums1, skipNums2));
            }
        }

        return dp[0][0];
    }
}


// Explanation:
// This is a Dynamic Programming problem where we must choose matching subsequences
// from both arrays to maximize their dot product.
//
// Key challenge:
// - The subsequence must be non-empty
// - Numbers can be negative, so we cannot default to 0
//
// State Definition:
// dp[i][j] represents the maximum dot product that can be obtained using
// elements from nums1 starting at index i and nums2 starting at index j.
//
// Transition:
// At each (i, j), we have three choices:
//
// 1) Pair nums1[i] with nums2[j]:
//    - Start a new subsequence: nums1[i] * nums2[j]
//    - Or extend an existing subsequence if it helps
//
// 2) Skip nums1[i]:
//    dp[i + 1][j]
//
// 3) Skip nums2[j]:
//    dp[i][j + 1]
//
// We take the maximum of these options.
//
// Initialization:
// dp is initialized with very small values to ensure that
// at least one pair of elements is chosen.
//
// Final Answer:
// dp[0][0] contains the maximum dot product using the full arrays.
//
// Time Complexity: O(n * m)
// Space Complexity: O(n * m)
