package sort;

/**
 * 快排思想：
 * 取待排数组里任一元素为枢轴元素
 * 将所有比枢轴元素小的放在其左边
 * 将所有比枢轴元素大的放在其右边（这里右边位置其实就是上一步挪到左边元素的位置）
 *
 * 左右两个子数组再按照上面的步骤，直到每个子数组只剩下一个元素
 * 平均：O(nlogn),最坏为O(n的平方)
 */
public class QuickSort {
    public static void sort(int[] arr){
        quickSort(arr,0,arr.length-1);
    }
    public static void quickSort(int[] arr,int left,int right){
        int middle;
        if(left < right){
            middle = partion(arr,left,right);//找枢轴元素
            quickSort(arr,left,middle-1);//左边
            quickSort(arr,middle+1,right);//右边
        }
    }
    public static int partion(int[] arr,int left,int right){
        int pivot = arr[left];//最开始先拿最左边元素当作枢轴元素
        while (left < right){
            //从右边开始找比枢轴元素小的，如果没有right左移
            while (left < right && arr[right] >= pivot){
                right--;
            }
            //找到了则移到左边
            arr[left] = arr[right];
            //从左边寻找比枢轴大的元素，如果没有left右移
            while (left < right && arr[left] <= pivot){
                left++;
            }
            //找到则移到右边
            arr[right] = arr[left];
        }
        //走到这里，说明left==right了
        arr[left] = pivot;
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {39 , 28 , 55 , 87 , 66 , 3 ,17 ,39};
        QuickSort.sort(arr);
        for (int a:arr){
            System.out.print(a+" ");
        }
    }
}
