package top.jxau.registry.impl;

import top.jxau.registry.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;


/**
 * @author plutohh
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonMap = new HashMap();

    @Override
    public <T> T getSingleton(String beanName) {
        return (T) singletonMap.get(beanName);
    }

    protected void addSingleton(String beanName, Object object) {
        singletonMap.put(beanName, object);
    }
}
