// Question:
// You are given a large integer represented as an integer array digits,
// where each digits[i] is the ith digit of the integer.
// The digits are ordered from most significant to least significant.
// The integer does not contain any leading zeros.
//
// Increment the large integer by one and return the resulting array of digits.

class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        // Start from the last digit and move left
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        // If all digits were 9, we need an extra digit at the front
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}

// Explanation:
// We simulate manual addition from right to left.
// If the current digit is less than 9, we increment it and return immediately.
// If the digit is 9, it becomes 0 and the carry continues to the next digit.
// When all digits are 9, the array becomes all zeros, so we create a new array
// with one extra digit and place 1 at the front.
// Time Complexity: O(n)
// Space Complexity: O(1) extra space (excluding the output array)
