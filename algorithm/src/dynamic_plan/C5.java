package dynamic_plan;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同，子数组是连续的。
 * 比如[2,2,3,4,6]的子数组有[2,3]，[2,3,4]，[3,4]等等，但是[2,3,6]不是子数组,
 * [2,3,4,6]是最长的，长度为4
 *
 * 要求：空间复杂度 O(n)，时间复杂度 O(nlogn)
 *
 * 思路:就是使用左右两个指针对数组进行分段。如果出现重复元素，那左指针指向重复元素第一次出现的位置，右
 * 指针指向第二次出现的位置，并更新保存子段中最长值。依此类推直到数组遍历完，最后的最长值就是结果。
 */
public class C5 {

    /**
     * 数组中最长无重复子数组
     * @param arr
     * @return
     */
    public int maxLength (int[] arr) {
        if(arr.length < 2){
            return arr.length;
        }
        //记录元素值与元素位置
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;//子序列最大值
        //左指针，指向出现重复元素时上一次元素出现位置，
        //比如元素a在位置0，3，5都出现过，则a最终指向3
        int left = -1;
        for(int right = 0; right < arr.length; right++){
            //遇到重复数字，用left记录重复数字上一次出现的位置
            if(map.containsKey(arr[right])){
                //left = Math.max(left, map.get(arr[right]));
                left = map.get(arr[right]);
            }
            //记录元素与元素位置
            map.put(arr[right], right);
            //根据左右指针与max计算子序列最长长度
            max = Math.max(max, right-left);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,5,3,4,7,9,8,3};
        C5 c5 = new C5();
        int i = c5.maxLength(arr);
        System.out.println(i);
    }
}
