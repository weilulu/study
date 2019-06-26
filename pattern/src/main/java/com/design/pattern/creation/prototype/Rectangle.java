package com.design.pattern.creation.prototype;

/**
 * @Author:weilu
 * @Date: 2019/5/13 20:54
 * @Description: ${Description}
 */
public class Rectangle extends Shape {

    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw(){
        System.out.println("Rectangle draw");
    }
}
