package array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求最短路径
 * 用一个矩阵matrix表示一个网络，1代表有路，0代表无路，每一个位置只要不越界，都有上下左右4个方向，求从最左上角到最右下角的最短通路值。
 * 如：
 * 1 0 1 1 1
 * 1 0 1 0 1
 * 1 1 1 0 1
 * 0 0 0 0 1
 * 通路只有一条，由12个1组成，所以返回12
 * 1，从原点（0，0）出发，走的路径坐标放入到两个队列里。初始化时放入原点，不停的取出向四个方向前进。
 * 2，从原点(0,0)出发，每个点都可以向上，下，左，右四个方向走，走的过程中进行两个判断：
 *    一是走的坐标对应的值必须为1，二是之前走的不能再走
 * 3，使用一个二维数组，将走过的路径和进行累加
 */
public class MinPathValue {
    public int minPathValue(int[][] m){
        //矩阵第一个元素得为1；矩阵右下角元素得为1
        if(m==null || m.length==0 || m[0].length==0 || m[0][0]!=1
            || m[m.length-1][m[0].length-1] !=1){
            return 0;
        }
        int res = 0;
        //走过的路径和
        int[][] map = new int[m.length][m[0].length];
        map[0][0] = 1;
        //在矩阵中走过的路径坐标
        Queue<Integer> rowQ = new LinkedList<>();
        Queue<Integer> colQ = new LinkedList<>();
        rowQ.add(0);//将矩阵第一个坐标分别入队列
        colQ.add(0);
        //矩阵坐标
        int row = 0;
        int col = 0;
        while (!rowQ.isEmpty()){
            row = rowQ.poll();
            col = colQ.poll();
            //说明走到了右下角的终点，返回
            if(row == m.length-1 && col == m[0].length-1){
                return map[row][col];
            }
            walk(map[row][col],row-1,col,m,map,rowQ,colQ);//上
            walk(map[row][col],row+1,col,m,map,rowQ,colQ);//下
            walk(map[row][col],row,col-1,m,map,rowQ,colQ);//左
            walk(map[row][col],row,col+1,m,map,rowQ,colQ);//右
        }
        return res;
    }
    public void walk(int pre,int toR,int toC,int[][] m,int[][] map,
                        Queue<Integer> rowQ,Queue<Integer> colQ){
        if(toR<0 || toR==m.length ||
            toC<0 || toC==m[0].length ||
            m[toR][toC] != 1 || map[toR][toC] != 0){//map[toR][toC]!=0 说明是之前走过的路不能走
            return;
        }
        //记录走过的路径和进行累加
        map[toR][toC] = pre + 1;
        //坐标分别入队列
        rowQ.add(toR);
        colQ.add(toC);
    }
}
