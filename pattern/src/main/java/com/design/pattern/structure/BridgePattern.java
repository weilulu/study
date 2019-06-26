package com.design.pattern.structure;

/**
 * @Author:weilu
 * @Date: 2019/5/15 20:58
 * @Description: 桥接模式
 * 定义：将抽象部分与它的实现部分分离，使它们都可以独立地变化
 *
 * 模式动机：设想如果要绘制矩形、圆形、正文形，我们至少需要3个形状类，进一步
 * 　　　　　如果绘制的图形需要具有不同的颜色，如红、绿、蓝等，此时至少有如下两种方案：
 * 　　　　　１，为每一种形状都提供一套颜色的版本
 *          ２，根据实际需要对形状和颜色进行组合
 *      显然，按方案２进行设计，系统中类的个数更少，系统扩展更为方便
 *
 * 角色：抽象类
 *      扩充抽象类
 *      实现类接口
 *      具体实现类
 */
public class BridgePattern {

    /**
     * 桥接接口
     */
    interface DrawAPI{
        void drawCircle(int radius,int x,int y);
    }

    static class RedCircle implements DrawAPI{
        @Override
        public void drawCircle(int radius,int x,int y){
            System.out.println("Drawing Circle[ color: red, radius: "
                    + radius +", x: " +x+", "+ y +"]");
        }
    }

    static class GreenCircle implements DrawAPI {
        @Override
        public void drawCircle(int radius, int x, int y) {
            System.out.println("Drawing Circle[ color: green, radius: "
                    + radius +", x: " +x+", "+ y +"]");
        }
    }

    /**
     * 在这里进行桥接，shape类里持有画画的功能
     */
    abstract static class Shape{
        protected DrawAPI drawAPI;
        protected Shape(DrawAPI drawAPI){
            this.drawAPI = drawAPI;
        }
        abstract void draw();
    }

    static class Circle extends Shape {
        private int x, y, radius;

        public Circle(int x, int y, int radius, DrawAPI drawAPI) {
            super(drawAPI);
            this.x = x;
            this.y = y;
            this.radius = radius;
        }

        @Override
        public void draw() {
            drawAPI.drawCircle(radius,x,y);
        }
    }

    public static void main(String[] args) {
        Shape redCircle = new Circle(100,100,10,new RedCircle());
        Shape greenCircle = new Circle(100,100,10,new GreenCircle());
        redCircle.draw();
        greenCircle.draw();
    }
}
