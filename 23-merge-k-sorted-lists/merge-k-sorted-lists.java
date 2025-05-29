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
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = null;
        ListNode last = head;

       PriorityQueue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        });

        for (ListNode l : lists) {
            if (l != null) {
                minHeap.add(l);
            }
        }

        while(!minHeap.isEmpty()) {
            ListNode node = minHeap.remove();

            if(head == null) {
                head = node;
                last = node;
            } else {
                last.next = node;
                last = last.next;
            }

            ListNode nextNode = node.next;
            node.next = null;

            if(nextNode != null) minHeap.add(nextNode);
        }

        return head;
    }
}