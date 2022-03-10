package string;

import java.util.Stack;

/**
 * 获取一个字符串中最长的回文子串，给定的字符串为数字或字母组成
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromeString {
    public static String test(String test){
        if(test == null || "".equals(test)){
            return null;
        }
        if(test.length() == 1){
            return test;
        }
        char[] chars = test.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<chars.length;i++){
            stack.push(chars[i]);
        }

        int startIndex = 0;
        int endIndex = 0;
        int maxLen = 1;
        for(int i=0;i<chars.length;i++){
            if(chars[i] != stack.pop()){
                //startIndex = i;
                continue;
            }else{
                if(startIndex == 0){
                    endIndex = startIndex = i;
                }else if(startIndex > endIndex){
                    startIndex = i;
                }else{
                    endIndex = i;
                }
                maxLen = endIndex - startIndex;
            }
        }
        return test.substring(startIndex,endIndex+1);
    }

    public static void main(String[] args) {
        String test = "cbba";// 12324
        String result = test(test);
        System.out.println(result);
    }

    /**
     * 判断一个数字是否是回文
     * https://leetcode-cn.com/problems/palindrome-number/solution/hua-jie-suan-fa-9-hui-wen-shu-by-guanpengchn/
     */
    public boolean test(int x){
        if(x<0)return false;
        int cur = 0;
        int num = x;
        //下面这个循环是在反转数字,比如123会转成321
        while (num !=0){
            cur = cur * 10 + num % 10;
            num = num / 10;
        }
        return x == cur;
    }
}
