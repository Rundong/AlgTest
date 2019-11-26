package leetcode;

import java.util.Stack;

class LC_InorderSuccessorInBST {

    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        LC_InorderSuccessorInBST obj = new LC_InorderSuccessorInBST();
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        node2.left = node1;
        TreeNode suc = obj.inorderSuccessor(node2, node1);
        System.out.println("\nsuccessor: " + (suc == null? "null" : suc.val));
        suc = obj.inorderSuccessor(node2, node2);
        System.out.println("\nsuccessor: " + (suc == null? "null" : suc.val));
    }

    private static void test2() {
        TreeNode node2 = new TreeNode(2);
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node2.left = node1;
        node3.left = node2;
        node3.right = node4;
        node5.left = node3;
        node5.right = node6;
        LC_InorderSuccessorInBST obj = new LC_InorderSuccessorInBST();
//        TreeNode suc = obj.inorderSuccessor(node5, node2);
        TreeNode suc = obj.inorderSuccessorIterative(node5, node2);
        System.out.println("\nsuccessor: " + (suc == null? "null" : suc.val));
    }

    private TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null)
            return null;

        if(root.val <= p.val) {
            if(root.val == p.val)
                found = true;
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode left = inorderSuccessor(root.left, p);
            if(left == null) {
                return found ? root : null;
            } else {
                return left;
            }
        }
    }

    private TreeNode inorderSuccessorIterative(TreeNode root, TreeNode p) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        boolean found = false;
        while(cur != null || !stack.isEmpty()) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if(found) {
                return cur;
            }
            if(cur == p) {
                found = true;
            }
            if(cur != null) {
                System.out.print(cur.val + ",");
                cur = cur.right;
            }
        }
        return null;
    }

    private boolean found = false;

 /* Definition for a binary tree node.*/
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}