// Question:
// You are given a 2D integer array squares where squares[i] = [xi, yi, li]
// represents a square with bottom-left corner (xi, yi) and side length li.
// All squares are axis-aligned.
//
// Find the minimum y-coordinate of a horizontal line such that
// the total area covered by squares above the line
// equals the total area covered by squares below the line.
//
// IMPORTANT (Separate Squares II):
// - Overlapping areas must be counted ONLY ONCE.
// - Answers within 1e-5 of the actual answer are accepted.

import java.util.*;

class Solution {

    static class Event {
        double y;
        int type; // +1 add interval, -1 remove interval
        double x1, x2;

        Event(double y, int type, double x1, double x2) {
            this.y = y;
            this.type = type;
            this.x1 = x1;
            this.x2 = x2;
        }
    }

    public double separateSquares(int[][] squares) {

        List<Event> events = new ArrayList<>();

        // Create sweep-line events
        for (int[] s : squares) {
            double x = s[0], y = s[1], l = s[2];
            events.add(new Event(y, 1, x, x + l));
            events.add(new Event(y + l, -1, x, x + l));
        }

        // Sort events by y-coordinate
        events.sort(Comparator.comparingDouble(e -> e.y));

        List<double[]> active = new ArrayList<>(); // active x-intervals
        List<double[]> slabs = new ArrayList<>();  // {y1, y2, area}

        double totalArea = 0.0;
        double prevY = events.get(0).y;
        int i = 0;

        while (i < events.size()) {
            double currY = events.get(i).y;
            double dy = currY - prevY;

            if (dy > 0) {
                double width = unionLength(active);
                double area = width * dy;
                slabs.add(new double[]{prevY, currY, area});
                totalArea += area;
            }

            // Process all events at currY
            while (i < events.size() && events.get(i).y == currY) {
                Event e = events.get(i);
                if (e.type == 1) {
                    active.add(new double[]{e.x1, e.x2});
                } else {
                    // remove interval
                    for (int k = 0; k < active.size(); k++) {
                        if (active.get(k)[0] == e.x1 && active.get(k)[1] == e.x2) {
                            active.remove(k);
                            break;
                        }
                    }
                }
                i++;
            }

            prevY = currY;
        }

        // Find y where area below == area above == totalArea / 2
        double target = totalArea / 2.0;
        double accumulated = 0.0;

        for (double[] slab : slabs) {
            double y1 = slab[0], y2 = slab[1], area = slab[2];
            if (accumulated + area >= target) {
                double width = area / (y2 - y1);
                return y1 + (target - accumulated) / width;
            }
            accumulated += area;
        }

        return slabs.get(slabs.size() - 1)[1];
    }

    // Compute union length of x-intervals
    private double unionLength(List<double[]> intervals) {
        if (intervals.isEmpty()) return 0.0;

        intervals.sort(Comparator.comparingDouble(a -> a[0]));

        double length = 0.0;
        double curL = intervals.get(0)[0];
        double curR = intervals.get(0)[1];

        for (int i = 1; i < intervals.size(); i++) {
            double l = intervals.get(i)[0];
            double r = intervals.get(i)[1];

            if (l > curR) {
                length += curR - curL;
                curL = l;
                curR = r;
            } else {
                curR = Math.max(curR, r);
            }
        }

        length += curR - curL;
        return length;
    }
}

// Explanation:
//
// This is a sweep-line + union-area problem.
//
// Difference from Separate Squares I:
// - Overlapping square areas are counted ONLY ONCE.
//
// Approach:
// 1. Perform a vertical sweep along the y-axis.
// 2. Each square contributes two events:
//    - entering at y
//    - leaving at y + l
// 3. Between consecutive y-events, active squares remain the same.
//    Their x-intervals form a union whose total length gives the covered width.
// 4. Area of each horizontal slab = union_width × slab_height.
//
// After computing the total union area:
// - Find the y where accumulated area below equals half of total area.
// - Interpolate inside the slab to get the exact y-coordinate.
//
// Time Complexity:
// O(n log n)
//
// Space Complexity:
// O(n)