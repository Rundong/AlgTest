package datometry;

public class Solution {

    public static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
   }

    public ListNode label(ListNode head) {
        ListNode cur = head;
        int value = 1;
        while (cur != null) {
            cur.val = value;
            value++;
            cur = cur.next;
        }

        return head;
    }


    public ListNode reverse(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode pre = head;
        head = head.next;
        pre.next = null;
        ListNode temp;
        while (head != null) {
            temp = head.next; // pre -> head -> temp
            head.next = pre;  // pre <- head -?- temp
            pre = head;       // pre.next <- pre=head -?- temp
            head = temp;      // pre.next <- pre -?- head=temp
        }
        head = pre;

        return head;
    }

    public  ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        ListNode tail = head; // head of a group of k nodes will become tail after reverse
        ListNode[] res = new ListNode[2];
        if (cur != null) {
            reverseAGroup(cur, k, res);
            head = res[0];
            cur = res[1];
        }

        while (cur != null) {
            reverseAGroup(cur, k, res);
            //printList(res[0]);
            tail.next = res[0]; // tail of the previous k nodes should point to the head of current k nodes
            tail = cur;         // the old head now becomes the tail
            cur = res[1];
        }

        return head;
    }

    // helper: reverse k nodes from head, return the new head and the node after these k nodes
    public void reverseAGroup(ListNode head, int k, ListNode[] res) {
        if (head.next == null) {
            res[0] = head;
            res[1] = null;
            return;
        }
        ListNode cur = head;
        ListNode pre = cur, temp;
        cur = cur.next;
        pre.next = null;
        for (int i = 0; i < k - 1; i++) {
            temp = cur.next;        // pre -> cur -> temp
            cur.next = pre;         // pre <- cur -||- temp
            pre = cur;              // pre.next <- pre=cur -||- temp
            cur = temp;             // pre.next <- pre -||- cur
            if (cur == null && i < k - 2) { // less than k nodes, reverse back
                //System.out.print(" reverse back: " + pre.val + " -- ");
                //printList(pre);
                reverse(pre);
                pre.next = null;
                res[0] = head;
                res[1] = null;
                return;
            }
        }
        head.next = cur;

        res[0] = pre;
        res[1] = cur;
    }

    public void printList(ListNode head) {
        ListNode cur = head;
        int count = 0;
        while(cur != null) {
            System.out.print(cur.val + ", ");
            cur = cur.next;
            if (++count > 100) break;
        }
        System.out.println();
    }


    // driver
    public static void main(String[] args) {
        //testLabel();
//        testReverse();
        //testReverseAGroup();
        testReverseKGroup();
    }

    public static void testReverseKGroup() {
        System.out.println("test reverseKGroup()");
        Solution obj = new Solution();

        // test case 1
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next.next = new ListNode(8);
        head.next.next.next.next.next.next.next.next.next = new ListNode(9);
        System.out.println("\ntest case 1\n  original list: ");
        obj.printList(head);

        head = obj.reverseKGroup(head, 19);
        obj.printList(head);

        // test case 2
        head = new ListNode(0);
        System.out.println("\ntest case 2\n  original list: ");
        obj.printList(head);
        head = obj.reverseKGroup(head, 2);
        System.out.println(head.val + ", next: " + head.next);

        // test case 3
        System.out.println("\ntest case 3\n  original list: null");
        head = obj.reverseKGroup(null, 3);
        System.out.println("null? " + head);
    }

    public static void testReverseAGroup() {
        Solution obj = new Solution();
        ListNode[] res = new ListNode[2];

        // test case 1
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next = new ListNode(6);
        System.out.println("\ntest case 1\n  original list: ");
        obj.printList(head);

        obj.reverseAGroup(head, 3, res);
        head = res[0];
        obj.printList(head);

        // test case 2
        head = new ListNode(0);
        System.out.println("\ntest case 2\n  original list: ");
        obj.printList(head);
        obj.reverseAGroup(head, 2, res);
        head = res[0];
        System.out.println(head.val + ", next: " + head.next);

        // test case 3
        obj.reverseAGroup(null, 3, res);
        head = res[0];
        System.out.println("null? " + head);
    }

    public static void testReverse() {
        Solution obj = new Solution();

        // test case 1
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);

        System.out.println("test case 1: orig: ");
        obj.printList(head);
        head = obj.reverse(head);
        obj.printList(head);

        // test case 2
        head = new ListNode(0);
        head = obj.reverse(head);
        System.out.println(head.val + ", next: " + head.next);

        // test case 3
        head = obj.reverse(null);
        System.out.println("null? " + head);
    }

    public static void testLabel() {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(-1);
        Solution obj = new Solution();
        obj.label(head);
        System.out.println("list: " + head.val);
        System.out.println(head.next.val);
        System.out.println(head.next.next.val);

        head = null;
        obj.label(head);
        System.out.println("null?: " + head);
    }

}
