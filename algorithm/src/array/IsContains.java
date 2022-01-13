package array;

/**
 * 在行列都排好序的矩阵中找指定的数
 * 给定一个NXM的整型矩阵matrix和一个整数k，matrix的每一行和每一列都是排好序的，判断k虽否在matrix中
 * 0  1  2  5
 * 2  3  4  7
 * 4  4  4  8
 * 5  7  7  9
 * k:7  true
 * k:6  false
 *
 * 如果将矩阵进行遍历，时间复杂度为MXN，但用下面的方法，可以做到M+N
 *
 * 1，先初始化一个坐标为右上角(0,M)
 * 2，如果matrix[row][col]大于要找的数，则这一列中从matrix[row][col]往下都比要找的数大，所以没必要在此列上寻找，所以col--移到左一列。
 * 3，如果matrix[row][col]小于要找的数，则这一行中从matrix[row][col]往左都比要找的数小，所以没必要在此行上寻找，所以row++移到下一行。
 */
public class IsContains {
    public boolean isContains(int[][] matrix,int k){
        int row = 0;
        int col = matrix[0].length - 1;
        //先从右上角开始找
        while (row < matrix.length && col > -1){
            System.out.println("col:"+col+",row:"+row);
            if(matrix[row][col] == k){
                return true;
            }else if(matrix[row][col] > k){
                col--;
            }else {
                row++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0,1,2,5},{2,3,4,7},{4,4,4,8},{5,7,7,9}};
        IsContains contains = new IsContains();
        boolean contains1 = contains.isContains(matrix, 5);
        System.out.println(contains1);
    }
}
