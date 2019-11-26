package leetcode;

public class LC25_ReverseNodesKGroup {

    /* Test cases:
        [1,2]
        3
        [1]
        1
        [1]
        2
        []
        1
        [1,2,3,4,5]
        1
     */

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        int i = 1;
        while (i++ < 10) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }

        LC25_ReverseNodesKGroup algo = new LC25_ReverseNodesKGroup();
        algo.printList(head);

//        ListNode newHead = algo.reverse(head);
//        algo.printList(newHead);

        // algo.reverseKGroup(head, 2);
        algo.reverseKGroup1(head, 4);
    }

    public void reverseKGroup(ListNode head, int k) {
        ListNode pre = null, cur = head, next, groupTail = head, preTail = head;
        int count = 0;
        boolean isHead = true;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            if (++count == k) {
                if (isHead) {
                    head = pre;
                    isHead = false;
                } else {
                    // ... -> preTail, groupTail <- ... <- pre, cur -> next
                    // ... -> pre -> ... -> groupTail, cur -> next
                    // next time, ... -> preTail, groupTail <- ...
                    preTail.next = pre;
                    preTail = groupTail;
                }
                groupTail = cur;
                pre = null;
                count = 0;
                printList(head);
            }
        }
        System.out.println("preTail: " + preTail.val);
        if (!isHead)
            preTail.next = reverse(pre);
        else
            head = reverse(pre);

        printList(head);
    }

    public void reverseKGroup1(ListNode head, int k) {
        // get the length
        ListNode cur = head;
        int n = 0;
        while (cur != null) {
            n++;
            cur = cur.next;
        }

        cur = head;
        ListNode pre = null, next, preTail = null;
        for (int i = 0; i < n / k; i++) {
            ListNode groupTail = cur;
            for (int j = 0; j < k; j++) {
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            if (preTail != null) {
                preTail.next = pre;
            } else {
                head = pre;
            }
            preTail = groupTail;
        }
        if (preTail != null) {
            preTail.next = cur;
        }
        printList(head);
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head, next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ", ");
            head = head.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
