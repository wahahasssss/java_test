package com.hdu.atomic;

import java.util.*;

/**
 * @ClassName：MySumMain
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/9/5 5:18 下午
 * @Versiion：1.0
 */
public class MySumMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean flag = false;
        int number = 0;

        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String input = in.nextLine();
            if (!flag && !input.contains(" ")){
                number = Integer.valueOf(input);
                flag = true;
            }else if (flag){
                List<Integer> inputNumbers = new ArrayList<>();
                String[] numbers = input.split(" ");
                for (String n : numbers){
                    inputNumbers.add(Integer.valueOf(n));
                }
                System.out.println(lookSum(number, inputNumbers));
                flag = false;
            }
        }
    }

    public static Integer lookSum(Integer count, List<Integer> values){
        int sum = 0;

        for (int i = 1; i <= count;i++){
            Set<String> sets = new HashSet<>();
            List<List<Integer>> allList = childSetWithCount(i, values);
            for (List<Integer> l:allList){
                l.sort(Integer::compareTo);
                String repeatString = joinString(l);
                Integer tmpSum = 0;
                for (int num:l){
                    tmpSum = tmpSum + num;
                }
                if (tmpSum == 24 && !sets.contains(repeatString)){
                    sum = sum + 1;
                }
                sets.add(repeatString);
            }
        }
        return sum;
    }
    public static  List<List<Integer>> childSetWithCount(int setSize, List<Integer> values){
        List<List<Integer>> childSets = new ArrayList<>();
        for (int i = 0;i < values.size();i++){
            List<Integer> tmpList = new ArrayList<>();
            tmpList.add(values.get(i));
            List<Integer> copyArray = copyList(values);
            copyArray.remove(i);
            childSets(setSize - 1, tmpList, childSets, copyArray);
        }
        return childSets;
    }

    public static void childSets(int c, List<Integer> tmpList,  List<List<Integer>> childSets, List<Integer> values){
        if (c == 0){
            childSets.add(tmpList);
        } else if (c > 0) {
            for(int i = 0; i < values.size(); i++){
                List<Integer> copyTmpList = copyList(tmpList);
                copyTmpList.add(values.get(i));
                List<Integer> copyArray =copyList(values);
                copyArray.remove(i);
                childSets(c - 1, copyTmpList, childSets, copyArray);
            }
        }
    }

    public static List<Integer> copyList(List<Integer> source){
        List<Integer> results = new ArrayList<>();
        for (Integer i:source){
            results.add(i);
        }
        return results;
    }

    public static String joinString(List<Integer> values){
        StringBuilder sb = new StringBuilder();
        for (Integer v:values){
            sb.append(v);
            sb.append("_");
        }
        return sb.toString();
    }


}