# Linked List Addition Problem

[Link to the problem](https://leetcode.com/problems/add-two-numbers/description/)

This README explains the step-by-step logic and implementation details of two solutions for the problem of adding two numbers represented by linked lists in reverse order.

## Problem Description

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

### Example

**Input:**
- l1 = [2,4,3]
- l2 = [5,6,4]

**Output:**
- [7,0,8]

**Explanation:**
- 342 + 465 = 807

### Constraints

- The number of nodes in each linked list is in the range [1, 100].
- 0 <= Node.val <= 9
- It is guaranteed that the list represents a number that does not have leading zeros.

## Solution 1: Basic Solution Using BigInteger

### Step-by-Step Logic

1. **Convert Linked List to Integer**:
    - Traverse the linked list, multiplying each digit by its positional value and summing them up to get the integer value of the linked list.

2. **Add the Two Integers**:
    - Convert both linked lists to their integer representations and add them using BigInteger to handle large values.

3. **Convert the Resulting Integer Back to a Linked List**:
    - Convert the resulting integer back into a linked list, where each node represents a single digit.

### Code Implementation

```java
public class Solution {
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
```

### Explanation

- **toInt(ListNode a)**: Converts the linked list to an integer by traversing the list and summing up the digits multiplied by their positional values.
- **toList(BigInteger value)**: Converts the BigInteger value back to a linked list by extracting each digit and creating a new node for it.

## Solution 2: Optimized Solution

### Step-by-Step Logic

1. **Initialize Variables**:
    - Use a dummy node to simplify handling the head of the result linked list.
    - Initialize `total` and `carry` to handle sum and carry-over during addition.

2. **Traverse and Add Nodes**:
    - Traverse both linked lists until the end of both lists and until there is no carry-over.
    - For each pair of nodes (or single node if one list is shorter), add the values together along with the carry.
    - Calculate the new carry and the digit to be added to the result list.

3. **Create Result List Nodes**:
    - Create new nodes for each digit of the result and link them together.

### Code Implementation

```java
public class Solution {
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
}
```

### Explanation

- **Initialization**: A dummy node is used to simplify edge cases and `total` and `carry` are used to manage the sum and carry-over.
- **Traverse and Add Nodes**: Both linked lists are traversed simultaneously, and corresponding digits are added along with any carry. The resulting sum's unit digit becomes a node in the result list, and the carry is passed to the next iteration.
- **Create Result List Nodes**: Nodes are created for each digit of the resulting sum and linked together to form the final result list.

## Conclusion

- **Solution 1** is straightforward and uses BigInteger to handle large numbers but may not be as efficient due to the conversion between linked lists and BigInteger.
- **Solution 2** is more optimized for the problem constraints and directly performs addition using linked list nodes, ensuring efficient handling of the linked list structure without additional conversions.