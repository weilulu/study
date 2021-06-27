package sort;

/**
 * 插入排序：
 * 假设一个小范围内的元素是有序的（最开始只有一个），来一个新元素时会在之前已有序的元素里找到合适的位置插入
 * 可以想象是在玩扑克一样
 * 时间复杂度：平均O(n平方)、最好O(n)、最坏O(n的平方)
 *
 * 这个和冒泡有一点点类似，不同之处在于内层循环中的比较条件、交换数据有点差异
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {2,4,3,6,7,9,2,1};
        int n = arr.length;
        for(int i=0;i<n;i++){
            for(int j=i;j>0 && arr[j] < arr[j-1];j--){
                Utils.change(arr,j,j-1);
            }
        }
        Utils.sout(arr);
    }
}
