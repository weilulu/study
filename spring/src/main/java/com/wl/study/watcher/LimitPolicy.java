package com.wl.study.watcher;

import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:weilu
 * @Date: 2019/3/21 18:13
 * 限流策略
 */
public class LimitPolicy {

    private String method;
    private String desc;
    private String join;
    private String threadHold;
    private Map<String,String> conditionMap = new HashMap<>();
    private String conditions;
    private RateLimiter limiter;

    enum JoinLogic{
        AND,OR
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(String join) {
        this.join = join;
    }

    public String getThreadHold() {
        return threadHold;
    }

    public void setThreadHold(String threadHold) {
        double max = Double.valueOf(threadHold) / 60;
        if(limiter == null){
            limiter = RateLimiter.create(max);//1秒生成max个令牌
        }else{
            limiter.setRate(max);
        }
        this.threadHold = threadHold;
    }

    public Map<String, String> getConditionMap() {
        return conditionMap;
    }

    public void setConditionMap(Map<String, String> conditionMap) {
        this.conditionMap = conditionMap;
    }

    public String getConditions() {
        if (conditionMap.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : conditionMap.entrySet()) {
            sb.append(entry.getKey());
            sb.append(":");
            sb.append(entry.getValue());
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public void setConditions(String conditions) {
        if (StringUtils.isNotBlank(conditions)) {
            String[] kvArr = StringUtils.split(conditions, ",");
            for (String kvstr : kvArr) {
                String[] kv = StringUtils.split(kvstr, ":");
                conditionMap.put(kv[0], kv[1]);
            }
        }
    }

    public RateLimiter getLimiter() {
        return limiter;
    }

    public void setLimiter(RateLimiter limiter) {
        this.limiter = limiter;
    }
}
