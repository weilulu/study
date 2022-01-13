package array;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 有 n 个整数，使其前面各数顺序向后移 m 个位置，最后 m 个数变成最前面的 m 个数
 * 思路：合理运用%运算，原数组索引%len=原数组的索引——>（原数组索引+移动位数）%len=新数组索引
 */
public class MoveEle {
    static int[] arr = {3,4,2,7,1,6};
    public static void main(String[] args) {
        Random random = new Random();
        int len = random.nextInt(arr.length);
        int[] newArr = new int[arr.length];
        for(int i=0;i<newArr.length;i++){
            newArr[(i+len)%arr.length] = arr[i];
        }
        System.out.println(Arrays.toString(newArr));
    }

    @Test
    public void moveElementInArr(){
        int[] arr = {3,4,2,7,1,6};
        int k = 2;
        int slow = 0;
        int fast = k;
        for (int i=0;i<arr.length;i++){
            //int tmp = arr[(i+k)%arr.length];
            arr[(i+k)%arr.length] = arr[i];
        }
        System.out.println(Arrays.toString(arr));
    }
}
