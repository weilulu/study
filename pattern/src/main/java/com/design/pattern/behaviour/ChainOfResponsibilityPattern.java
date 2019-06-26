package com.design.pattern.behaviour;

/**
 * @Author:weilu
 * @Date: 2019/6/15 16:35
 * @Description: 责任链模式
 *
 * 每个接收者都包含对另一个接收者的引用，如果一个对象不通处理该请求，那么它会把相同的请求传给下一个接收者，依此类推
 */
public class ChainOfResponsibilityPattern {

    abstract static class AbstractLogger{
        static int INFO = 1;
        static int DEBUG = 2;
        static int ERROR = 3;

        protected int level;

        protected AbstractLogger nextLogger;
        public void setNextLogger(AbstractLogger nextLogger){
            this.nextLogger = nextLogger;
        }
        public void logMessage(int level,String message){
            if(this.level <= level){//判断是否有条件处理
                write(message);
            }
            if(nextLogger != null){
                nextLogger.logMessage(level,message);
            }
        }
        abstract protected void write(String message);
    }

    static class ConsoleLogger extends AbstractLogger{
        public ConsoleLogger(int level){
            this.level = level;
        }
        @Override
        public void write(String message){
            System.out.println("standard console::logger:"+message);
        }
    }

    static class ErrorLogger extends AbstractLogger{
        public ErrorLogger(int level){
            this.level = level;
        }
        @Override
        public void write(String message){
            System.out.println("standard error::logger:"+message);
        }
    }
    static class FileLogger extends AbstractLogger{
        public FileLogger(int level){
            this.level = level;
        }
        @Override
        public void write(String message){
            System.out.println("standard file::logger:"+message);
        }
    }

    static AbstractLogger getChainOfLoggers(){
        AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        AbstractLogger fileLogger = new FileLogger(AbstractLogger.DEBUG);
        AbstractLogger consoleLogger = new ConsoleLogger(AbstractLogger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger loggerChain = getChainOfLoggers();
        loggerChain.logMessage(AbstractLogger.INFO,"information");
        loggerChain.logMessage(AbstractLogger.DEBUG,"a debug level information");
        loggerChain.logMessage(AbstractLogger.ERROR,"a error level information");
    }
}
