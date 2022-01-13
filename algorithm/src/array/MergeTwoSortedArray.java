package array;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * 假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6], n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 * 思路：采用双指针的方式，从后向前比
 *
 * 时间复杂度是O(n+m)，空间复杂度为O(1)
 */
public class MergeTwoSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n){
        int p1 = m - 1;
        int p2 = n - 1;
        int p = m + n - 1;
        while (p1 >= 0 && p2 >= 0){
            nums1[p--] = nums1[p1] < nums2[p2] ? nums2[p2--] : nums1[p1--];
        }
        System.arraycopy(nums2,0,nums1,0,p2 + 1);
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0};
        int[] nums2 = {2,5,6};
        merge(nums1,3,nums2,3);
        for (int i : nums1){
            System.out.print(i+" ");
        }
    }
}
