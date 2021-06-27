package sort;

/**
 * 冒泡排序
 * 时间复杂度：平均O(n平方)、最好O(n)、最坏O(n的平方)
 * 1,两两比较
 * 2,最小的冒到最前面
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {2,4,3,6,7,9,2,1};
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length-1; j >i; j--) {
                if (arr[j] < arr[j-1]) {
                    Utils.change(arr,j,j-1);
                }
            }
        }
        Utils.sout(arr);
    }
}
