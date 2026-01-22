// Question:
// Given two binary strings a and b, return their sum as a binary string.
//
// Each string consists only of characters '0' or '1'.
// The result should be returned as a binary string without leading zeros
// (except when the result itself is "0").

class Solution {
    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        StringBuilder sb = new StringBuilder();

        // Traverse both strings from right to left
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;

            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }

            // Current bit
            sb.append(sum % 2);

            // Update carry
            carry = sum / 2;
        }

        // Reverse because we built the result from LSB to MSB
        return sb.reverse().toString();
    }
}

/*
Explanation:
- Binary addition works like decimal addition but base-2.
- We start from the least significant bits (rightmost characters).
- Add corresponding bits and a carry.
- The resulting bit is sum % 2, and carry becomes sum / 2.
- Continue until both strings are exhausted and no carry remains.
- Since bits are appended in reverse order, reverse at the end.

Time Complexity:
O(max(len(a), len(b)))

Space Complexity:
O(max(len(a), len(b)))
*/
