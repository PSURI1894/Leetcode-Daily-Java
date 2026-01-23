// Question:
// 3510. Minimum Pair Removal to Sort Array II
//
// Given an array nums, you can perform the following operation any number of times:
//
// 1) Select the adjacent pair with the minimum sum in nums.
//    If multiple such pairs exist, choose the leftmost one.
// 2) Replace the selected pair with their sum.
//
// Return the minimum number of operations needed to make the array non-decreasing.
//
// An array is non-decreasing if nums[i] >= nums[i-1] for all valid i.

import java.util.PriorityQueue;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        // val[i] stores the current value at index i
        long[] val = new long[n];

        // Doubly linked list representation
        int[] next = new int[n];
        int[] prev = new int[n];

        // Marks whether an index has been removed (merged)
        boolean[] removed = new boolean[n];

        // Initialize structures
        for (int i = 0; i < n; i++) {
            val[i] = nums[i];
            next[i] = (i == n - 1) ? -1 : i + 1;
            prev[i] = (i == 0) ? -1 : i - 1;
        }

        // Count how many "bad" (decreasing) adjacent pairs exist
        int badCount = 0;
        for (int i = 0; i < n - 1; i++) {
            if (val[i] > val[i + 1]) {
                badCount++;
            }
        }

        // If already non-decreasing, no operations needed
        if (badCount == 0) return 0;

        // Min-heap storing (pairSum, leftIndex, rightIndex)
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) return Long.compare(a[0], b[0]);
                return Long.compare(a[1], b[1]); // leftmost if tie
            }
        );

        // Initialize heap with all adjacent pairs
        for (int i = 0; i < n - 1; i++) {
            pq.offer(new long[]{val[i] + val[i + 1], i, i + 1});
        }

        int ops = 0;

        // Process forced merges until array becomes non-decreasing
        while (!pq.isEmpty()) {
            long[] top = pq.poll();
            long sum = top[0];
            int u = (int) top[1];
            int v = (int) top[2];

            // Skip invalid or outdated heap entries
            if (removed[u] || removed[v] || next[u] != v) continue;
            if (val[u] + val[v] != sum) continue;

            ops++;

            // Update badCount for pairs that will be removed
            if (val[u] > val[v]) badCount--;
            int l = prev[u];
            int r = next[v];

            if (l != -1 && val[l] > val[u]) badCount--;
            if (r != -1 && val[v] > val[r]) badCount--;

            // Merge u and v into u
            val[u] = sum;
            removed[v] = true;
            next[u] = r;
            if (r != -1) prev[r] = u;

            // Add new adjacent pairs and update badCount
            if (l != -1) {
                if (val[l] > val[u]) badCount++;
                pq.offer(new long[]{val[l] + val[u], l, u});
            }
            if (r != -1) {
                if (val[u] > val[r]) badCount++;
                pq.offer(new long[]{val[u] + val[r], u, r});
            }

            // Stop as soon as the array becomes non-decreasing
            if (badCount == 0) return ops;
        }

        return ops;
    }
}

/*
Explanation:
- The operation order is forced: at each step we must merge the adjacent pair
  with the minimum sum (leftmost if tied).
- A doubly linked list (via next/prev arrays) allows O(1) adjacency updates.
- A min-heap gives the required pair in O(log n).
- Lazy deletion is used to ignore outdated heap entries.
- We track "badCount": the number of indices where nums[i] > nums[i+1].
- Merges are performed only until badCount becomes zero, meaning the array
  is non-decreasing for the first time.
- The moment badCount reaches zero, we can stop and return the operation count.

Time Complexity:
O(n log n)

Space Complexity:
O(n)
*/