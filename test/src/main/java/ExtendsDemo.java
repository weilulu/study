/**
 * 这个例子写学习继承里的向上/向下转型，可参见：https://www.jianshu.com/p/5771df145452
 * 向上转型（子类赋给父类）一般不会有问题；
 * 向下转型（父类强转成子类并赋给子类）的时候就需要注意了
 */
public class ExtendsDemo {
    public void test(ExtendsDemo extendsDemo){
        //向下转型
        //A a = new ExtendsDemo();编译出错，正确的写法
        ExtendsDemo demo = new ExtendsDemo();
        //下面可以转型成功，但有风险，因为这个demo可能是ExtendsDemo，也可能是ExtendsDemo的其它子类，
        //所以在转型前需要使用instanceof先判断下再转
        //if( demo instanceof A) ....
        A a = (A)demo;
    }
}

class A extends ExtendsDemo{}
class B extends ExtendsDemo{}
