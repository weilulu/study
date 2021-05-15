/**
 * @Author:weilu
 * @Date:2019/11/10 9:20
 * @Description:${Description}
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        for (int i=0;i<=3;i++) {
            test.f();
        }
    }
    void f(){
        //static int j=0;编译报错，不允许出现static在这里

    }
}
