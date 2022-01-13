package dynamic_plan;

/**
 * 汉诺塔问题
 * 给定一个整数n,代表汉诺塔游戏中从小到大放置的n个圆盘，假设开始时所有的圆盘都放在左边的柱子上，想按照汉诺塔游戏的要求把所有的圆盘
 * 移到右边柱子上。实现函数打印最优轨迹。
 * 比如:
 * n=1时，打印：
 * move from left to right
 * n=2时，打印：
 * move from left to mid
 * move from left to right
 * move from mid to right
 */
public class C3 {
    public void hanoi(int n){
        if(n > 0){
            func(n,"left","mid","right");
        }
    }
    public void func(int n,String from,String mid,String to){
        if(n==1){
            System.out.println("move from "+from+" to "+to);
        }else {
            func(n-1,from,to,mid);
            func(1,from,mid,to);
            func(n-1,mid,from,to);
        }
    }

    public static void main(String[] args) {
        C3 c3 = new C3();
        c3.hanoi(3);
    }
}
