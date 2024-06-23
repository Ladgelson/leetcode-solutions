import java.math.BigInteger;

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode res = dummy;
        int total = 0, carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            total = carry;

            if (l1 != null) {
                total += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                total += l2.val;
                l2 = l2.next;
            }

            int num = total % 10;
            carry = total / 10;
            dummy.next = new ListNode(num);
            dummy = dummy.next;
        }

        return res.next;
    }

    // Basic Solution
    public ListNode addTwoNumbersBasic(ListNode l1, ListNode l2) {
        BigInteger total = toInt(l1).add(toInt(l2));
        return toList(total);
    }

    private BigInteger toInt(ListNode a) {
        BigInteger sum = BigInteger.ZERO;
        BigInteger base = BigInteger.ONE;
        while (a != null) {
            sum = sum.add(base.multiply(BigInteger.valueOf(a.val)));
            base = base.multiply(BigInteger.TEN);
            a = a.next;
        }
        return sum;
    }

    private ListNode toList(BigInteger value) {
        if (value.equals(BigInteger.ZERO)) {
            return new ListNode(0);
        }
        ListNode head = null;
        ListNode lastNode = null;
        while (!value.equals(BigInteger.ZERO)) {
            int digit = value.mod(BigInteger.TEN).intValue();
            value = value.divide(BigInteger.TEN);
            ListNode node = new ListNode(digit);
            if (head == null) {
                head = node;
            } else {
                lastNode.next = node;
            }
            lastNode = node;
        }
        return head;
    }
}