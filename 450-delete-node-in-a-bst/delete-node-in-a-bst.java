/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return root;
        if(root.left == null && root.right == null) return key == root.val ? null : root;
        if(root.val == key) return mergeSubtrees(root.left, root.right);
        // for these above conditions the parent will be null so I handled them separetly

        TreeNode parent = null;
        TreeNode cur = root;

        while (cur != null) {
            if (cur.val == key)
                break;
            else if (key < cur.val) {
                parent = cur;
                cur = cur.left;
            } else {
                parent = cur;
                cur = cur.right;
            }
        }

        if(cur == null) return root;

        boolean isLeftChild = parent.left == cur;

        if(cur.left != null) {
            if(isLeftChild) {
                parent.left = cur.left;
                parent.left.right = mergeSubtrees(parent.left.right, cur.right);
            } else {
                parent.right = cur.left;
                parent.right.right = mergeSubtrees(parent.right.right, cur.right);
            }
        } else if(cur.right != null) {
            if(isLeftChild) {
                parent.left = cur.right;
                parent.left.left = mergeSubtrees(cur.left, parent.left.left);
            } else {
                parent.right = cur.right;
                parent.right.left = mergeSubtrees(cur.left, parent.right.left);
            }
        } else {
            if(isLeftChild) parent.left = null;
            else parent.right = null;
        }

        return root;
    }

    private static TreeNode mergeSubtrees(TreeNode leftRoot, TreeNode rightRoot) {
        if(leftRoot == null) return rightRoot;
        if(rightRoot == null) return leftRoot;

        TreeNode cur = rightRoot;
        while(cur.left != null) {
            cur = cur.left;
        }

        cur.left = leftRoot;

        return rightRoot;
    }
}