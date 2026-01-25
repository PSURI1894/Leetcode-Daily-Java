// Question:
// 1984. Minimum Difference Between Highest and Lowest of K Scores
//
// You are given an integer array nums where nums[i] is the score of the i-th student,
// and an integer k.
//
// Pick scores of any k students such that the difference between the highest
// and the lowest of those k scores is minimized.
//
// Return the minimum possible difference.

import java.util.Arrays;

class Solution {
    public int minimumDifference(int[] nums, int k) {
        // If we pick only one student, difference is always 0
        if (k == 1) return 0;

        // Sort the scores
        Arrays.sort(nums);

        int n = nums.length;
        int minDiff = Integer.MAX_VALUE;

        // Sliding window of size k
        for (int i = 0; i + k - 1 < n; i++) {
            int diff = nums[i + k - 1] - nums[i];
            minDiff = Math.min(minDiff, diff);
        }

        return minDiff;
    }
}

/*
Explanation:

Key Idea:
If we want to minimize (max - min) among k elements,
those k elements should be as close as possible in value.

Steps:
1. Sort the array.
2. Consider every consecutive group of k elements.
3. For each group:
      difference = nums[i + k - 1] - nums[i]
4. Take the minimum of all such differences.

Why this works:
- After sorting, the smallest possible range of k elements
  must appear as a contiguous subarray.
- Any non-contiguous selection would only increase the range.

Example:
nums = [9,4,1,7], k = 2
Sorted nums = [1,4,7,9]

Windows:
[1,4] -> diff = 3
[4,7] -> diff = 3
[7,9] -> diff = 2  ✅

Answer = 2

Time Complexity:
O(n log n) due to sorting

Space Complexity:
O(1) extra space (ignoring sort internals)
*/