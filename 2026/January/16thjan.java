// Question:
// You are given two integers n and m and two integer arrays hBars and vBars.
// The grid has (n + 2) horizontal bars and (m + 2) vertical bars, creating 1x1 unit cells.
//
// You can remove some bars from hBars (horizontal) and vBars (vertical).
// Other bars are fixed and cannot be removed.
//
// After removing bars, holes (empty regions) are formed.
// Return the maximum possible area of a square-shaped hole in the grid.

import java.util.Arrays;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        final int MOD = 1_000_000_007;

        // Add boundary fences
        int[] h = new int[hFences.length + 2];
        int[] v = new int[vFences.length + 2];

        h[0] = 1;
        h[h.length - 1] = m;
        for (int i = 0; i < hFences.length; i++) {
            h[i + 1] = hFences[i];
        }

        v[0] = 1;
        v[v.length - 1] = n;
        for (int i = 0; i < vFences.length; i++) {
            v[i + 1] = vFences[i];
        }

        Arrays.sort(h);
        Arrays.sort(v);

        // Store all possible heights
        java.util.HashSet<Integer> heights = new java.util.HashSet<>();
        for (int i = 0; i < h.length; i++) {
            for (int j = i + 1; j < h.length; j++) {
                heights.add(h[j] - h[i]);
            }
        }

        // Find largest square side
        int maxSide = 0;
        for (int i = 0; i < v.length; i++) {
            for (int j = i + 1; j < v.length; j++) {
                int width = v[j] - v[i];
                if (heights.contains(width)) {
                    maxSide = Math.max(maxSide, width);
                }
            }
        }

        if (maxSide == 0) return -1;
        return (int) ((long) maxSide * maxSide % MOD);
    }
}

// Explanation:
// Removing k consecutive horizontal bars merges (k + 1) rows.
// Removing k consecutive vertical bars merges (k + 1) columns.
//
// To form a square hole:
// - Height = longest consecutive removed horizontal bars + 1
// - Width  = longest consecutive removed vertical bars + 1
//
// The largest possible square side length is the minimum of height and width.
// The result is side².
//
// Time Complexity: O(h log h + v log v)
// Space Complexity: O(1)
