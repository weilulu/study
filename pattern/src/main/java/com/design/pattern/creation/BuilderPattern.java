package com.design.pattern.creation;

/**
 * @Author:weilu
 * @Date: 2019/5/5 9:37
 * @Description: 建造者模式
 * 一、建造者相对于工厂模式，创建的对象更加复杂
 * 二、建造者模式中，产品类和建造者类是比较稳定的，而Director类变化的可能性会比较大
 * 三、建造者扩展也比较容易，如果有新的需求，通过实现一个新的建造者类就可以
 *
 * 模式中包含的角色：
 * 一、Builder：抽象建造者
 * 二、ConcreteBuilder：具体建造者
 * 三、Director：指挥者
 * 四、Product：产品角色
 */
public class BuilderPattern {

    /**
     * 比较稳定
     */
    static class Product{
        private String name;
        private String type;
        public void showProduct(){
            System.out.println("name:"+name);
            System.out.println("type:"+type);
        }
        public void setName(String name){
            this.name = name;
        }
        public void setType(String type){
            this.type=type;
        }

    }
    /**
     * 比较稳定
     */
    abstract static class Builder{
        public abstract void setPart(String arg1,String arg2);
        public abstract Product getProduct();
    }
    /**
     * 比较稳定
     */
    static class ConcreteBuilder extends Builder{//Concrete.有形的，实在的
        private Product product = new Product();

        @Override
        public Product getProduct() {
            return product;
        }
        @Override
        public void setPart(String args1,String args2){
            product.setName(args1);
            product.setType(args2);
        }
    }

    /**
     * 相对不稳定，很有可能会有变化
     * 指挥者的角色，在具体建造者只有一个的时候可省略，由Builder扮演指挥者与建造者双重角色
     */
    public static class Director{
        private Builder builder = new ConcreteBuilder();
        public Product getAProduct(){
            builder.setPart("宝马汽车","X7");
            return builder.getProduct();
        }
        public Product getBProduct(){
            builder.setPart("奥迪汽车","Q5");
            return builder.getProduct();
        }
    }

    /**
     * 客户端
     * @param args
     */
    public static void main(String[] args) {
        Director director = new Director();
        Product aProduct = director.getAProduct();
        aProduct.showProduct();

        Product bProduct = director.getBProduct();
        bProduct.showProduct();
    }
}
