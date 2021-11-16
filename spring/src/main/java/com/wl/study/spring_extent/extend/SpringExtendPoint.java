package com.wl.study.spring_extent.extend;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringExtendPoint extends ClassPathXmlApplicationContext {

    @Override
    public void initPropertySources(){
        getEnvironment().getSystemProperties().put("key","new_properties");
    }

    @Override
    public void customizeBeanFactory(DefaultListableBeanFactory beanFactory){
        super.setAllowBeanDefinitionOverriding(true);
        super.setAllowCircularReferences(true);
        super.customizeBeanFactory(beanFactory);
    }

}

class ExtendBeanAware implements ApplicationContextAware{
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext){
        this.applicationContext = applicationContext;
    }
}

/**
 *
 * 在所有beanDefinition加载完成之后，bean实例化之前对beanDefinition进行修改。
 * 两种方式。方式一：比如有配置文件如下：
 * <id="person" "name"="Zhao"/>
 * 我们想给person动态添加'interest'属性，就可以重写postProcessBeanFactory。
 * 方式二：那要是我们要完全
 * 加入一个新的bean呢？可以实现BeanDefinitionRegistryPostProcessor，
 * 像line 52重写postProcessBeanDefinitionRegistry方法
 *
 * todo https://blog.csdn.net/caihaijiang/article/details/35552859
 * 还需要分析BeanFactoryPostProcessor的所有子类？？
 *
 */
class CustomBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    /**
     * 方式二
     * @param beanDefinitionRegistry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(person.class);
        beanDefinitionRegistry.registerBeanDefinition("person",beanDefinition);
    }

    /**
     * 方式一
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("person");
        PropertyValue propertyValue = new PropertyValue("interest","writing");
        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
    }
    class person{
        private String name;
        //get()/set()
    }
}