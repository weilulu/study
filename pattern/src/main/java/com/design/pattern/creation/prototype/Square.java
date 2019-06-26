package com.design.pattern.creation.prototype;

/**
 * @Author:weilu
 * @Date: 2019/5/13 20:56
 * @Description: ${Description}
 */
public class Square extends Shape {
    public Square(){
        type = "Square";
    }
    @Override
    public void draw(){
        System.out.println("Square draw");
    }
}
