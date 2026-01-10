// Question:
// Given two strings s1 and s2, return the minimum ASCII sum of deleted characters
// required to make the two strings equal.
//
// You may delete characters from either string.
// The cost of deleting a character is its ASCII value.
// The goal is to minimize the total deletion cost.

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        // dp[i][j] = minimum ASCII delete sum to make s1[i:] and s2[j:] equal
        int[][] dp = new int[n + 1][m + 1];

        // Base case: delete all remaining characters from s1
        for (int i = n - 1; i >= 0; i--) {
            dp[i][m] = dp[i + 1][m] + s1.charAt(i);
        }

        // Base case: delete all remaining characters from s2
        for (int j = m - 1; j >= 0; j--) {
            dp[n][j] = dp[n][j + 1] + s2.charAt(j);
        }

        // Fill the DP table
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else {
                    dp[i][j] = Math.min(
                        s1.charAt(i) + dp[i + 1][j],
                        s2.charAt(j) + dp[i][j + 1]
                    );
                }
            }
        }

        return dp[0][0];
    }
}


// Explanation:
// This problem is a variation of the Longest Common Subsequence DP,
// where instead of maximizing matches, we minimize deletion cost.
//
// State Definition:
// dp[i][j] represents the minimum ASCII delete sum needed to make
// s1[i:] and s2[j:] equal.
//
// Transitions:
// - If characters match, move both pointers without cost.
// - If they do not match, delete one character:
//     • Delete s1[i]
//     • Delete s2[j]
//   Choose the cheaper option.
//
// Base Cases:
// - If one string is exhausted, delete all remaining characters
//   from the other string.
//
// Final Answer:
// dp[0][0] gives the minimum ASCII delete sum.
//
// Time Complexity: O(n * m)
// Space Complexity: O(n * m)