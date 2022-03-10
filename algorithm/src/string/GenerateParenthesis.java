package string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 生成括号
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 * 其中 1 <= n <= 8
 *
 * 参考：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
 */
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n){
        List<String> list = new ArrayList<>();
        if(n==0){
            return list;
        }
        dfs("",n,n,list);
        return list;
    }
    public void dfs(String curString,int left,int right,List<String> list){
        //递归终止条件
        if(left == 0 && right == 0){
            list.add(curString);
            return;
        }
        if(left > right){
            return;
        }
        if(left > 0){
            dfs(curString+"(",left-1,right,list);
        }
        if(right > 0){
            dfs(curString+")",left,right-1,list);
        }
    }

    public static void main(String[] args) {
        int n = 2;
        GenerateParenthesis test = new GenerateParenthesis();
        List<String> strings = test.generateParenthesis(n);
        System.out.println(strings.toString());
    }

}
