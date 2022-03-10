package array;

/**
 * 删除有序数组里的重复元素，返回新数据长度，如
 * [1,2,2,3]去重后为[1,2,3]
 * 返回3
 */
public class RemoveDuplicates {
    public int test(int[] arr){
        if(arr == null || arr.length == 0){
            return 0;
        }
        int p = 0;
        int q = 1;
        while (q < arr.length){
            if(arr[p] != arr[q]){
                arr[p+1] = arr[q];
                p++;
            }else{
                q++;
            }
        }
        return p+1;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,2,3};
        RemoveDuplicates test = new RemoveDuplicates();
        int result = test.test(arr);
        System.out.println(result);
    }
}
