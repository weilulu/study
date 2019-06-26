package com.design.pattern.creation.prototype;

/**
 * @Author:weilu
 * @Date: 2019/5/13 21:03
 * @Description: ${Description}
 */
public class Circle extends Shape {

    public Circle(){
        type = "Circle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
