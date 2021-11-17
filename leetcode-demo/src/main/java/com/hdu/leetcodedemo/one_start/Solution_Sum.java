package com.hdu.leetcodedemo.one_start;


/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/10/10
 * @Time 下午7:21
 */
public class Solution_Sum {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder result1 = new StringBuilder();
        StringBuilder result2 = new StringBuilder();
        getNumber(l1, result1);
        getNumber(l2, result2);

        return getListNode(add(result1, result2));
    }

    private String add(StringBuilder s1, StringBuilder s2) {
        String result = "";
        Integer tmpInteger = 0;
        String[] strings1 = s1.toString().split("");
        String[] strings2 = s2.toString().split("");
        int maxIndex = strings1.length > strings2.length ? strings1.length : strings2.length;
        for (int i = 0; i < maxIndex; i++) {
            int number1 = Integer.valueOf(i < strings1.length ? strings1[strings1.length - i - 1] : "0");
            int number2 = Integer.valueOf(i < strings2.length ? strings2[strings2.length - i - 1] : "0");
            int r = number1 + number2 + tmpInteger;
            result = r % 10 + result;
            tmpInteger = r / 10;
        }
        if (tmpInteger > 0) {
            result = tmpInteger + result;
        }
        return result;
    }

    private ListNode getListNode(String str) {
        String[] strings = str.split("");
        ListNode result = new ListNode(Integer.valueOf(strings[strings.length - 1]));
        ListNode tmpNode = result;
        if (strings.length >= 2) {
            for (int i = strings.length - 2; i >= 0; i--) {
                ListNode node = new ListNode(Integer.valueOf(strings[i]));
                tmpNode.next = node;
                tmpNode = node;
            }
        }
        return result;

    }

    private void getNumber(ListNode node, StringBuilder result) {
        if (node == null) {
            return;
        }
        result.insert(0, node.val);
        getNumber(node.next, result);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }


    /**
     * 494. 目标和，回溯
     *
     * @param nums
     * @param target
     */
    public static int findTargetSumWays(int[] nums, int target) {

        int count = 0;
        count = findTarget(nums, 0, 0, target, count);
        return count;
    }

    private static int findTarget(int[] nums, int depth, int sum, int target, int count) {
        if (depth >= nums.length && sum == target) {
            count++;
        } else if (depth < nums.length) {
            count = findTarget(nums, depth + 1, sum + nums[depth], target, count);
            count = findTarget(nums, depth + 1, sum - nums[depth], target, count);
        }
        return count;
    }


    public static void main(String[] args) {
        String s1 = "1000000000000000000000000000001";
        String s2 = "465";
        Solution_Sum instance = new Solution_Sum();
        ListNode result = instance.addTwoNumbers(instance.getListNode(s1), instance.getListNode(s2));
        System.out.println(result.toString());


        System.out.println(findTargetSumWays(new int[]{42, 16, 31, 11, 36, 19, 9, 3, 25, 0, 27, 29, 35, 29, 45, 15, 35, 42, 35, 23}, 39));

    }
}


