package cn.xieyongjie.leetcode;

/**
 * 题目：两数相加
 * 给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 总结：
 * 遇到题目先分析有没有简单的方案,从简入手
 * 引用传递（修改形参，原参也会改变）
 * 学习如何使用哑结点省略代码
 *
 * @author YongJie-Xie
 * @data 08/12/2018
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // Show method result
        ListNode l1 = new ListNode(9);
        // l1.next = new ListNode(4);
        // l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);
        l2.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next = new ListNode(9);
        l2.next.next.next.next.next.next.next.next.next = new ListNode(9);

        ListNode lt = new AddTwoNumbers().addTwoNumbers1(l1, l2);
        while (lt != null) {
            System.out.print(lt.val + ", ");
            lt = lt.next;
        }
        System.out.println();

        System.out.println("执行时间：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    /**
     * 我的方法：
     * 先转化为十进制，相加再写入链表，事实证明暴力破解没办法解决，此方法作废。
     *
     * @param l1 非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字
     * @param l2 非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字
     * @return 两数相加的新链表
     */
    private ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        long n1 = 0, n2 = 0;
        for (int i = 0; l1 != null; i++) {
            n1 += l1.val * Math.pow(10, i);
            l1 = l1.next;
        }
        for (int i = 0; l2 != null; i++) {
            n2 += l2.val * Math.pow(10, i);
            l2 = l2.next;
        }
        long n3 = n1 + n2;
        if (n3 == 0) {
            return l3;
        }
        ListNode temp = l3;
        while (n3 > 0) {
            long val = n3 % 10;
            temp.next = new ListNode((int)val);
            temp = temp.next;
            n3 /= 10;
        }
        return l3.next;
    }

    /**
     * 方法1：
     * 初等数学
     *
     * @param l1 非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字
     * @param l2 非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字
     * @return 两数相加的新链表
     */
    private ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode a = l1, b = l2, temp = dummyHead;
        int carry = 0;
        while (a != null || b != null) {
            int x = (a != null) ? a.val : 0;
            int y = (b != null) ? b.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
            if (a != null) {
                a = a.next;
            }
            if (b != null) {
                b = b.next;
            }
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

}

/**
 * 此题自带的类
 */
class ListNode {

    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

}