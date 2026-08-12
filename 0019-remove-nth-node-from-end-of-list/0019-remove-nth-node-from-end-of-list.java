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
    public static int count(ListNode head){
        int count = 0;
        while(head != null){
            count++;
            head = head.next;
        }
        return count;
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = count(head);
        int k = length - n + 1;

        if(head == null || head.next == null){
            return null;
        }

        if(k == 1){
            return head.next;
        }
        ListNode current = head;
        for(int i = 1 ; i < k - 1 && current != null ; i++){
            current = current.next;
        }

        if(current.next == null){
            return head;
        }

        current.next = current.next.next;

        return head;
    }
}