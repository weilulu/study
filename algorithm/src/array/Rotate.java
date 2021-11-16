package array;

/**
 * 将正文形矩阵旋转90度
 *之前：
 * 1  2  3  4
 * 5  6  7  8
 * 9 10 11 12
 * 13 14 15 16
 * 之后：
 * 13  9   5  1
 * 14  10  6  2
 * 15  11  7  3
 * 16  12  8  4
 *  上面是原始的，下面是旋转90度后的
 *
 *  分析：也是从外层再到内层的思路，从左向右且从外层开始遍历数组，然后将第一行第一列的值赋给第一行第三列，第一行第二列的值赋给第二行第三列
 */
public class Rotate {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        print(matrix);
        int tR = 0;
        int tC = 0;
        int dR = matrix.length-1;
        int dC = matrix[0].length-1;
        while (tR < dR){
            rotate(matrix,tR++,tC++,dR--,dC--);
        }
        System.out.println(" ");
        print(matrix);

    }
    public static void rotate(int[][] matrix,int tR,int tC,int dR,int dC){
        int times = dC - tC;//层
        int tmp = 0;//临时变量
        for(int i=0;i != times;i++){
            tmp = matrix[tR][tC + i];
            matrix[tR][tC + i] = matrix[dR - i][tC];
            matrix[dR - i][tC] = matrix[dR][dC - i];
            matrix[dR][dC - i] = matrix[tR + i][dC];
            matrix[tR + i][dC] = tmp;
        }
    }
    public static void print(int[][] matrix){
        int row = matrix.length;
        int column = matrix[0].length;
        for(int i=0;i<=row-1;i++){
            for(int j=0;j<=column-1;j++){
                System.out.print(matrix[i][j] + " ");
            }
        }
    }
}
