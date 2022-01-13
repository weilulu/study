package array;

/**
 * 最大矩阵的最大累加和问题，和MaxArraySum类似
 * 时间复杂度为N的3尺方
 */
public class MaxMatrixSum {
    public int maxSum(int[][] m){
        if(m == null){
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        int[] s = null;//累加数组
        for (int i=0; i!= m.length;i++){//开始从矩阵的第一行，然后第二行。。。
            s = new int[m[0].length];
            //下面两个循环会生成一个数组，然后求数组的子数组最大累加和
            for (int j=i;j != m.length;j++){
                cur = 0;
                for (int k=0;k!= s.length;k++){
                    s[k] += m[j][k];//累加矩阵的每一列，生成一个一维数组，然后求这个一维数组最大加子数组
                    cur += s[k];
                    max = Math.max(max,cur);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }
}
