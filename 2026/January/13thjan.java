// Question:
// You are given a 2D integer array squares where squares[i] = [xi, yi, li]
// represents a square with bottom-left corner (xi, yi) and side length li.
// All squares are axis-aligned.
//
// Find the minimum y-coordinate of a horizontal line such that
// the total area of the squares strictly above the line
// equals the total area of the squares strictly below the line.
//
// Squares may overlap, and overlapping areas should be counted multiple times.
// Answers within 1e-5 of the correct value are accepted.

class Solution {
    public double separateSquares(int[][] squares) {

        // Compute (area_above - area_below) for a given y
        java.util.function.DoubleFunction<Double> balance = (double y) -> {
            double diff = 0.0;

            for (int[] sq : squares) {
                double bottom = sq[1];
                double top = sq[1] + sq[2];
                double area = 1.0 * sq[2] * sq[2];

                if (y <= bottom) {
                    // Entire square is above the line
                    diff += area;
                } else if (y >= top) {
                    // Entire square is below the line
                    diff -= area;
                } else {
                    // Line cuts the square
                    double above = (top - y) * sq[2];
                    double below = (y - bottom) * sq[2];
                    diff += above - below;
                }
            }

            return diff;
        };

        // Binary search range
        double low = squares[0][1];
        double high = squares[0][1] + squares[0][2];

        for (int[] sq : squares) {
            low = Math.min(low, sq[1]);
            high = Math.max(high, sq[1] + sq[2]);
        }

        // Binary search to find y where balance(y) == 0
        for (int i = 0; i < 60; i++) { // enough for 1e-5 precision
            double mid = (low + high) / 2.0;
            if (balance.apply(mid) > 0) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }
}

// Explanation:
// We want a horizontal line y = c such that:
//   total area above the line = total area below the line.
//
// Define f(c) = (area above y=c) - (area below y=c).
// We are looking for c where f(c) = 0.
//
// For each square:
// - If the line is below the square → entire area counts above.
// - If the line is above the square → entire area counts below.
// - If the line cuts the square → split the area proportionally.
//
// The function f(c) is continuous and monotonically decreasing,
// which allows us to apply binary search on y.
//
// We search between the minimum bottom and maximum top among all squares
// and iterate enough times to ensure the required precision.
//
// Time Complexity:
// O(n * log(precision))
//
// Space Complexity:
// O(1)