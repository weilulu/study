package com.wl.study.groovy.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author:weilu
 * @Date: 2019/6/26 18:13
 * @Description: ${Description}
 */
@Component
public class SpringContextUtils implements BeanFactoryPostProcessor,ApplicationContextAware {

    private static ApplicationContext applicationContext;
    /**
     * Modify the application context's internal bean factory after its standard
     * initialization. All bean definitions will have been loaded, but no beans
     * will have been instantiated yet. This allows for overriding or adding
     * properties even to eager-initializing beans.
     *
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException in case of errors
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //String fileName = "script/Response.groovy";
        String fileName = "response.groovy";
        Class<?> groovyClass = getGroovyClass(fileName);
        if(groovyClass == null){
            System.out.println("groovy class is null");
            return;
        }
        registBean(beanFactory,groovyClass);
    }

    /**
     * 获取groovy class对象
     * @param fileName
     * @return
     */
    public static Class<?> getGroovyClass(String fileName){
        Class<?> groovyClass = null;
        try {
            groovyClass = ClassUtils.getGroovyClass(fileName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return groovyClass;
    }
    private void registBean(ConfigurableListableBeanFactory beanFactory,Class<?> clazz){
        if(clazz == null){
            return;
        }
        BeanDefinitionBuilder definitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        AbstractBeanDefinition rawBeanDefinition = definitionBuilder.getRawBeanDefinition();
        SpringContextUtils.applicationContext.getAutowireCapableBeanFactory()
                .applyBeanPostProcessorsAfterInitialization(rawBeanDefinition,"response");

        DefaultListableBeanFactory defaultListableBeanFactory
            = (DefaultListableBeanFactory)beanFactory;
        defaultListableBeanFactory.registerSingleton("responseService",rawBeanDefinition);
    }

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String beanName){
        ApplicationContext context = SpringContextUtils.getApplicationContext();
        return context.getBean(beanName);
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
