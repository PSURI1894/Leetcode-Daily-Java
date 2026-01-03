// Question:
// You have a grid of size n x 3 and you want to paint each cell of the grid
// with exactly one of the three colors: Red, Yellow, or Green.
// You must ensure that no two adjacent cells (horizontal or vertical)
// have the same color.
//
// Given n, return the number of ways to paint the grid.
// Since the answer can be very large, return it modulo 10^9 + 7.

class Solution {
    public int numOfWays(int n) {
        final long MOD = 1000000007L;

        // aba: patterns like R G R (first and third same)
        // abc: patterns like R G B (all different)
        long aba = 6; // 3 * 2
        long abc = 6; // 3 * 2 * 1

        for (int i = 2; i <= n; i++) {
            long newAba = (aba * 3 + abc * 2) % MOD;
            long newAbc = (aba * 2 + abc * 2) % MOD;

            aba = newAba;
            abc = newAbc;
        }

        return (int)((aba + abc) % MOD);
    }
}


// Explanation:
// For each row, only two valid coloring patterns exist:
//
// 1) aba pattern:
//    First and third cells have the same color, middle is different.
//    Example: Red-Green-Red
//
// 2) abc pattern:
//    All three cells have different colors.
//    Example: Red-Green-Yellow
//
// Initial state (n = 1):
// aba = 6
// abc = 6
//
// Transition rules:
// - New aba rows can be formed from:
//     previous aba rows in 3 ways
//     previous abc rows in 2 ways
// - New abc rows can be formed from:
//     previous aba rows in 2 ways
//     previous abc rows in 2 ways
//
// We use dynamic programming with constant space,
// updating only the previous row's counts.
//
// Time Complexity: O(n)
// Space Complexity: O(1)
