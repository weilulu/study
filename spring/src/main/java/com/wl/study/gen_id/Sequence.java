package com.wl.study.gen_id;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:weilu
 * @Date: 2019/3/25 11:32
 *
 * 1,Sequence.get()方法上的synchronized是为了处理一个实例上多线程的问题
 * 2,使用乐观锁update数据是为了处理多个实例的问题，A实例：1-5;B实例：6-10....不同的实例有不同的组
 */
public class Sequence {

    private DataSource dataSource;
    private int blockSize = 10;//块大小
    private long startValue = 0;//起始值
    private final static String GET_SQL = "select id from sequence_value where name=?";
    private final static String NEW_SQL = "insert into sequence_value(id,name) values(?,?)";
    private final static String UPDATE_SQL = "update sequence_value set id=? where name= ? and id = ?";//乐观锁更新

    private Map<String,Step> stepMap = new HashMap<>();//多线程下，stepMap不会变

    /**
     * 方法入口处加锁
     * @param sequenceName
     * @return
     */
    public synchronized long get(String sequenceName){
        System.out.println(stepMap.toString());
        Step  step = stepMap.get(sequenceName);
        if(step == null){
            //step初始化
            step = new Step(startValue,startValue+blockSize);
            stepMap.put(sequenceName,step);
        }else{
            if(step.currentValue < step.endValue){
                //给定的块未用完，直接在块范围内取，无须操作表
                System.out.println("不用从表里取");
                return step.incrementAndGet();
            }
        }
        //块内的值已用完或第一次取数
        for(int i=0;i<blockSize;i++){//重试blockSize次
            if(getNextBlock(sequenceName,step)){
                System.out.println("表里取");
                return step.incrementAndGet();
            }
        }
        throw new RuntimeException("no more id");
    }

    /**
     * 取一个块的值
     * 一般是第一次，还有就是块的值用完了
     * @param sequenceName
     * @param step
     * @return
     */
    private boolean getNextBlock(String sequenceName,Step step){
        Long value = getPersistenceValue(sequenceName);
        //表里无数据，写入
        if(value == null){
            try {
                value = newPersistenceValue(sequenceName);
            }catch (Exception e){
                System.out.println("newPersistenceValue 出错!");
                //试错一次，多个线程进行数据插入的时候，会有异常发生，考虑在写入的时候加锁
                //这里会有多线程的情形，不同实例下会产生这种情形
                value = getPersistenceValue(sequenceName);
            }
        }
        //这里除了第一次取数或一个块的值用完，都需要更新表这个原因外，另一个
        //重要原因是通过数据库乐观锁处理多个实例取到相同块里的值
        boolean flag = updateValue(value,sequenceName) == 1;
        if(flag){
            step.setCurrentValue(value);
            step.setEndValue(value + blockSize);
        }
        return flag;
    }

    /**
     * 乐观更新表里的值
     * @param value
     * @param sequenceName
     * @return
     */
    private int updateValue(long value,String sequenceName){
        System.out.println("ooo");
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(UPDATE_SQL);
            statement.setLong(1,value + blockSize);
            statement.setString(2,sequenceName);
            statement.setLong(3,value);
            return statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("newPersistenceValue error!");
        }finally {
            if(statement != null){
                try {
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 插入数据
     * @param sequenceName
     * @return
     */
    private Long newPersistenceValue(String sequenceName){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(NEW_SQL);
            statement.setLong(1,startValue);
            statement.setString(2,sequenceName);
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("newPersistenceValue error!");
        }finally {
            if(statement != null){
                try {
                    statement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return startValue;
    }

    /**
     * 表里查数据
     * @param sequenceName
     * @return
     */
    private Long getPersistenceValue(String sequenceName){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(GET_SQL);
            statement.setString(1,sequenceName);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getLong("id");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("getPersistenceValue error!");
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    static class Step{
        private long currentValue;
        private long endValue;

        Step(long currentValue,long endValue){
            this.currentValue = currentValue;
            this.endValue = endValue;
        }
        public void setCurrentValue(long currentValue){
            this.currentValue = currentValue;
        }
        public void setEndValue(long endValue){
            this.endValue = endValue;
        }
        public long incrementAndGet(){
            //内部类不用加锁
            return ++currentValue;
        }
    }
}
