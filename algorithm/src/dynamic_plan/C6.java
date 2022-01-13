package dynamic_plan;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 数组中的最长连续序列
 * 给定无序数组arr,返回其中最长的连续序列的长度
 * 如：arr=[100,4,200,1,3,2],最长的连续序列为[1,2,3,4]，返回4
 * 时间复杂度为O(n),空间复杂度为：O(n)
 */
public class C6 {
    /**
     * 这个是左程云的方法，但merge方法里的开始两行不理解
     * @param arr
     * @return
     */
    public int longestConsecutive(int[] arr){
        if (arr == null || arr.length == 0){
            return 0;
        }
        int max =1;
        //key:遍历过的某个数，value：这个数所在的最长连续序列的长度
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<arr.length;i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i],1);//在没拼接之前，放进去的数对应的都是最短值1，随着这个数与前面的数(-1)或后面的数(+1)拼接后会形成最长值。
                if(map.containsKey(arr[i]-1)){
                    max = Math.max(max,merge(map,arr[i]-1,arr[i]));
                }
                if(map.containsKey(arr[i]+1)){
                    max = Math.max(max,merge(map,arr[i],arr[i]+1));
                }
            }
        }
        return max;
    }
    public int merge(HashMap<Integer,Integer> map,int less,int more){
        int left = less - map.get(less) + 1;//子序列的左边界元素
        int right = more + map.get(more) - 1;//子序列的右边界元素
        int len = right - left + 1;
        map.put(left,len);
        map.put(right,len);
        return len;
    }

    public static void main(String[] args) {
        C6 c6 = new C6();
        int[] arr = {100,4,200,1,3,2};
        int i = c6.m3(arr);
        System.out.println(i);
    }

    /**
     * 这个是网上的方法：https://blog.nowcoder.net/n/8c71b5ca85a64af39e9ca2601671a74e
     * @param nums
     * @return
     * {100,4,200,1,3,2}
     */
    public int m2(int[] nums) {
        //map中保存着子序列当前左侧元素，当前右侧元素，当前元素及子序列长度
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int x : nums) {
            if (!map.containsKey(x)) {
                int left = map.getOrDefault(x - 1, 0);//前一元素所在序列长度
                int right = map.getOrDefault(x + 1, 0);//后一元素所在序列长度
                int sum = left + right + 1;//前面元素所在的序列长度+后面元素所在序列长度+当前元素序列长度(当前元素是一个长度为1的子序列)
                map.put(x, sum);
                map.put(x - left, sum);//x-left 序列的左边界
                map.put(x + right, sum);//x+right 序列的右边界
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    /**
     * 1，先进行排序
     * 2，统计连续序列长度：遇到重复元素跳过；遇到不连续元素重置；遇到连续元素加一并更新max
     * @param arr
     * @return
     */
    public int m3(int[] arr) {
        Arrays.sort(arr);
        int length = 1;//每次遍历中子序列的长度，遍历过程中如果发现当前元素与已形成的子序列连续，则子序列长度加1；如果不连续则子序列长度置为1
        int max = 1;//记录所有子序列中最长的长度
        for (int i=1;i<arr.length;i++){
            if(arr[i] == arr[i-1]+1){//说明是连续的，将
                length = length + 1;
                max = Math.max(max,length);
            }else if(arr[i] == arr[i-1]){//遇到重复的元素，直接跳过
                continue;
            }else{//说明不连续，将子序列置为1
                length = 1;
            }
        }
        return max;
    }
}
