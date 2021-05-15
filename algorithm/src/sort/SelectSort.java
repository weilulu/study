package sort;

import static sort.Utils.change;
import static sort.Utils.sout;

/**
 * 插入排序：不断选择剩下元素中最小的
 * 在第一轮循环中，先将第一个元素设为最小，拿它和第二个元素比较，如果第二个元素比它小，将它们进行交换；
 * 第一轮比较完之后，再进行第二轮直到最后
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {2,4,3,6,7,9,2,1};
        for(int i=0;i<arr.length;i++){
            int min = i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            change(arr,i,min);
        }
        sout(arr);
    }

}
