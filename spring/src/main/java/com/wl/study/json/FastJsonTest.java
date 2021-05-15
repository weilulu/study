package com.wl.study.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.math.BigDecimal;

/**
 * @Author:weilu
 * @Date:2020/7/8 20:36
 * @Description: ${Description}
 */
public class FastJsonTest {

    static class Store{
        private String name;

        private Fruit fruit;

        public String getName() {

            return name;

        }

        public void setName(String name) {

            this.name = name;

        }

        public Fruit getFruit() {

            return fruit;

        }

        public void setFruit(Fruit fruit) {

            this.fruit = fruit;

        }

    }
    interface Fruit {

    }

    static class Apple implements Fruit {

        private BigDecimal price;

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "price=" + price +
                    '}';
        }
    }

    public static void main(String[] args) {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        Store store = new FastJsonTest.Store();

        store.setName("Hollis");

        Apple apple = new FastJsonTest.Apple();


        apple.setPrice(new BigDecimal(0.5));

        store.setFruit(apple);

        String jsonString = JSON.toJSONString(store, SerializerFeature.WriteClassName);
        //String jsonString = JSON.toJSONString(store);

        System.out.println("toJSONString : " + jsonString);


        /*Store newStore = JSON.parseObject(jsonString, Store.class);

        System.out.println("parseObject : " + newStore);

        Apple newApple = (Apple)newStore.getFruit();

        System.out.println("getFruit : " + newApple);*/
    }
}
