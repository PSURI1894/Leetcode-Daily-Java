// Question:
// You are given an integer array nums with the following properties:
// 1) nums.length = 2 * n
// 2) nums contains n + 1 unique elements
// 3) Exactly one element in nums is repeated n times
//
// Return the element that is repeated n times.

import java.util.HashSet;

class Solution {
    public int repeatedNTimes(int[] nums) {
        HashSet<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) {
                return num;
            }
            seen.add(num);
        }

        return -1; // This line will never be reached due to problem constraints
    }
}

// Explanation:
// Since exactly one element is repeated n times, any duplicate we encounter
// while scanning the array must be the required answer.
// We use a HashSet to track elements we have already seen.
// The moment we see an element again, we return it.
//
// Time Complexity: O(n)
// Space Complexity: O(n)
