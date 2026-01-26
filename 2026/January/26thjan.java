/*
Question:
1200. Minimum Absolute Difference

You are given an array of distinct integers arr.
Find all pairs of elements with the minimum absolute difference
between any two elements.

Return the pairs in ascending order, where for each pair [a, b]:
- a < b
- b - a equals the minimum absolute difference.
*/

import java.util.*;

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Step 1: Sort the array
        Arrays.sort(arr);
        
        // Step 2: Find the minimum absolute difference
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            minDiff = Math.min(minDiff, arr[i] - arr[i - 1]);
        }
        
        // Step 3: Collect all pairs having this minimum difference
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] == minDiff) {
                result.add(Arrays.asList(arr[i - 1], arr[i]));
            }
        }
        
        return result;
    }
}