package array;

/**
 * 给定一个无序数组，找出其中最小的k个数
 * 这里使用大顶堆来实现:
 * 维护一个有k个数的大顶堆（从数组里取数），然后遍历整个数组，遍历过程中看当前元素是否比堆顶元素小，如果是，则将使用此元素将堆顶元素替换，
 * 然后调整堆使其仍是一个大顶堆，如果不是则继续遍历数组。遍历结束后堆中的k个数就是数组中最小的k个数
 */
public class GetMinByHeap {
    public static void main(String[] args) {
        int[] arr = {4,3,7,5,1,8,9,3,5};
        GetMinByHeap heap = new GetMinByHeap();
        int[] minkByHeap = heap.getMinkByHeap(arr, 5);
        for(int i=0;i<=minkByHeap.length-1;i++){
            System.out.print(minkByHeap[i]+" ");
        }
    }

    public int[] getMinkByHeap(int[] arr,int k){
        if(k < 1 || k > arr.length){
            return arr;
        }
        int[] kHeap = new int[k];
        //建堆
        for(int i=0;i != k;i++){
            heapInsert(kHeap,arr[i],i);
        }
        //调整堆
        for(int i=k;i != arr.length;i++){
            if(arr[i] < kHeap[0]){
                kHeap[0] = arr[i];//替换堆顶元素
                heapify(kHeap,0,k);//调整堆以其仍是大顶堆
            }
        }
        return kHeap;
    }

    /**
     * 建堆,大顶堆：每个节点的值都大于或等于其左右孩子节点的值
     * @param arr
     * @param value
     * @param index
     */
    public void heapInsert(int[] arr,int value,int index){
        arr[index] = value;
        while (index != 0){
            int parent = (index -1) / 2;
            //保证根节点最大
            if(arr[parent] < arr[index]){
                swap(arr,parent,index);
                index = parent;
            }else{
                break;
            }
        }
    }

    /**
     * 调整堆
     * @param arr 待调整的大顶堆数组
     * @param index
     * @param heapSize
     * 每次循环都是使用一个节点和它的左右子节点进行比较，保证父节点大于或等左右子节点
     */
    public void heapify(int[] arr,int index,int heapSize){
        int left = index * 2 +1;
        int right = index * 2 + 2;
        int largest = index;
        while (left < heapSize){
            if(arr[left] > arr[index]){
                largest = left;
            }
            if (right < heapSize && arr[right] > arr[largest]) {
                largest = right;
            }
            if(largest != index){
                swap(arr,largest,index);
            }else {
                break;
            }
            index = largest;
            left = index * 2 +1;
            right = index * 2 + 2;
        }

    }
    public void swap(int[] arr,int index1,int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

}
