// Question:
// Given the root of a binary tree, the depth of each node is the shortest
// distance to the root.
//
// Return the smallest subtree such that it contains all the deepest nodes
// in the original tree.
//
// A node is called the deepest if it has the largest depth in the tree.
// The subtree of a node consists of that node and all its descendants.

class Solution {

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    // Helper class to store depth and subtree root
    class Result {
        int depth;
        TreeNode node;

        Result(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }

    // DFS returns a Result object containing:
    // - depth: maximum depth from this node
    // - node: root of the smallest subtree containing all deepest nodes
    private Result dfs(TreeNode root) {
        if (root == null) {
            return new Result(0, null);
        }

        Result left = dfs(root.left);
        Result right = dfs(root.right);

        // If left subtree is deeper, propagate left result
        if (left.depth > right.depth) {
            return new Result(left.depth + 1, left.node);
        }

        // If right subtree is deeper, propagate right result
        if (right.depth > left.depth) {
            return new Result(right.depth + 1, right.node);
        }

        // If both depths are equal, current node is the answer
        return new Result(left.depth + 1, root);
    }
}


// Explanation:
// We use a bottom-up Depth-First Search.
//
// For each node, DFS computes:
// - The maximum depth reachable from that node
// - The root of the smallest subtree containing all deepest nodes below it
//
// Decision logic:
// - Left deeper → deepest nodes lie entirely in left subtree
// - Right deeper → deepest nodes lie entirely in right subtree
// - Equal depth → deepest nodes are split across both subtrees,
//   so current node is their lowest common ancestor
//
// This guarantees the smallest subtree that contains all deepest nodes.
//
// Time Complexity: O(n)
// Space Complexity: O(n) due to recursion stack