package top.jxau;

import top.jxau.support.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyBeanFactory implements FactoryBean<TestService> {

    @Override
    public Object getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {

            System.out.println("alibaba");
            System.out.println("byte-dance");
            System.out.println("tencent");

            return "代理了 " + method.getName();
        };
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{TestService.class}, handler);    }


    @Override
    public Class<?> getObjectType() {
        return TestService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
