package array;

/**
 * 1，定义上，下两个坐标，同时进行移动，上坐标往右，下坐标往下。
 *    上坐标移到最右一列时，沿着最右一列向下移动；上坐标移到最下面一行时，沿着最下面一行向右移到。
 * 2，在上，下坐标移动过程中的连线就是需要打印的坐标。
 * 3，打印的时候，左下向右上，右上向左下交替进行
 */
public class ZigZagPrint {
    public void printZigZag(int[][] matrix){
        int tR = 0,tC = 0;//(tR,tC)上坐标
        int dR = 0,dC = 0;//(dR,dC)下坐标
        int endR = matrix.length - 1;//(endR,endC)最右下角坐标
        int endC = matrix[0].length - 1;
        boolean fromUp = false;//控制打印方向，左下向右上，右上向左下，每循环一次进行交换
        while (tR != endR + 1){
            printLevel(matrix,tR,tC,dR,dC,fromUp);
            //下面两行，是上坐标从左往右移动，遇到最后一列则沿着最后一列向下移动
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            //下面两行，是下坐标从上往下移动，遇到最后一行则沿着最后一行向右移动
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp = !fromUp;
        }
        System.out.println();
    }
    public void printLevel(int[][] matrix,int tR,int tC,int dR,int dC,boolean fromUp){
        //下面matrix[xx++][xx--],matrix[xx--][xx++]都是上，下坐标连线上的坐标
        if(fromUp){
            while (tR != dR + 1){
                System.out.println(matrix[tR++][tC--]+" ");
            }
        }else {
            while (dR != tR - 1){
                System.out.println(matrix[dR--][dC++]+ " " );
            }
        }
    }
}
