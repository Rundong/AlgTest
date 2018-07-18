package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class LC_ConsecutiveOnes2 {

    public static void main(String[] args) {
        LC_ConsecutiveOnes2 obj = new LC_ConsecutiveOnes2();
        System.out.println(obj.findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}, 1));
        System.out.println(obj.findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}, 2));
        System.out.println(obj.findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}, 3));
        System.out.println(obj.findMaxConsecutiveOnes(new int[]{0,0,1}, 2));
        System.out.println(obj.findMaxConsecutiveOnes(new int[]{0,0,0}, 2));
    }

    public int findMaxConsecutiveOnes(int[] nums, int k) {
        int max = 0, firstOne = 0;
        Queue<Integer> qu = new ArrayDeque(k+1);
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                qu.offer(i);
                if(qu.size() > k) {
                    max = Math.max(max, i - firstOne);
                    firstOne = qu.poll() + 1;
                }
            }
            if(i == nums.length - 1) {
                max = Math.max(max, i + 1 - firstOne);
            }
        }

        return max;
    }
}
