// Question:
// You are given an array nums consisting of n prime integers.
// (Here nums is provided as a List<Integer>.)
//
// Construct an array ans of length n such that for each index i:
//     ans[i] OR (ans[i] + 1) == nums[i]
//
// You must minimize ans[i]. If it is not possible, set ans[i] = -1.
//
// Return the resulting array as an int[].
import java.util.*;
class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            int x = nums.get(i);

            // If x is even, it is impossible
            if ((x & 1) == 0) {
                ans[i] = -1;
                continue;
            }

            // Count trailing 1s in x
            int t = 0;
            while (((x >> t) & 1) == 1) {
                t++;
            }

            // Remove the highest bit among trailing 1s
            ans[i] = x - (1 << (t - 1));
        }

        return ans;
    }
}

/*
Explanation:
- For a value x = a OR (a + 1), x must be odd.
  If x is even, no such a exists → answer is -1.
- Let x end with k trailing 1s in binary.
  Example: x = 101111 (k = 4)
- The smallest valid a is obtained by turning the highest bit
  in this trailing block to 0:
      a = x - 2^(k-1)
- This guarantees:
      a OR (a + 1) == x
  and a is minimized.
- We apply this logic independently for each element in nums.

Time Complexity:
O(n · log(nums[i]))  (counting trailing bits)

Space Complexity:
O(n)  (output array)
*/
