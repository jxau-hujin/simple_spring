package top.jxau.support.instantiation.impl;

import top.jxau.BeanDefinition;
import top.jxau.exceptions.BeansException;
import top.jxau.support.instantiation.InstantiationStrategy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author plutohh
 */
public class JdkInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor constructor, Object[] args) {
        Class clazz = beanDefinition.getBeanClass();
        try {
          if(constructor != null) {
              return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
          } else {
              return clazz.getDeclaredConstructor().newInstance();
          }
        } catch (InvocationTargetException e) {
            throw new BeansException("InvocationTargetException [" + clazz.getName() + "]", e);
        } catch (InstantiationException e) {
            throw new BeansException("InstantiationException [" + clazz.getName() + "]", e);
        } catch (IllegalAccessException e) {
            throw new BeansException("IllegalAccessException [" + clazz.getName() + "]", e);
        } catch (NoSuchMethodException e) {
            throw new BeansException("NoSuchMethodException [" + clazz.getName() + "]", e);
        }
    }
}
