package leetcode;

public class LC138_CopyRandomList {

    public static void main(String[] args) {
        RandomListNode head = new RandomListNode(1);
        head.next = new RandomListNode(2);
        head.next.next = new RandomListNode(3);
        head.random = head.next.next; // 1 --> 3
        head.next.random = head; // 2-->1
        head.next.next.random = head.next; // 3-->2
        RandomListNode copyHead = copyRandomList(head);
        while (copyHead!=null) {
            System.out.println(copyHead + ": " + copyHead.label + " random->" + copyHead.random.label);
            copyHead = copyHead.next;
        }

        head = new RandomListNode(1);
        head.random = head;
        System.out.println("original head: " + head);
        copyHead = copyRandomList(head);
        while (copyHead!=null) {
            System.out.println(copyHead + ": " + copyHead.label + " random->" + copyHead.random.label);
            copyHead = copyHead.next;
        }

        head = new RandomListNode(1);
        head.random = null;
        System.out.println("original head: " + head);
        copyHead = copyRandomList(head);
        while (copyHead!=null) {
            System.out.println(copyHead + ": " + copyHead.label + " random->" + copyHead.random);
            copyHead = copyHead.next;
        }
    }

    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;

        RandomListNode copyHead = new RandomListNode(head.label * 100);
        RandomListNode copy = copyHead;
        RandomListNode cur = head;
        // interweave the copied nodes
        while (true) {
            copy.next = cur.next; // copy->next
            cur.next = copy; // cur->copy->next
            cur = copy.next; // copy->next(=cur)

            if (cur != null) {
                copy = new RandomListNode(cur.label * 100);
            } else
                break;
        }

        /*// debug
        cur = head;
        while (cur != null) {
            System.out.print(" " + cur.label + "(" + (cur.random == null ? 0 : cur.random.label) + ")");
            cur = cur.next;
        }
        System.out.println();*/

        // set the random pointers
        copy = copyHead;
        cur = head;
        while (true) {
            //System.out.println(" loop: " + cur.label + " -> " + copy.label);
            if (cur.random == null)
                copy.random = null;
            else
                copy.random = cur.random.next;
            //System.out.println("    " + cur.label + " has random: " + cur.random.label);
            //System.out.println("    " + copy.label + " has random: " + copy.random.label);
            cur = copy.next;
            if (cur == null)
                break;
            copy = cur.next;
        }

        // detach the new and the old
        copy = copyHead;
        cur = head;
        while (true) {
            cur.next = copy.next;
            cur = copy.next;
            if (cur == null) break;
            copy.next = cur.next;
            copy = cur.next;
        }

        return copyHead;
    }

    /**
     * Definition for singly-linked list with a random pointer.
     */
    static class RandomListNode {
         int label;
         RandomListNode next, random;
         RandomListNode(int x) { this.label = x; }
    }
}
