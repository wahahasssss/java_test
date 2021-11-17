package com.hdu.leetcodedemo.one_start;

import java.util.ArrayList;
import java.util.List;

/**
 * DESCRIPTION:
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author shushoufu
 * @Date 2018/8/27
 * @Time 下午8:28
 */
public class LinkedListSum {
    final static String NUMBER_STRING = "0123456789";

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        long sum = getNumber(l1) + getNumber(l2);
        return numberToListNode(Long.valueOf(sum));
    }

    private ListNode numberToListNode(Long number) {
        String sumString = String.valueOf(number);
        ListNode resultNode = null;
        for (String s : sumString.split("")) {
            if (NUMBER_STRING.contains(s)) {
                ListNode tmpNode = new ListNode(Long.valueOf(s));
                if (resultNode != null) {
                    tmpNode.next = resultNode;
                }
                resultNode = tmpNode;
            }

        }
        return resultNode;
    }

    private Long getNumber(ListNode listNode) {
        int n = 0;
        ListNode tmp = listNode;
        long sum = 0;
        while (tmp != null) {
            sum = sum + (long) (tmp.val * Math.pow(10, n));
            n = n + 1;
            tmp = tmp.next;
        }
        return sum;
    }

    public static class ListNode {
        long val;
        ListNode next;

        public ListNode(long x) {
            val = x;
        }

        public ListNode() {
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }


    public static void main(String[] args) {
        LinkedListSum sum = new LinkedListSum();
        System.out.println(sum.addTwoNumbers(sum.numberToListNode(9L), sum.numberToListNode(9999999991L)));
    }
}
