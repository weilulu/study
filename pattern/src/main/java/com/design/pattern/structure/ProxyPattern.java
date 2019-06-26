package com.design.pattern.structure;

/**
 * @Author:weilu
 * @Date: 2019/6/15 15:57
 * @Description: 代理模式
 *
 * 定义：给某个对象提供一个代理，并由代理对象控制对原对象的引用，对被代理对象的控制是这个模式的重点，也是区别其它模式的地方
 * 动机：通过引入代理对象来间接访问一个对象，这正是代理模式的动机
 *
 * 角色：
 *   Subject:抽象主题角色
 *   Proxy：代理主题角色
 *   RealSubject：真正主题角色
 */
public class ProxyPattern {

    public interface Image{
        void display();
    }
    static class RealImage implements Image{
        private String fileName;
        public RealImage(String fileName){
            this.fileName = fileName;
            loadFromDisk(fileName);
        }
        @Override
        public void display() {
            System.out.println("display "+fileName);
        }
        public void loadFromDisk(String fileName){
            System.out.println("loading "+fileName);
        }
    }

    /**
     * 代理类，在这里可以对被代理的对象进行控制
     */
    static class ProxyImage implements Image{
        private RealImage realImage;
        private String fileName;
        public ProxyImage(String fileName){
            this.fileName = fileName;
        }
        @Override
        public void display(){
            if(realImage == null){
                realImage = new RealImage(fileName);
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        Image image = new ProxyImage("test.jpg");
        image.display();//第一次需要去加载资源
        System.out.println("");
        image.display();//第二次不再需要加载

    }
}
