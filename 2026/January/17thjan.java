// Question:
// You are given multiple axis-aligned rectangles on a 2D plane.
// Each rectangle is defined by its bottom-left and top-right coordinates.
//
// You must find the maximum possible area of a square that can be placed
// completely inside the intersection region of at least two rectangles.
//
// If no two rectangles intersect, return 0.

class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxArea = 0;

        // Check intersection for every pair of rectangles
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int left   = Math.max(bottomLeft[i][0], bottomLeft[j][0]);
                int bottom = Math.max(bottomLeft[i][1], bottomLeft[j][1]);
                int right  = Math.min(topRight[i][0], topRight[j][0]);
                int top    = Math.min(topRight[i][1], topRight[j][1]);

                // If intersection exists
                if (right > left && top > bottom) {
                    long side = Math.min(right - left, top - bottom);
                    maxArea = Math.max(maxArea, side * side);
                }
            }
        }
        return maxArea;
    }
}

/*
Explanation:
- A square can only fit inside the intersection of two rectangles.
- For any two rectangles, we compute their overlapping region.
- The largest square that fits in that region has side length:
      min(intersection width, intersection height)
- We check all rectangle pairs and keep the maximum square area.
- If no pair intersects, the answer remains 0.

Time Complexity: O(n^2)
Space Complexity: O(1)
*/
