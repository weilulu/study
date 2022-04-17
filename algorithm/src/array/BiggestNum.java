package array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一组非负整数 nums，重新排列它们每个数字的顺序（每个数字不可拆分）使之组成一个最大的整数。
 *
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * 示例 1：
 * 输入：nums = [10,2]
 * 输出：“210”
 * 示例 2：
 * 输入：nums = [3,30,34,5,9]
 * 输出：“9534330”
 * 示例 3：
 */
public class BiggestNum {
    //自定义一个比较器
    static class StringCompare implements Comparator<String> {

        @Override
        public int compare(String a,String b) {
            String num1 = a+b;
            String num2 = b+a;
            return num2.compareTo(num1);
        }
    }
    public static String test(int[] arr){
        String[] a = new String[arr.length];
        for(int i=0;i<arr.length;i++){
            a[i] = String.valueOf(arr[i]);
        }
        //Arrays.sort(a,new StringCompare());//排序
        Arrays.sort(a,(x,y)-> (y+x).compareTo(x+y));
        String test = "";
        for(int i=0;i<a.length;i++){
            test = test+a[i];
        }
        return test;
    }

    public static void main(String[] args) {
        int[] arr = {3,30,34,5,9};
        String test = BiggestNum.test(arr);
        System.out.println(test);
    }
}
