package dynamic_plan;

/**
 * 矩阵的最小路径和
 * 给定一个矩阵m,从左上角开始每次只能向右或下走，最后到达右下角的位置，路径上所有的数字累加起来就是路径和，
 * 返回所有的路径中最小的路径和。如
 * 1 3 5 9
 * 8 1 3 4
 * 5 0 6 1
 * 8 8 4 0
 * 路径1,3,1,0,6,1,0是所有路径和最小的，所以返回12
 *
 */
public class C2 {

    /**
     * 矩阵中一共有MXN个位置，每个位置都计算一次从(0,0)位置达到自己的最小路径和，
     * 计算的时候只是比较上边位置的最小路径和与左边位置的最小路径和哪个更小，所以
     * 时间复杂度为O(MXN),DP矩阵的大小为MXN,所以空间复杂度为O(MXN)。
     * 步骤：
     * 1，先生成第一行
     * 2，再生成第一列
     * 3，再生成其它行，列
     * 4，最右下角就是要找到值
     * @param m
     * @return
     */
    public int minPathSum1(int[][] m){
        if(m==null || m.length==0 || m[0] == null || m[0].length == 0){
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        /**
         * 生成第一行路径和数组，也就是：
         * 1,4,9,18
         */
        for(int i=1;i<row;i++){
            dp[i][0] = dp[i-1][0] + m[i][0];
        }
        /**
         * 生成第一列路径和数组，也就是：
         * 1,9,14,22
         */
        for(int j=1;j<col;j++){
            dp[0][j] = dp[0][j-1] + m[0][j];
        }
        for(int i=1;i<row;i++){
            for (int j=1;j<col;j++){
                //比较左移时与上移时的大小，取小的
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + m[i][j];
            }
        }
        return dp[row-1][col-1];
    }

    /**
     * 时间复杂度为O(MXN)，空间复杂度为O(min{M,N})
     * 先生成一个长度为min{M,N}的数组arr，然后不断滚动更新这个数组，让arr变成dp(方法1里的矩阵)每一行的值，
     * 最终变成dp矩阵最后一行的值。
     * 注意，生成数组后滚动的方向可以是由上到下（以行的形式，行等于列或行大于列），也可能是由左到右（以列的形式，行小于列）
     * @param m
     * @return
     */
    public int minPathSum2(int[][] m){
        if(m==null || m.length==0 || m[0] == null || m[0].length == 0){
            return 0;
        }
        int more = Math.max(m.length,m[0].length);//行列较大者
        int less = Math.min(m.length,m[0].length);//行列较小者
        boolean rowmore = more == m.length;//行列是否相等
        int[] arr = new int[less];
        //第一步，先由原矩阵第一行生成arr数组（路径和的数组）
        arr[0] = m[0][0];
        for(int i=1;i<less;i++){
            arr[i] = arr[i-1] + (rowmore ? m[0][i] : m[i][0]);
        }
        //第二步，滚动更新arr数组
        for (int i=1;i<more;i++){
            //由己生成数组第一个元素与矩阵生成的元素覆盖数组第一个元素
            arr[0] = arr[0] + (rowmore ? m[i][0] : m[0][i]);
            //数组中后续元素由数组前一元素与第一步生成的数组里的元素比较后，再与矩阵里的元素生成
            for (int j=1;j<less;j++){
                //arr[j-1]是内层循环上一次循环己生成的值;arr[j]是外层循环上一次生成的值
                arr[j] = Math.min(arr[j-1],arr[j]) + (rowmore ? m[i][j] : m[j][i]);
            }
        }
        return arr[less-1];
    }
}
