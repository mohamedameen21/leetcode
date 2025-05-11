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
    public void recoverTree(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        TreeNode firstViolation = null;
        TreeNode secondViolation = null;


        while(root != null) {
            if(root.left == null) {
                if(list.isEmpty()) list.add(root);
                else {
                    TreeNode prevNode = list.get(list.size() -1);
                    if(prevNode.val > root.val && firstViolation == null) {
                        firstViolation = prevNode;
                        secondViolation = root;
                    } else if(prevNode.val > root.val) {
                        secondViolation = root;
                    }

                    list.add(root);
                }

                root = root.right;
            } else {
                TreeNode cur = root.left;

                while(cur.right != null && cur.right != root) {
                    cur = cur.right;
                }

                if(cur.right == null) {
                    cur.right = root;
                    root = root.left;
                } else {
                    cur.right = null;
                     TreeNode prevNode = list.get(list.size() -1);
                    if(prevNode.val > root.val && firstViolation == null) {
                        firstViolation = prevNode;
                        secondViolation = root;
                    } else if(prevNode.val > root.val) {
                        secondViolation = root;
                    }

                    list.add(root);

                    root = root.right;
                }
            }
        }

        int temp = firstViolation.val;
        firstViolation.val = secondViolation.val;
        secondViolation.val = temp;
    }
}             