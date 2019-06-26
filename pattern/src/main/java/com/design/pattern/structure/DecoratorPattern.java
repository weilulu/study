package com.design.pattern.structure;

/**
 * @Author:weilu
 * @Date: 2019/5/24 21:39
 * @Description: 装饰者模式
 *
 * 装饰者模式以对客户透明的方式动态地给一个对象附加上更多的责任，就增加对象功能来说，
 * 装饰者模式比生成子类实现更加灵活
 *
 * 一般有两种方式可以实现给一个类或对象增加行为：
 *  继承机制
 *  关联机制，装饰者模式使用了这种方式
 *  从这里可以看到，从增加对象的功能来看，装饰者模式比生成子类实现更加灵活
 *
 * 角色：
 *   抽象构件
 *   具体构件
 *   抽象装饰类
 *   具体装饰类
 */
public class DecoratorPattern {

    /**
     * 抽象构件
     */
    interface Shape{
        void draw();
    }

    /**
     * 具体构件
     */
    static class Rectangle implements Shape {
        @Override
        public void draw(){
            System.out.println("Shape:Rectangle");
        }
    }

    /**
     * 具体构件
     */
    static class Circle implements Shape {

        @Override
        public void draw() {
            System.out.println("Shape: Circle");
        }
    }

    /**
     * 抽象装饰类
     */
    abstract static class ShapeDecorator implements Shape{
        protected Shape decoratorShape;//将一个类的对象嵌入到另一个对象中

        public ShapeDecorator(Shape decoratorShape){
            this.decoratorShape = decoratorShape;
        }

        @Override
        public void draw(){
            decoratorShape.draw();
        }
    }

    /**
     * 具体装饰类
     */
    static class RedShapeDecorator extends ShapeDecorator{
        public RedShapeDecorator(Shape decoratorShape){
            super(decoratorShape);
        }
        @Override
        public void draw(){
            decoratorShape.draw();
            setRedBorder(decoratorShape);
        }
        private void setRedBorder(Shape decoratedShape){
            System.out.println("Border color:red");
        }
    }

    public static void main(String[] args) {
        Shape circle = new Circle();
        ShapeDecorator redCircle = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());
        circle.draw();
        System.out.println("-------------");
        redCircle.draw();
        System.out.println("-------------");
        redRectangle.draw();
    }



}
