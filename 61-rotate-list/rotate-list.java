/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) return head;

        ListNode slow = head;
        ListNode fast = head;

        int lenghtOfTheList = 0;
        ListNode cur = head;
        while(cur != null) {
            lenghtOfTheList++;
            cur = cur.next;
        }

        k = k % lenghtOfTheList;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        fast.next = head;
        ListNode newHead = slow.next;
        slow.next = null;

        return newHead;
    }
}