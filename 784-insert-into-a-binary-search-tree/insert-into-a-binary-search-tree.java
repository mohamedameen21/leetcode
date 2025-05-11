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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);

        TreeNode prevNode = null;
        TreeNode cur = root;

        while(cur != null) {
            if(val <= cur.val) {
                prevNode = cur;
                cur = cur.left;
            } else {
                prevNode = cur;
                cur = cur.right;
            }
        }

        TreeNode newNode = new TreeNode(val);

        if(prevNode.val <= val) prevNode.right = newNode;
        else prevNode.left = newNode;

        return root;
    }
}