package top.jxau.support.bean;

import top.jxau.exceptions.BeansException;
import top.jxau.support.factory.BeanFactory;

/**
 * @author plutohh
 */
public interface BeanFactoryAware extends Aware {

   /**
    * 设置 BeanFactory
    * @param beanFactory
    * @throws BeansException
    */
   void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
