import java.util.HashMap;
import java.util.Map;

/**
 * 这个例子主要是为了说明一个类里面的实例变量实例化的时机
 * https://www.cnblogs.com/hanks/p/11965407.html
 * 1，类肯定是先初始化，再实例化的
 * 2，另外一个顺序：静态域 --> 实例域 --> 构造函数；细化展开：
 * 对于静态域：其先经过链接创建静态变量，赋default值；再到初始化阶段给静态变量赋assign值和执行静态代码块。
 * 对于实例域：也是分成创建和赋值两个部分，不同的只是加入构造函数（形参和代码块）：先创建实例变量和构造函数形参以default值，然后对变量和形参赋assign值和执行实例代码块，最后执行构造函数的代码
 *
 * 类变量：准备阶段根据系统设置默认值；初始化阶段根据程序重新给变量赋值
 * 实例变量：在调用构造器之前会赋默认值，然后再执行init()方法（由非静态实例变量显示赋值代码和非静态代码块、对应构造器代码组成。https://www.cnblogs.com/pxblog/p/11567790.html对init方法有说明）
 */
public class NoneStaticVariable {

    public Map<String,String> map = new HashMap<>(8);
    public Test test = new Test();

    public static void main(String[] args) {
        NoneStaticVariable variable = new NoneStaticVariable();
        NoneStaticVariable variable1 = new NoneStaticVariable();
        System.out.println(variable.map.size());
        //1,下面两个test的hashcode不一样
        //2，在输出hashcode前'Test initialize'己经输出了
        //针对第2点更进一步，在调用new NoneStaticVariable();之前test这个实例变量己经被初始化好了
        System.out.println(variable.test.hashCode());//1587487668
        System.out.println(variable1.test.hashCode());//1199823423
    }

}

class Test{
    public Test(){
        System.out.println("Test initialize");
    }
}
