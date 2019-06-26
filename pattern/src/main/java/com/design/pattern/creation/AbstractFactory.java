package com.design.pattern.creation;

/**
 * @Author:weilu
 * @Date: 2019/5/3 11:21
 * @Description: 抽象工厂模式
 * 定义：为创建一组相关或互相依赖的对象提供一个接口，而且无需指定他们的具体类
 * 适用场景：抽象工厂是工厂方法模式的升级，它用来创建一组相关或者依赖的对象。它与工厂方法模式区别在于：
 * 工厂方法模式针对的是一个产品等级结构；而抽象工厂模式则是针对的多个产品等级结构
 *
 * 模式中包含的角色：
 * 一、AbstractFactory： 抽象工厂
 * 二、ConcreteFactory：具体工厂
 * 三、AbstractProduct：抽象产品
 * 四、Product：具体产品
 */
public class AbstractFactory {

    interface IProduct1{
        void show();
    }
    interface IProduct2{
        void show();
    }
    static class Product1 implements IProduct1{
        @Override
        public void show(){
            System.out.println("product1");
        }
    }
    static class Product2 implements IProduct2{
        @Override
        public void show(){
            System.out.println("product2");
        }
    }
    interface IFactory{
        IProduct1 createProduct1();
        IProduct2 createProduct2();
    }
    static class Factory implements IFactory{
        @Override
        public IProduct1 createProduct1() {
            return new Product1();
        }
        @Override
        public IProduct2 createProduct2(){
            return new Product2();
        }
    }

    public static void main(String[] args) {
        IFactory factory = new Factory();
        factory.createProduct1().show();
        factory.createProduct2().show();
    }
}
