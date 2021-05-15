package log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

/**
 * @Author:weilu
 * @Date:2019/8/6 20:23
 * @Description: log借助lambda实现，
 * 但感觉也没有特别的，将<code>info()</code>进行封装后，不使用lambda也很方便
 * 这里也确实只是对日志级别判断进行了提取，还有一个弊端：传入的日志参数不能使用占位符了
 */
public class LogWithLambda {
    private Logger logger = LoggerFactory.getLogger(LogWithLambda.class);

    public void log(){
        info(() -> "look at this...");
    }

    public void info(Supplier<String> message){
        if(logger.isInfoEnabled()){
            logger.info(message.get());
        }
    }
}
