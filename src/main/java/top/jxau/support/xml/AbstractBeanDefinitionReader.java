package top.jxau.support.xml;

import top.jxau.registry.BeanDefinitionRegistry;
import top.jxau.support.core.io.BeanDefinitionReader;
import top.jxau.support.core.io.loader.DefaultResourceLoader;
import top.jxau.support.core.io.loader.ResourceLoader;


/**
 * @author plutohh
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

}
