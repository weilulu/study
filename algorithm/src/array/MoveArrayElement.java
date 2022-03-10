package array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 有 n 个整数，使其前面各数顺序向后移 m 个位置，最后 m 个数变成最前面的 m 个数
 * 思路：合理运用%运算，原数组索引%len=原数组的索引——>（原数组索引+移动位数）%len=新数组索引
 */
public class MoveArrayElement {

    //这个方法使用了新的数组
    public static void main(String[] args) {
        int[] arr = {3,4,2,7,1,6};
        Random random = new Random();
        int len = random.nextInt(arr.length);
        int[] newArr = new int[arr.length];
        for(int i=0;i<newArr.length;i++){
            newArr[(i+len)%arr.length] = arr[i];
        }
        System.out.println(Arrays.toString(newArr));
    }

    /**
     * 【不使用新的数组】，将数组分成两部分，分别进行逆序，然后整体再逆序
     * 比如：{1, 2, 3, 4, 5, 6, 7, 8}，要求向左移3位，结果为{ 4, 5, 6, 7, 8,1,2,3}
     * 我们可以将数组分成A{1,2,3}与B{4,5,6,7,8}这两部分，分别将A与B进行逆序，然后再整体逆序
     *
     * 只申请了一个临时变量的空间！！！！
     */
    @Test
    public  void moveElementInArr(){
        int[] arr = {3,4,2,7,1,6};//要求左移3位
        reverseArr(arr,0,2);//反转前部分
        System.out.println(Arrays.toString(arr));

        reverseArr(arr,arr.length-3,arr.length-1);//反转后部分
        System.out.println(Arrays.toString(arr));

        reverseArr(arr,0,arr.length-1);//整体反转
        System.out.println(Arrays.toString(arr));
    }

    //反转指定开始与结束索引间的元素
    public  int[] reverseArr(int[] arr,int startIndex,int endIndex){
        if(arr == null){
            return new int[0];
        }
        for(;startIndex<endIndex;startIndex++,endIndex--){
            int tmp = arr[endIndex];
            arr[endIndex] = arr[startIndex];
            arr[startIndex] = tmp;
        }
        return arr;
    }
}
