package com.hdu.leetcodedemo.str;

import java.util.*;

/**
 * @ClassName：StringUtil
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/4/21 2:40 下午
 * @Versiion：1.0
 */
public class StringUtil {
    private static final List<Character> NUMBER_CHARS = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');

    public static Integer lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        List<Character> tmpList = new ArrayList<>();
        Integer maxlength = 0;
        for (int i = 0; i < chars.length; i++) {
            tmpList.clear();
            for (int m = i; m < chars.length; m++) {
                if (tmpList.contains(chars[m])) {
                    maxlength = Math.max(maxlength, tmpList.size());
                    tmpList.clear();
                }
                tmpList.add(chars[m]);
            }
            maxlength = Math.max(maxlength, tmpList.size());
        }
        return Math.max(maxlength, tmpList.size());
    }

    //找出所有的重复字符的下标 进行计算
    public static Integer lengthOfLongestSubstringV2(String s) {
        Set<Character> occ = new HashSet<Character>();
        int sLength = s.length();
        int rPoint = -1;
        int maxLength = 0;
        for (int i = 0; i < sLength; i++) {
            if (i != 0) {
                occ.remove(s.charAt(i - 1));
            }
            while (rPoint + 1 < s.length() && !occ.contains(s.charAt(rPoint + 1))) {

                occ.add(s.charAt(rPoint + 1));
                rPoint++;
            }
            maxLength = Math.max(maxLength, rPoint - i + 1);
        }
        return maxLength;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> numbers = new ArrayList();
        for (int n : nums1) {
            numbers.add(n);
        }
        for (int n : nums2) {
            numbers.add(n);
        }
        if (numbers.size() == 0) {
            return 0;
        }

        numbers.sort(Integer::compareTo);
        if (numbers.size() % 2 == 0) {
            int half = numbers.size() / 2;
            int half2 = half - 1;
            return (numbers.get(half) + numbers.get(half2)) / 2.0;
        } else {
            int half = numbers.size() / 2;
            return numbers.get(half);
        }

    }

    /**
     * 最长回文子串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        Set<Character> occ = new HashSet<Character>();
        int sLength = s.length();
        int rPoint = -1;
        int maxLength = 0;
        for (int i = 0; i < sLength; i++) {
            if (i != 0) {
                occ.remove(s.charAt(i - 1));
            }
            while (rPoint + 1 < s.length() && !occ.contains(s.charAt(rPoint + 1))) {

                occ.add(s.charAt(rPoint + 1));
                rPoint++;
            }
            maxLength = Math.max(maxLength, rPoint - i + 1);
        }
        return "";
    }

    public static int reverse(int x) {
        boolean flag = true;
        if (x < 0) {
            flag = false;
        }
        x = Math.abs(x);
        int sum = 0;
        do {
            int y = x % 10;
            if ((Integer.MAX_VALUE - y) / 10 < sum) {
                return 0;
            }
            sum = sum * 10 + y;
        } while ((x = x / 10) > 0);
        return flag ? sum : (-1 * sum);
    }

    public static int myAtoi(String s) {
        CalState automaton = new CalState();
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            automaton.get(s.charAt(i));
        }
        return automaton.sum;

    }

    /**
     * 判断一个数是否为回文数，翻转之后为一样的。
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        String string = String.valueOf(x);
        int length = string.length();
        int leftIndex = 0;
        int rigthIndex = length - 1;
        boolean isPalindrome = true;
        while (leftIndex <= rigthIndex) {
            if (string.charAt(leftIndex) != string.charAt(rigthIndex)) {
                return false;
            }
            leftIndex++;
            rigthIndex--;
        }
        return isPalindrome;
    }


    public static void main(String[] args) {
//        System.out.println(StringUtil.lengthOfLongestSubstringV2("pwwkew"));
//        System.out.println(StringUtil.findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
//        System.out.println(reverse(-1567889239));
//        System.out.println(myAtoi("   -42"));
        System.out.println(isPalindrome(121));
    }


    static class CalState {
        int sum = 0;
        int sign = 1;
        private String state = "start";
        private Map<String, String[]> table = new HashMap<String, String[]>() {{
            put("start", new String[]{"start", "signed", "in_number", "end"});
            put("signed", new String[]{"end", "end", "in_number", "end"});
            put("in_number", new String[]{"end", "end", "in_number", "end"});
            put("end", new String[]{"end", "end", "end", "end"});
        }};

        public void get(char c) {
            state = table.get(state)[get_col(c)];
            if (state.equals("signed")) {
                sign = c == '-' ? -1 : 1;
            } else if (state.equals("in_number")) {
                int number = c - '0';
                if (sign == 1 && (Integer.MAX_VALUE - number) / 10 < sum) {
                    sum = Integer.MAX_VALUE;
                } else if (sign == -1 && (Integer.MIN_VALUE + number) / 10 > sum) {
                    sum = Integer.MIN_VALUE;
                } else {
                    sum = sum * 10 + sign * number;
                }

            }
        }

        private int get_col(char c) {
            if (c == ' ') {
                return 0;
            } else if (c == '-' || c == '+') {
                return 1;
            } else if (NUMBER_CHARS.contains(c)) {
                return 2;
            }
            return 3;
        }
    }
}