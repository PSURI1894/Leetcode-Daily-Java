// Question:
// Given the root of a binary tree, split the binary tree into two subtrees
// by removing exactly one edge such that the product of the sums of the
// two resulting subtrees is maximized.
//
// Return the maximum product of the subtree sums.
// Since the answer may be very large, return it modulo (10^9 + 7).
//
// Note: You must maximize the product before taking the modulo.

class Solution {
    private long totalSum = 0;
    private long maxProduct = 0;
    private static final int MOD = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        // First pass: compute total sum of the tree
        totalSum = dfsSum(root);

        // Second pass: compute max product by trying all splits
        dfsProduct(root);

        return (int)(maxProduct % MOD);
    }

    // DFS to compute subtree sums
    private long dfsSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + dfsSum(node.left) + dfsSum(node.right);
    }

    // DFS to compute product for each possible split
    private long dfsProduct(TreeNode node) {
        if (node == null) return 0;

        long leftSum = dfsProduct(node.left);
        long rightSum = dfsProduct(node.right);

        long currentSum = node.val + leftSum + rightSum;

        // Product if we cut above this subtree
        long product = currentSum * (totalSum - currentSum);
        maxProduct = Math.max(maxProduct, product);

        return currentSum;
    }
}


// Explanation:
// The key idea is that removing any one edge splits the tree into two parts:
// - a subtree with sum = s
// - the remaining part with sum = totalSum - s
//
// The product for that split is:
// s * (totalSum - s)
//
// Approach:
// 1) First DFS computes the total sum of all nodes in the tree.
// 2) Second DFS computes the sum of every subtree.
//    For each subtree sum s, we calculate the product
//    s * (totalSum - s) and update the maximum.
// 3) We keep track of the maximum product globally.
// 4) The modulo is applied only once at the end, as required.
//
// Time Complexity: O(n)
// Space Complexity: O(n) due to recursion stack
