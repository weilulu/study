package sort;

/**
 * https://www.cnblogs.com/luomeng/p/10618709.html
 * 1,构建大顶堆，将待排序列构造一个大顶堆（见下面）
 * 2，将堆顶元素与堆尾元素交换，并断开堆尾元素
 * 3，重新构建堆
 * 4，重复2，3，直到堆里只剩一个元素
 *
 * https://blog.csdn.net/jump_into_zehe/article/details/119720455
 * 设每个节点下标为i,则左孩子的下标为2*i+1,右孩子的下标为2*i+2
 * 如何构建一个大顶堆？如果以某个节点i为树，它的左右子树都是一个大根堆，那我们只要将arr[i] 与 max(arr[2*i+1], arr[2*i+2])进行比较，
 * 如果arr[i] < max(arr[2*i+1], arr[i*2+2])，就将两者交换，然后继续往下交换，直到arr[i] > max(arr[i*2+1], arr[i*2+2])，或者i*2+1>length，
 * 就可以完成大根堆的构造。通过动态规划的思想，从最后一个非叶子结点一直往上推，就能保证每一个结点的左右子树都为大根堆
 *
 * {3,2,5,4,1,12}对应的树（构造大顶堆前）：
 *         3
 *       /    \
 *      2      5
 *    /   \   /
 *  4      1  12
 *
 *  构造之后：
 *         12
 *       /    \
 *      5      4
 *    /   \   /
 *  3      2  1
 *
 *平均：O(nlogn),最坏为O(n的平方)
 */
public class HeapSort {

    private static void heapSort(int[] arr) {
        //创建堆
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            //从最后一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }

        //调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            //将堆顶元素与末尾元素进行交换
            int temp = arr[i];//末尾
            arr[i] = arr[0];
            arr[0] = temp;

            //重新对堆进行调整
            adjustHeap(arr, 0, i);
        }
    }

    private static void adjustHeap(int[] arr, int parent, int length) {
        //将temp作为父节点
        int temp = arr[parent];
        //左孩子
        int lChild = 2 * parent + 1;

        while (lChild < length) {
            //右孩子
            int rChild = 2 * parent + 2;
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (rChild < length && arr[lChild] < arr[rChild]) {
                //lChild++;
                lChild = rChild;//交换左右孩子节点，保证左孩子节点大于右孩子节点
            }
            // 如果父结点的值已经大于左孩子结点的值，则直接结束
            if (arr[parent] >= arr[lChild]) {
                break;
            }
            // 把孩子结点的值赋给父结点，保证父节点最大
            arr[parent] = arr[lChild];
            //选取孩子结点的左孩子结点,继续向下筛选
            parent = lChild;
            lChild = 2 * parent + 1;
        }
        arr[parent] = temp;
    }
    public static void main(String[] args) {
        //int[] a = {4,3,7,5,1,8,9,3,5};
        int[] a = {3,2,5,4,1,12};
        HeapSort.heapSort(a);
        for (int i=0;i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
    }
}
