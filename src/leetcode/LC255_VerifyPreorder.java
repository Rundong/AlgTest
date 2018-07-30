package leetcode;

import java.util.Stack;

public class LC255_VerifyPreorder {

    public static void main(String[] args) {
        LC255_VerifyPreorder obj = new LC255_VerifyPreorder();
        System.out.println(obj.verifyPreorder(new int[]{1,3,4,2}));
        System.out.println(obj.verifyPreorder(new int[]{5,2,6,1,3}));
        System.out.println(obj.verifyPreorder(new int[]{5,2,1,3,6}));
    }

    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        for(int num : preorder) {
            if(num < max)
                return false;
            while(!stack.isEmpty() && stack.peek() < num) {
                max = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }
}
