package leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC102_BinaryTreeLevelOrder {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        LC102_BinaryTreeLevelOrder obj = new LC102_BinaryTreeLevelOrder();
        LC102_BinaryTreeLevelOrder.TreeNode node3 = new LC102_BinaryTreeLevelOrder.TreeNode(3);
        LC102_BinaryTreeLevelOrder.TreeNode node9 = new LC102_BinaryTreeLevelOrder.TreeNode(9);
        LC102_BinaryTreeLevelOrder.TreeNode node20 = new LC102_BinaryTreeLevelOrder.TreeNode(20);
        LC102_BinaryTreeLevelOrder.TreeNode node15 = new LC102_BinaryTreeLevelOrder.TreeNode(15);
        LC102_BinaryTreeLevelOrder.TreeNode node17 = new LC102_BinaryTreeLevelOrder.TreeNode(17);
        node3.left = node9; node3.right = node20;
        node20.left = node15; node20.right = node17;

        List<List<Integer>> res = obj.levelOrder(node3);
        System.out.println(res.size());
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> sublist = new ArrayList<>();
        int count = 0, subtotal = 1, nextLevelTotal = 0;
        Deque<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        while(!qu.isEmpty()) {
            TreeNode cur = qu.poll();
            System.out.print(cur.val + ",");
            if(cur.left != null) {
                qu.add(cur.left);
                nextLevelTotal++;
            }
            if(cur.right != null) {
                qu.add(cur.right);
                nextLevelTotal++;
            }

            sublist.add(cur.val);
            if(++count == subtotal) {
                System.out.println(" qu size: " + qu.size());
                count = 0;
                res.add(sublist);
                sublist = new ArrayList<>();
                subtotal = nextLevelTotal;
                nextLevelTotal = 0;
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
