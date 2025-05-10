/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    // Morris Traversal TC:O(N) SC: O(1)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        while(root != null) {
            if(root.left == null) {
                list.add(root.val);  // here there is not left node so print the current node for (Inorder traversal)
                root = root.right; // then move right
            } else {
                TreeNode cur = root.left;

                while(cur.right != null && cur.right != root) {
                    cur = cur.right;
                }

                // create link
                if(cur.right == null) {
                    cur.right = root;
                    root = root.left;
                } else if(cur.right == root) { // delete link
                    cur.right = null;
                    list.add(root.val);
                    root = root.right;
                }
            }
        }

        return list;
    }
}