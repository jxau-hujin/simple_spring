package top.jxau.support.instantiation.impl;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import top.jxau.support.bean.BeanDefinition;
import top.jxau.support.instantiation.InstantiationStrategy;

import java.lang.reflect.Constructor;

/**
 * @author plutohh
 */
public class CglibInstantiationStrategy implements InstantiationStrategy {


    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor constructor, Object[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if(constructor == null) {
            return enhancer.create();
        }
        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
