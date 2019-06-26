package com.wl.study.bit.redis;

/**
 * @Author:weilu
 * @Date: 2019/4/10 11:54
 */
/*@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig extends JCacheConfigurerSupport{

    @Autowired
    private Environment environment;

    *//**
     * bean用在方法上，告诉spring，可以从这个方法中拿到一个bean,
     * 然后这个bean对象交给spring管理，产生这个bean对象的方法spring只会调用一次，
     * 随后spring会将这个bean对象放在自己的ioc容器里
     *
     *//*
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory fac = new JedisConnectionFactory();
        fac.setHostName(environment.getProperty("redis.hostName"));
        fac.setPort(Integer.parseInt(environment.getProperty("redis.port")));
        //fac.setPassword(environment.getProperty("redis.password"));
        fac.setTimeout(Integer.parseInt(environment.getProperty("redis.timeout")));
        fac.getPoolConfig().setMaxIdle(Integer.parseInt(environment.getProperty("redis.maxIdle")));
        fac.getPoolConfig().setMaxTotal(Integer.parseInt(environment.getProperty("redis.maxTotal")));
        fac.getPoolConfig().setMaxWaitMillis(Integer.parseInt(environment.getProperty("redis.maxWaitMillis")));
        fac.getPoolConfig().setMinEvictableIdleTimeMillis(
                Integer.parseInt(environment.getProperty("redis.minEvictableIdleTimeMillis")));
        fac.getPoolConfig()
                .setNumTestsPerEvictionRun(Integer.parseInt(environment.getProperty("redis.numTestsPerEvictionRun")));
        fac.getPoolConfig().setTimeBetweenEvictionRunsMillis(
                Integer.parseInt(environment.getProperty("redis.timeBetweenEvictionRunsMillis")));
        fac.getPoolConfig().setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("redis.testOnBorrow")));
        fac.getPoolConfig().setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("redis.testWhileIdle")));
        return fac;

    }

    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}*/
