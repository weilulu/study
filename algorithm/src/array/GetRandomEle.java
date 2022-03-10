package array;

import java.util.Random;

/**
 * 随机获取数组里的值
 * 思路：根据数组长度生成长度以内的随机数作为数组索引，
 * 生成一个输出后，与数组最后一个元素交换，且随机种子也进行递减，这样就不会重复生成
 *
 */
public class GetRandomEle {
    public static void main(String[] args) {
        int[] arr = {3,4,5,6};
        Random random = new Random();
        for(int i=0;i<arr.length;i++){
            int element = random.nextInt(arr.length-i);
            System.out.println(arr[element]);
            int tmp = arr[element];
            //获取到随机数后将其放在数组最后，这样，下次的随机数就只会在剩下的数里选。交换的位置也要往前顺移
            arr[element] = arr[arr.length-1-i];
            arr[arr.length-1-i] = tmp;
        }
    }
}
