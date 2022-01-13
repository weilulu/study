package dynamic_plan;

import org.checkerframework.checker.units.qual.C;

/**
 * 给定整数N，返回斐波那契数列的第N项
 * 1,1,2,3,5,8,13...
 * 除了第1，2项外，第N项有F(N)=F(N-1)+F(N-2)
 */
public class C1 {
    /**
     * 时间复杂度：2的N尺方
     * @param n
     * @return
     */
    public int m1(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        return m1(n-1)+m1(n-2);
    }

    /**
     * 时间复杂度：O(n)
     * @param n
     * @return
     *
     *
     */
    public int m2(int n){
        if(n < 1){
            return 0;
        }
        if(n == 1 || n == 2){
            return 1;
        }
        int res = 1;//最开始为第2项，然后顺移
        int pre = 1;//最开始为第1项，然后顺移
        int tmp = 0;//临时保存第2项的值
        for(int i=3;i<=n;i++){//从第3项开始
            tmp = res;
            res = res+pre;
            pre = tmp;
        }
        return res;
    }

    /**
     * 非递归爬楼梯一样
     * https://blog.csdn.net/a740169405/article/details/50679759
     * @param n
     * @return
     */
    public int m3(int n){
        int n1 = 1;
        int n2 = 2;
        for(int i=3;i<=n;i++){
            int tmp = n2;
            n2 = n1 + n2;
            n1 = tmp;
        }
        return n2;
    }

    public static void main(String[] args) {
        C1 c1 = new C1();
        int i = c1.m2(5);
        System.out.println(i);
    }
}
