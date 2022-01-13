package array;

/**
 * 子数组的最大累加和问题
 * 给定一个数组，返回子数组的最大累加和，数组里可能有负数的元素.
 * [1,-2,3,5,-2,6,-1,0],子数组为[3,5,-2,6]累加的和最大，最大为12，注意一定要是子数组。
 * 因为是子数组，所以不能遇到负数就抛弃掉(比如替换成0)
 */
public class MaxArraySum {


    public int maxSum(int[] arr){
        if(arr == null){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int sum = 0;
        //每计算一步的时候，都使用max变量将最大值进行记录，如果计算后总结果出现负数，则结果为0
        for (int i=0;i< arr.length-1;i++){
            sum += arr[i];
            max = Math.max(max,sum);
            sum = sum < 0 ? 0 : sum;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,-2,3,5,-2,6,-1,0};
        MaxArraySum sum = new MaxArraySum();
        int i = sum.maxSum(arr);
        System.out.println(i);
    }
}
