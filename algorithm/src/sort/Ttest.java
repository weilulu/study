package sort;

import java.util.ArrayList;

public class Ttest {

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        for(int i=(input.length-1)/2;i>=0;i--){
            adjustHeap(input,i,input.length-1);
        }
        for(int i=k;i>0;i--){
            int tmp = input[i];
            input[i] = input[0];
            input[0] = tmp;
            adjustHeap(input,0,i);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0;i<k;i++){
            list.add(input[i]);
        }
        return list;
    }

    public void adjustHeap(int[] input,int parent,int length){
        int tmp = input[parent];
        int left = 2*parent+1;
        while(left<length){
            int right = 2*parent+2;
            if(left<length && input[left] < input[right]){
                left = right;
            }
            if(input[parent] > input[left]){
                break;
            }
            input[parent] = input[left];
            parent=left;
            left = 2*parent+1;

        }
        input[parent] =tmp;
    }

    public static void main(String[] args) {
        int[] test = {4,5,1,6,2,7,3,8};
        int k =4;
        Ttest tt = new Ttest();
        ArrayList<Integer> integers = tt.GetLeastNumbers_Solution(test, k);
        System.out.println(integers.toString());
    }
}
