package com.design.pattern.creation.prototype;

/**
 * @Author:weilu
 * @Date: 2019/5/13 20:50
 * @Description: 需要重用的对象，要实现Cloneable接口
 */
public abstract class Shape implements Cloneable{
    private String id;
    protected String type;

    abstract void draw();

    public String getType(){
        return type;
    }
    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @Override
    public Object clone(){
        Object cloneObject = null;
        try {
            cloneObject = super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return cloneObject;
    }
}
