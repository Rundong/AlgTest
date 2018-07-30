package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LC145_BinaryTreePostorder {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        LC145_BinaryTreePostorder obj = new LC145_BinaryTreePostorder();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node2.left = new TreeNode(3);
        node1.right = node2;

        List<Integer> res = obj.postorderTraversal(node1);
        System.out.println(Arrays.toString(res.toArray()));
        obj.printPostorder(node1);
    }

    void printPostorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> directions = new Stack<>(); // 0: cur is a left child, 1: cur is a right child
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            if(cur != null) {
                stack.push(cur);
                directions.push(0);
                cur = cur.left;
            } else {
                if(directions.pop() == 0) {
                    cur = stack.peek();
                    directions.push(1);
                    cur = cur.right;
                } else {
                    cur = stack.pop();
                    System.out.print(cur.val+",");
                    cur = null; // notice: key operation, signaling that this branch has been traversed
                }
            }
        }
        System.out.println();
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> directions = new Stack<>(); // 0: cur is a left child, 1: cur is a right child, 2: cur is traversed
        TreeNode cur = root;
        int dir = 0;
        while(cur != null || !stack.isEmpty()) {
            if(directions.isEmpty() || directions.peek() == 1) { // cur is root or cur is a right child
                while(cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                    directions.push(0);
                }
            } else if (directions.peek() == 2) {
                directions.pop();
            }
            if(directions.isEmpty())
                break;
            dir = directions.pop();
            cur = stack.peek();
            if(dir == 0 && cur.right != null) { // cur's left child has been visited
                directions.push(1);
                cur = cur.right;
            } else { // (1) dir == 1 meaning cur's right branch has been visited, or (2) cur's left branch has been visited and cur has no right branch
                res.add(cur.val);
                stack.pop();
                directions.push(2); // indicates cur is finished, needs to go back to cur's parent
            }
        }
        return res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}
