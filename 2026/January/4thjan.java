// Question:
// You are given an integer array nums.
// Return the sum of divisors of the integers in nums that have exactly four divisors.
// If no such integer exists, return 0.
//
// Example:
// nums = [21, 4, 7]
// 21 has divisors: 1, 3, 7, 21 → exactly 4 divisors → sum = 32
// 4 has 3 divisors, 7 has 2 divisors → ignored
// Answer = 32


class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        for (int num : nums) {
            int count = 0;
            int sum = 0;

            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    int d1 = i;
                    int d2 = num / i;

                    if (d1 == d2) {
                        count += 1;
                        sum += d1;
                    } else {
                        count += 2;
                        sum += d1 + d2;
                    }

                    if (count > 4) {
                        break;
                    }
                }
            }

            if (count == 4) {
                totalSum += sum;
            }
        }

        return totalSum;
    }
}


// Explanation:
// A number has exactly four divisors if and only if:
// - It is of the form p^3 where p is prime, OR
// - It is of the form p * q where p and q are distinct primes
//
// Instead of prime factorization, we brute-force divisors up to sqrt(num).
// For every divisor i:
// - i and num / i are both divisors
// - We count and sum them together
//
// If at any point divisor count exceeds 4, we stop early.
// Only when the total divisor count is exactly 4 do we add its divisor sum
// to the final answer.
//
// Time Complexity:
// O(n * sqrt(max(nums[i])))
//
// Space Complexity:
// O(1) extra space
