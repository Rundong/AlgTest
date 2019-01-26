package leetcode;

public class LC421_MaximumXOR {

    public static void main(String[] args) {
        int xor = 0;
        System.out.println(((xor << 1) + 1));
        int[] nums = new int[]{3, 10, 5, 25, 2, 8};
        LC421_MaximumXOR obj = new LC421_MaximumXOR();
        int max = obj.findMaximumXOR(nums);
        System.out.println("max xor: " + max);
    }

    public int findMaximumXOR(int[] nums) {
        // build a Trie
        TrieNode root = new TrieNode(0);
        for (int num : nums) {
            TrieNode cur = root;
            for (int i = 31; i >= 0; i--) {
                int bit = num & (1 << i);
                if (bit == 0) {
                    if (cur.left == null)
                        cur.left = new TrieNode(0);
                    cur = cur.left;
                } else {
                    if (cur.right == null)
                        cur.right = new TrieNode(1);
                    cur = cur.right;
                }
            }
        }

        TrieNode cur1 = root;
        int k = 27;
        while (k-- > 0) {
            cur1 = cur1.left;
        }

        // for each number, find the max XOR result
        int max = 0;
        for (int num : nums) {
            int xor = 0, x2 = 0;
            TrieNode cur = root;
            System.out.println("\nnum: " + num);
            for (int i = 31; i >= 0; i--) {
                int bit = num >> i & 1;
                if (num == 25 && i <= 4)
                    System.out.print(" " + bit);
                xor = xor << 1;
                x2 = x2 << 1;
                if (bit == 0) {
                    if (cur.right != null) {
                        if (num == 25 && i <= 4)
                            System.out.print("_right_");
                        xor += 1;
                        x2 += 1;
                        cur = cur.right;
                    } else {
                        if (num == 25 && i <= 4)
                            System.out.print("_left_");
                        cur = cur.left;
                    }
                } else {
                    if (cur.left != null) {
                        if (num == 25 && i <= 4)
                            System.out.print("_left_");
                        xor += 1;
                        cur = cur.left;
                    } else {
                        if (num == 25 && i <= 4)
                            System.out.print("_right_");
                        x2 += 1;
                        cur = cur.right;
                    }
                }
            }
            System.out.println("\n xor: " + xor + ", with " + x2);
            max = Math.max(max, xor);
        }
        return max;
    }

    private static class TrieNode {
        public int bit;
        public TrieNode left, right;
        public TrieNode(int b) {
            bit = b;
            left = null;
            right = null;
        }
    }
}
