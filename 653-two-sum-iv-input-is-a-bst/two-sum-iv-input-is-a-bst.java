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
    public boolean findTarget(TreeNode root, int k) {
        return findTarget(root, k, new HashSet<>());
    }

    private static boolean findTarget(TreeNode root, int k, Set<Integer> hashSet) {
        if(root == null) return false;

        if(hashSet.contains(k - root.val)) return true;

        hashSet.add(root.val);

        return findTarget(root.left, k, hashSet) || findTarget(root.right, k, hashSet);
    }
}