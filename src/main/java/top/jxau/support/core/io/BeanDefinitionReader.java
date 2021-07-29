package top.jxau.support.core.io;

import top.jxau.exceptions.BeansException;
import top.jxau.registry.BeanDefinitionRegistry;
import top.jxau.support.core.io.loader.ResourceLoader;


/**
 * @author plutohh
 */
public interface BeanDefinitionReader {

    /**
     * 获取注册 BeanDefinition
     * @return
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 根据 Resource 加载 BeanDefinition
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 根据多个 Resource 加载 BeanDefinition
     * @param resources
     * @throws BeansException
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 根据多个 location 加载 BeanDefinition
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws BeansException;

}
