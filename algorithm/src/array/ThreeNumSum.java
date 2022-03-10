package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且
 * 不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 *
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 *
 * 链接：https://leetcode-cn.com/problems/3sum
 *
 * 思路：排序+双指针
 */
public class ThreeNumSum {
    public List<List<Integer>> threeSum(int[] nums){
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        for (int i=0;i<len;i++){
            if(nums[i] > 0)return lists;//nums[i]是三个数里最小的值，如果大于0则三个数相加肯定大于0
            if(i>0 && nums[i] == nums[i-1])continue;//避免重复
            //双指针
            int L = i+1;
            int R = len-1;
            while (L < R){
                int tmp = nums[i] + nums[L] + nums[R];
                if(tmp == 0){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    while (L < R && nums[L] == nums[L+1])L++;//去重
                    while (L < R && nums[R] == nums[R-1])R--;
                    L++;
                    R--;
                }else if(tmp < 0){//右移
                    L++;
                }else{//左移
                    R--;
                }
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        ThreeNumSum sum = new ThreeNumSum();
        List<List<Integer>> lists = sum.threeSum(nums);
        System.out.println(lists.toString());
    }
}
