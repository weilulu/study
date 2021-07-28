package com.wl.study.spring_extent.bean_override;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.stereotype.Component;

/**
 * @Component
 * 替换原有bean
 * https://blog.csdn.net/gzt19881123/article/details/109333230
 */
@Component
public class ReplaceOldBeanTest extends AnnotationBeanNameGenerator implements BeanDefinitionRegistryPostProcessor {
    private String existedBeanName = "bankaccQueryFacade";

    /**
     * 将之前的bean名称进行修改
     * @param definition
     * @param registry
     * @return
     */
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry){
        String oldBeanName = super.generateBeanName(definition,registry);
        if(oldBeanName.equalsIgnoreCase(existedBeanName)){
            oldBeanName = oldBeanName + "Old";
        }
        return oldBeanName;
    }
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        if(registry.containsBeanDefinition(existedBeanName)){
            generateBeanName(registry.getBeanDefinition(existedBeanName),registry);
        }
        //todo 加载配置文件
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(NewService.class);
        beanDefinitionBuilder.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
        registry.registerBeanDefinition(existedBeanName,beanDefinitionBuilder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}

class NewService{
    public Object getBankaccByCustomerId(String cmsId){
        return new Object();
    }
}
