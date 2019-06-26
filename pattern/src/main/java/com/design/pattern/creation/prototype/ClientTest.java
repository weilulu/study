package com.design.pattern.creation.prototype;

/**
 * @Author:weilu
 * @Date: 2019/5/13 21:07
 * @Description: 原型模式
 * 功能：用于创建惩处的对象，同时又能保证性能
 */
public class ClientTest {

    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape = (Shape) ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape.getType());

        Shape clonedShape2 = (Shape) ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());

        Shape clonedShape3 = (Shape) ShapeCache.getShape("3");
        System.out.println("Shape : " + clonedShape3.getType());
    }
}
