package com.hdu.leetcodedemo.base;

import java.util.*;

/**
 * @ClassName：AlgorithmUtil
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/4/26 4:06 下午
 * @Versiion：1.0
 */
public class AlgorithmUtil {


    /**
     * 连输子数组最大和
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int current = nums[0];
        int former = 0;
        for (int n : nums) {
            current = n;
            if (former > 0) {
                current = current + former;
            }
            max = Math.max(current, max);
            former = current;
        }
        return max;

    }

    public static int maxSubArrayOne(int[] nums) {
        int max = nums[0];
        int former = 0;//用于记录dp[i-1]的值，对于dp[0]而言，其前面的dp[-1]=0
        int cur = nums[0];//用于记录dp[i]的值
        for (int num : nums) {
            cur = num;
            if (former > 0) cur += former;
            max = Math.max(cur, max);
            former = cur;
        }
        return max;
    }

    public static int singleNumber(int[] nums) {
        int sum = 0;
        int flag = -1;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + flag;
            flag = -1 * flag;
        }
        return sum;
    }

    /**
     * 高频词
     *
     * @param words
     * @param k
     * @return
     */
    public static List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word)) {
                map.put(word, 0);
            }
            Integer count = map.get(word);
            count = count + 1;
            map.put(word, count);
        }

        HashMap<Integer, List> countMap = new HashMap<>();
        for (Map.Entry entry : map.entrySet()) {
            String word = entry.getKey().toString();
            Integer count = Integer.valueOf(entry.getValue().toString());
            if (!countMap.containsKey(count)) {
                countMap.put(count, new ArrayList());
            }
            countMap.get(count).add(word);
        }

        List<Integer> sortedList = new ArrayList<>(countMap.keySet());
        sortedList.sort(Comparator.comparingInt(t -> (Integer) t).reversed());
        List<String> sortedWords = new ArrayList<>();

        for (int c : sortedList) {
            List<String> sameCountListString = countMap.get(c);
            sameCountListString.sort(String::compareTo);
            sortedWords.addAll(sameCountListString);
        }

        return sortedWords.subList(0, k);
    }

    /**
     * 四的幂
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfFour(int n) {
        Double r = logN(n, 4);
        if (r.intValue() != r) {
            return false;
        }
        return true;

    }

    public static Double logN(int n, int m) {
        return Math.log(n) / Math.log(m);
    }


    /**
     * 最小k个数
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            for (int m = i; m < arr.length; m++) {
                int value = arr[m];
                if (min > value) {
                    int tmp = value;
                    arr[m] = min;
                    arr[i] = tmp;
                }

            }
        }
        System.out.println(arr);
        return null;
    }

    public static long dpN(int n) {
        long[] dpTable = new long[n];
        dpTable[0] = dpTable[1] = 1L;
        for (int i = 2; i < n; i++) {
            dpTable[i] = dpTable[i - 1] + dpTable[i - 2];
        }
        return dpTable[n - 1];
    }

    /**
     * 凑零钱
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 0; i < amount + 1; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                int res = Math.min(dp[i], 1 + dp[i - coin]);
                dp[i] = res;
            }
        }
        return dp[amount] == 0 ? -1 : dp[amount];
    }

    /**
     * 爬楼梯 ，
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        if (n <= 2)
            return n;
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 杨辉三角：给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        results.add(Arrays.asList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> tmpResult = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                int left = j - 1 < 0 ? 0 : results.get(i - 1).get(j - 1);
                int right = j >= results.get(i - 1).size() ? 0 : results.get(i - 1).get(j);
                int t = left + right;
                tmpResult.add(t);
            }
            results.add(tmpResult);
        }
        return results;
    }


    public static List<Integer> getRow(int rowIndex) {
        return generate(rowIndex).get(rowIndex);
    }


    /**
     * 1137. 第 N 个泰波那契数, 定义为：T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
     *
     * @param n
     * @return
     */
    public static int tribonacci(int n) {
        int[] dp = n <= 3 ? new int[3] : new int[n];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        if (n > 3) {
            for (int i = 3; i < n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
            }
        }
        return dp[n - 1];

    }


    /**
     * 509. 斐波那契数 斐波那契数，通常用 F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
     *
     * @param n F(0) = 0，F(1) = 1
     *          F(n) = F(n - 1) + F(n - 2)，其中 n > 1
     * @return
     */
    public static int fib(int n) {
        if (n < 2) {
            return n - 1;
        }
        int[] dp = new int[n];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     * 翻转数位:给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
     *
     * @param num
     * @return
     */
    public static int reverseBits(int num) {
        int cur = 0; //表示当前连续1的位数， 遇到1则加1， 遇到零则置0
        return cur;
    }


    /**
     * 动态规划：
     * 338. 比特位计数
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     *
     * @param n
     * @return
     */
    public static int[] countBits(int n) {
        return null;
    }


    public static int[] calSlidingWindowsArr(int[] numbers, int k) {
        // write code here
        int leftIndex = 0;
        int rightIndex = k - 1;
        int[] maxNumber = new int[numbers.length - k + 1];
        for (int i = 0; i <= numbers.length - k; i++) {
            leftIndex = i;
            rightIndex = i + k - 1;
            int max = numbers[leftIndex];
            for (int n = leftIndex; n <= rightIndex; n++) {
                if (max < numbers[n]) {
                    max = numbers[n];
                }
            }
            maxNumber[i] = max;
        }
        return maxNumber;
    }

    public static void main(String[] args) {
//        String[] words = new String[]{"i", "love", "leetcode", "i", "love", "coding"};
//        System.out.println(topKFrequent(words, 2));
//        System.out.println(isPowerOfFour(2));

        System.out.println(dpN(20));

        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        List<List<Integer>> dps = generate(4);
        System.out.println(dps);

        System.out.println("tribonacci 泰波那契数 为：" + tribonacci(4));


        System.out.println("翻转位数：" + reverseBits(-1));

        int[] numbers = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        for (int n : calSlidingWindowsArr(numbers, k)) {
            System.out.println(n);
        }

    }
}