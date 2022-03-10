package array;

public class Test {
    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        double result = Test.findMedianSortedArrays(nums1, nums2);
        System.out.println(result);
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            if (nums2.length % 2 == 0) {
                return (nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]) / 2.0;
            } else {
                return nums2[nums2.length / 2];
            }
        }
        if (nums2.length == 0) {
            if (nums1.length % 2 == 0) {
                return (nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]) / 2.0;
            } else {
                return nums1[nums1.length / 2];
            }
        }

        int p1 = nums1.length-1;
        int p2 = nums2.length-1;
        int p = nums1.length + nums2.length -1;
        int[] nums = new int[p+1];
        while(p1>=0 && p2>=0){
            if(nums1[p1] < nums2[p2]){
                nums[p--]=nums2[p2--];
            }else{
                nums[p--]=nums1[p1--];
            }
        }
        if(p2 > p1){
            System.arraycopy(nums2,0,nums,0,p2+1);
        }else{
            System.arraycopy(nums1,0,nums,0,p1+1);
        }

        if(nums.length%2==0){
            return (nums[nums.length/2-1]+nums[nums.length/2])/2.0;
        }else{
            return  nums[nums.length/2];
        }
    }
}
