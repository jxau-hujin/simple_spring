package top.jxau.registry.impl;

import top.jxau.exceptions.BeansException;
import top.jxau.registry.SingletonBeanRegistry;
import top.jxau.support.bean.DisposableBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author plutohh
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonMap = new HashMap();

    private Map<String, DisposableBean> disposableBeanMap = new HashMap<>();


    @Override
    public <T> T getSingleton(String beanName) {
        return (T) singletonMap.get(beanName);
    }

    protected void addSingleton(String beanName, Object object) {
        singletonMap.put(beanName, object);
    }

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeanMap.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeanMap.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeanMap.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
