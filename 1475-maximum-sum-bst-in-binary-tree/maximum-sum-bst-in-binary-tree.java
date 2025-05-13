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
    static class NodeInfo {
        int minVal;
        int maxVal;
        int sum;

        public NodeInfo(int minVal, int maxVal, int sum) {
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.sum = sum;
        }
    }

    public int maxSumBST(TreeNode root) {
        int[] maxSize = {Integer.MIN_VALUE};
        getMaxSumBst(root, maxSize);

        return Math.max(0, maxSize[0]);
    }

    private static NodeInfo getMaxSumBst(TreeNode root, int[] maxSize) {
        if(root == null) {
            return new NodeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        NodeInfo left = getMaxSumBst(root.left, maxSize);
        NodeInfo right = getMaxSumBst(root.right, maxSize);

        if(root.val > left.maxVal && root.val < right.minVal) {
            int min = Math.min(root.val, left.minVal);
            int max = Math.max(root.val, right.maxVal);
            int sum = root.val + left.sum + right.sum;

            maxSize[0] = Math.max(maxSize[0], sum);

            return new NodeInfo(min, max, sum);
        }

        return new NodeInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.sum, right.sum));
    }

}