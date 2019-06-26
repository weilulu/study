package com.design.pattern.structure;

/**
 * @Author:weilu
 * @Date: 2019/5/26 14:45
 * @Description: 外观模式
 * 根据‘单一职责原则’，在软件中将一个系统划分为若干子系统有利于降低整个系统的复杂性，一个常见的设计
 * 目标是使子系统间的通信和相互依赖关系达到最小，而达到该目标的途径之一就是引入一个外观对象，它为子系统
 * 的访问提供了一个简单而单一的入口
 *
 * 缺点：在不引入抽象外观类的情况下，增加新的子系统可能需要修改外观类或客户断源代码，违背‘开闭原则’
 *
 * 角色：
 * 外观类
 * 子系统
 */
public class FacadePattern {

    interface Shape{
        void draw();
    }
    static class Rectanle implements Shape{
        @Override
        public void draw(){
            System.out.println("rectangle draw");
        }
    }
    static class Square implements Shape{
        @Override
        public void draw(){
            System.out.println("square draw");
        }
    }
    static class Circle implements Shape{
        @Override
        public void draw(){
            System.out.println("circle draw");
        }
    }

    /**
     * 外观类
     */
    static class ShapeMaker{
        private Shape circle;
        private Shape rectangle;
        private Shape square;

        public ShapeMaker() {
            circle = new Circle();
            rectangle = new Rectanle();
            square = new Square();
        }
        public void drawCircle(){
            circle.draw();
        }
        public void drawRectangle(){
            rectangle.draw();
        }
        public void drawSqure(){
            square.draw();
        }
    }

    public static void main(String[] args) {
        ShapeMaker maker = new ShapeMaker();
        maker.drawCircle();
        maker.drawRectangle();
        maker.drawSqure();
    }
}
