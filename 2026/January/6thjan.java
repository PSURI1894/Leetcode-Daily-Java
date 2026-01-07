// Question:
// Given the root of a binary tree, the level of its root is 1,
// the level of its children is 2, and so on.
//
// Return the smallest level x such that the sum of all the values
// of nodes at level x is maximum.

import java.util.*;

class Solution {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int level = 1;
        int answerLevel = 1;
        long maxSum = Long.MIN_VALUE;

        while (!queue.isEmpty()) {
            int size = queue.size();
            long levelSum = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            if (levelSum > maxSum) {
                maxSum = levelSum;
                answerLevel = level;
            }

            level++;
        }

        return answerLevel;
    }
}


// Explanation:
// We use Breadth-First Search (level-order traversal) to process the tree
// one level at a time.
//
// For each level:
// - Compute the sum of all node values at that level.
// - Compare it with the maximum sum seen so far.
// - If it is larger, update the maximum sum and store the level number.
//
// Since we traverse levels in increasing order, the first level that
// achieves the maximum sum is automatically the smallest such level.
//
// Time Complexity: O(n)
// Space Complexity: O(n)
