package string;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 括号字符串的有效性
 * str="()"返回true;str="(())"返回true
 * str="())"返回false;str="()("返回false
 *
 */
public class IsValid {
    public boolean isValid(String str){
        if(str == null || str.equals("")){
            return false;
        }
        char[] chars = str.toCharArray();
        int status = 0;
        for (int i=0;i<chars.length;i++){
            //str里只能有')','('两种符号
            if(chars[i] != ')' && chars[i] != '('){
                return false;
            }
            //下面两个if是在判断'(',')'是否一样多，注意两个if里的判断的字符不能反，不然")("判断不出来
            if(chars[i] == ')' && --status < 0){//这个if可以保证以'('开头
                return false;
            }
            if(chars[i] == '('){
                status++;
            }
        }
        return status == 0;
    }

    /**
     * 有可能是{}[]()
     * 比如{()}返回true
     * 这个也能判断(((())))一种类型的
     * @param str
     * @return
     */
    public static boolean isValid2(String str){
        Stack<Character> s = new Stack<>();
        char[] arr = str.toCharArray();
        int count = 0;
        for(int i=0;i< arr.length;i++){
            if(arr[i]=='('){
                s.push(')');
                count++;
            }else if(arr[i]=='{'){
                s.push('}');
                count++;
            }else if(arr[i]=='['){
                s.push(']');
                count++;
            }else if(s.isEmpty() || arr[i] != s.pop()){
                return false;
            }else{
                count--;
            }
        }
        return count==0;
    }

    public static void main(String[] args) {
        String test = "())";
        boolean valid2 = isValid2(test);
        System.out.println(valid2);
        /*String[] test = {"a","b","c","b"};
        int k = 2;
        String[][] strings = topKstrings(test, k);
        System.out.println(Arrays.asList(strings).toString());*/
    }


    }
