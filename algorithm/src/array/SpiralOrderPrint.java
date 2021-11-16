package array;


/**
 * 给定一个整型矩阵，需要按转圈的方式打印
 * 1  2  3  4
 * 5  6  7  8
 * 9 10 11 12
 * 输出结果为1 2 3 4 8 12 11 10 9 5 6 7
 * 要求空间复杂度为O（1）
 */
public class SpiralOrderPrint {
    public static void main(String[] args) {
//        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        SpiralOrderPrint print = new SpiralOrderPrint();
        print.spiralOrderPrint(matrix);
    }
    public void spiralOrderPrint(int[][] matrix){
        //设置初始值，(tR,tC)为最左下角坐标，也就是（0，0）；(dR,dC)为最右下角坐，也就是（3，3）
        int tR = 0;
        int tC = 0;
        int dR = matrix.length -1;//行数
        int dC = matrix[0].length -1;//列数
        while(tR <= dR && tC <= dC){
            print(matrix,tR++,tC++,dR--,dC--);
        }
    }
    private void print(int[][] matrix,int tR,int tC,int dR,int dC){
        if(tR == dR){//子矩阵只有一行时（执行到最里层时只有一行）
            for(int i = tC;i <= dC;i++){
                System.out.print(matrix[tR][i] + " ");
            }
        }else if(tC == dC){//子矩阵只有一列时（执行到最里层时只有一列）
            for(int i= tR;i < dR;i++){
                System.out.print(matrix[i][tC] + " ");
            }
        }else {//一般情况
            int curC = tC;
            int curR = tR;
            while(curC != dC){
                System.out.print(matrix[tR][curC] + " ");
                curC++;
            }
            while(curR != dR){
                System.out.print(matrix[curR][dC] + " ");
                curR++;
            }
            while(curC != tC){
                System.out.print(matrix[dR][curC]+" ");
                curC--;
            }
            while (curR != tR){
                System.out.print(matrix[curR][tC]+" ");
                curR--;
            }
        }
    }

}
