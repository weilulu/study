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
 *  分析：先使用临时变量保存(0,0)的值，然后让13占据1的位置，让1占据4的位置，4占据16的位置，16占据13的位置。类似，2，3，8，12，15，14，9，5也做同样处理，这样最外层就处理完了。
 *  最里层同理
 */
public class Rotate {
    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        print(matrix);
        int tR = 0;
        int tC = 0;
        int dR = matrix.length-1;
        int dC = matrix[0].length-1;
        while (tR < dR){//从外层往内层
            rotate(matrix,tR++,tC++,dR--,dC--);
        }
        System.out.println(" ");
        print(matrix);

    }
    public static void rotate(int[][] matrix,int tR,int tC,int dR,int dC){
        int times = dC - tC;//层
        int tmp = 0;//临时变量
        for(int i=0;i != times;i++){//处理每层
            tmp = matrix[tR][tC + i];//最开始这是(0,0)，也就是1的位置，下一次循环是2的位置
            matrix[tR][tC + i] = matrix[dR - i][tC];//(3,0)
            matrix[dR - i][tC] = matrix[dR][dC - i];//(3,3)
            matrix[dR][dC - i] = matrix[tR + i][dC];//(0,3)
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
