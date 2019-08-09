package com.wl.study.enum_interface;

/**
 * @Author:weilu
 * @Date:2019/8/9 14:15
 * @Description:使用接口实现枚举的可扩展,具体做法是让不同枚举实现接口
 */
public enum BasicOperation implements Operation{//枚举实现了接口，如果还有别的算法，也可以实现这个接口
    PLUS("+"){
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-"){
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    }
    ;

    private final String symbol;
    BasicOperation(String symbol){
        this.symbol = symbol;
    }
    @Override
    public String toString(){
        return symbol;
    }

    public static void main(String[] args) {
        int x = 5;
        int y = 3;
        test(BasicOperation.class,x,y);
    }

    /**
     *
     * @param opSet
     * @param x
     * @param y
     * @param <T>传入的Class必须是一个Enum且有实现Operation
     */
    private static <T extends Enum & Operation> void test(Class<T> opSet,double x,double y){
        for(Operation op : opSet.getEnumConstants()){
            System.out.printf("%f %s %f = %f%n",
                    x,op,y,op.apply(x,y));
        }
    }
}

interface Operation{
    double apply(double x,double y);
}
