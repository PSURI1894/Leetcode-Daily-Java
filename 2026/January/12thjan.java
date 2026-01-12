// Question:
// On a 2D plane, there are n points with integer coordinates points[i] = [xi, yi].
// You need to visit all the points in the given order.
//
// In 1 second, you can:
// - Move vertically by 1 unit, or
// - Move horizontally by 1 unit, or
// - Move diagonally by 1 unit (i.e., move 1 unit vertically and 1 unit horizontally).
//
// Return the minimum time in seconds to visit all the points in order.

class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int totalTime = 0;

        for (int i = 1; i < points.length; i++) {
            int x1 = points[i - 1][0];
            int y1 = points[i - 1][1];
            int x2 = points[i][0];
            int y2 = points[i][1];

            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);

            // Minimum time is the maximum of dx and dy
            totalTime += Math.max(dx, dy);
        }

        return totalTime;
    }
}


// Explanation:
// Diagonal moves allow reducing both x and y distances by 1 in one second.
// So we always use as many diagonal moves as possible.
//
// For two consecutive points:
// - Horizontal distance = |x2 - x1|
// - Vertical distance   = |y2 - y1|
//
// The minimum time required to move between them is:
// max(|x2 - x1|, |y2 - y1|)
//
// We compute this for each consecutive pair of points
// and sum it to get the total minimum time.
//
// Time Complexity: O(n)
// Space Complexity: O(1)