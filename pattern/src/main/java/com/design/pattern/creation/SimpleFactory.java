package com.design.pattern.creation;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author:weilu
 * @Date: 2019/5/5 9:24
 * @Description: 简单工厂方法
 * 模式分析：
 * 一、将对象的创建和对象本身业务处理分离可降低系统的耦合度
 * 二、工厂方法是静态方法，可通过类名直接调用，在调用的过程中，只需要传入一个简单的参数就可以
 * 三、简单工厂模式最大的问题在于工厂的职责相对过重，增加新的产品需要修改工厂类的判断逻辑，与开闭原则相违背
 * 四、当需要什么，只需传入一个正确的参数，就可以获取所需要的对象，无须知道其创建细节
 *
 * 模式包含的角色：
 * 一、Factory：工厂角色
 * 二、Product：抽象产品角色
 * 三、ConcreteProduct：具体产品角色
 */
public class SimpleFactory {

    interface Shape{
        void draw();
    }
    static class Rectangle implements Shape{
        @Override
        public void draw(){
            System.out.println("rectangle draw");
        }
    }
    static class Square implements Shape{
        @Override
        public void draw() {
            System.out.println("square draw");
        }
    }
    static class ShapeFactory{
        public Shape getShape(String shapeType){
            if(StringUtils.isBlank(shapeType)){
                return null;
            }
            if("rectangle".equalsIgnoreCase(shapeType)){
                return new Rectangle();
            }else if("square".equalsIgnoreCase(shapeType)){
                return new Square();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape rectangle = shapeFactory.getShape("rectangle");
        rectangle.draw();

        Shape square = shapeFactory.getShape("square");
        square.draw();
    }

}
