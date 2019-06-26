package com.design.pattern.creation;

/**
 * @Author:weilu
 * @Date: 2019/5/3 10:43
 * @Description: 工厂方法模式
 * 定义：定义一个用于创建对象的接口，让子类决定实例化哪一个类，工厂方法使一个类的实例化延迟到其子类
 * 适用场景：
 * 一、在任务生成复杂对象的时候，都可以使用工厂方法模式，而简单的对象则不必，这样会增加系统的复杂度
 * 二、工厂模式是一种典型的解耦模式，如果调用者自己组装产品需要增加依赖关系时，使用工厂模式会大大降低对象之间的耦合度
 * 三、工厂模式将实例产品的任务交由实现类实现，扩展性比较好，当需要系统有较好的扩展性的时候，可以考虑工厂模式
 *
 * 模式包含的角色：
 * 一、Product：抽象产品
 * 二、ConcreteProduct：具体产品
 * 三、Factory：抽象工厂
 * 四、ConcreteFactory：具体工厂
 */
public class MethodOfFactory {

    interface IProduct{
        void productMethod();
    }
    static class Product implements IProduct{
        @Override
        public void productMethod(){
            System.out.println("product");
        }
    }
    interface IFactory{
        IProduct createProduct();
    }
    static class Factory implements IFactory{
        @Override
        public IProduct createProduct(){
            return new Product();
        }
    }

    public static void main(String[] args) {
        IFactory factory = new Factory();
        IProduct product = factory.createProduct();
        product.productMethod();
    }
}
