package search;

public class BSearch {
    public int besearch(int[] array,int n,int value){
        int low = 0;
        int high = n-1;
        while (low <= high){
            //有溢出风险，可写成 low+(high-low)/2，或换成low+((high-low)>>1)
            int mid = low+(high-low) / 2;
            if(array[mid] == value){
                return mid;
            }else if(array[mid] < value){
                low = mid + 1;
            }else{
                high = mid -1;
            }
        }
        return -1;
    }
}
