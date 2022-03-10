package string;

import java.util.Stack;

/**
 * 最长括号数
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 * https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-powcai/
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s){
        if(s == null || s.length() == 0){
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();
                if(stack.isEmpty()){
                    stack.push(i);
                }else{
                    max = Math.max(max,i-stack.peek());
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LongestValidParentheses test = new LongestValidParentheses();
        int i = test.longestValidParentheses(")");
        System.out.println(i);
    }
}
