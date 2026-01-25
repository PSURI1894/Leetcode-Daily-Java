// Question:
// 1877. Minimize Maximum Pair Sum in Array
//
// The pair sum of a pair (a, b) is a + b.
// You are given an array nums of even length.
// Pair up all elements such that:
// 1. Each element is used exactly once.
// 2. The maximum pair sum is minimized.
//
// Return the minimized maximum pair sum.

import java.util.Arrays;

class Solution {
    public int minPairSum(int[] nums) {
        // Sort the array to enable greedy pairing
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int maxPairSum = 0;

        // Pair smallest with largest
        while (left < right) {
            maxPairSum = Math.max(maxPairSum, nums[left] + nums[right]);
            left++;
            right--;
        }

        return maxPairSum;
    }
}

/*
Explanation:

Key Insight:
To minimize the maximum pair sum, we should avoid pairing large numbers together.

Optimal Strategy:
- Sort the array
- Pair the smallest element with the largest element

Why this works:
- Pairing two large values produces a large sum.
- Pairing smallest with largest balances all pair sums.
- This greedy approach guarantees the minimum possible maximum.

Example:
nums = [3,5,2,3]
Sorted = [2,3,3,5]

Pairs:
(2,5) -> 7
(3,3) -> 6

Maximum pair sum = 7 (minimum possible)

Time Complexity: O(n log n)
Space Complexity: O(1) extra space (sorting aside)
*/