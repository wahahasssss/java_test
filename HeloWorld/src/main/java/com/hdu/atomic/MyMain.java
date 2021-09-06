package com.hdu.atomic;
import java.util.Scanner;
import java.util.*;
public class MyMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String input = in.nextLine();
            System.out.println(countWord(input));
        }
    }

    public static String countWord(String input){
        char[] inputChar = input.toCharArray();

        HashMap<Character, Integer> charCountMap = new HashMap<>();
        for (char c:inputChar){
            if (!charCountMap.containsKey(c)){
                charCountMap.put(c, 1);
            }else {
                int count = charCountMap.get(c);
                count ++;
                charCountMap.put(c, count);
            }
        }
        List<Character> chars = new ArrayList<>();
        for (Character c:charCountMap.keySet()){
            chars.add(c);
        }
        chars.sort(Comparator.comparing(Character::charValue));
        StringBuilder sb = new StringBuilder();
        for (Character c:chars){
            sb.append(c);
            sb.append(charCountMap.get(c));
        }
        return sb.toString();
    }
}