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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        while(root != null) {
            if(root.right == null) {
                list.add(root.val);
                root = root.left;
            } else {
                TreeNode cur = root.right;

                while(cur.left != null && cur.left != root) {
                    cur = cur.left;
                }

                if(cur.left == null) {
                    cur.left = root;
                    list.add(root.val);
                    root = root.right;
                } else {
                    cur.left = null;
                    root = root.left;
                }
            }
        }

        Collections.reverse(list);

        return list;
    }
}