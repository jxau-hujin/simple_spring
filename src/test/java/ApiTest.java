import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;
import top.jxau.*;
import top.jxau.support.bean.BeanDefinition;
import top.jxau.support.bean.BeanReference;
import top.jxau.support.bean.PropertyValue;
import top.jxau.support.bean.PropertyValues;
import top.jxau.support.context.impl.ClassPathXmlApplicationContext;
import top.jxau.support.core.io.Resource;
import top.jxau.support.core.io.loader.DefaultResourceLoader;
import top.jxau.support.factory.impl.DefaultBeanFactory;
import top.jxau.support.factory.impl.DefaultListableBeanFactory;
import top.jxau.support.xml.XmlBeanDefinitionReader;

import java.io.IOException;
import java.io.InputStream;

public class ApiTest {

    DefaultBeanFactory beanFactory = null;
    DefaultResourceLoader resourceLoader = null;

    @Before
    public void before() {
        beanFactory = new DefaultBeanFactory();
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_01() {
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_02() {
        BeanDefinition beanDefinition = new BeanDefinition(User.class);
        beanFactory.registerBeanDefinition("user", beanDefinition);

        User user = beanFactory.getBean("user", "username", "password");

        System.out.println(user.toString());
    }

    @Test
    public void test_03() {
        PropertyValues a = initAPropertyValues();
        PropertyValues b = initBPropertyValues();
        PropertyValues c = initCPropertyValues();
        BeanDefinition aBeanDefinition = new BeanDefinition(A.class, a);
        BeanDefinition bBeanDefinition = new BeanDefinition(B.class, b);
        BeanDefinition cBeanDefinition = new BeanDefinition(C.class, c);
        beanFactory.registerBeanDefinition("A", aBeanDefinition);
        beanFactory.registerBeanDefinition("B", bBeanDefinition);
        beanFactory.registerBeanDefinition("C", cBeanDefinition);

        A a1 = beanFactory.getBean("A");
        B b1 = beanFactory.getBean("B");
        C c1 = beanFactory.getBean("C");

        System.out.println("a1: {[hashcode: " + a1.hashCode() + "] b: {[hashcode: " + a1.getB().hashCode() + "]" + c + "}}");
        System.out.println("a2: {[hashcode: " + b1.hashCode() + "] a: {[hashcode: " + b1.getA().hashCode() + "]" + c + "}}");
        System.out.println("c1: {[username: " + c1.getUsername() + "] a: {[password: " + c1.getPassword() + "]}}");
    }

    @Test
    public void test_04() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:spring.xml");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_05() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_06() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/jxau-hujin/simple_spring/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_07() {
        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        A a = beanFactory.getBean("A", A.class);
        System.out.println("A: " + a.hashCode() + " A.B: " + a.getB().hashCode() + " A.token: " + a.getToken());
        B b = beanFactory.getBean("B", B.class);
        System.out.println("B: " + b.hashCode() + " B.A: " + b.getA().hashCode() + " B.token: " + b.getToken());
        C c = beanFactory.getBean("C", C.class);
        System.out.println("C: " + c.hashCode() + " username: " + c.getUsername() + " password: " + c.getPassword());
        User user = beanFactory.getBean("user", User.class);
        System.out.println("user: " + user.hashCode() + " username: " + user.getUsername() + " password: " + user.getPassword());
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_08() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);
    }

    @Test
    public void test_09() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring08.xml");

        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
    }

    @Test
    public void test_10() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close！")));
    }

    @Test
    public void test_11() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        UserDao userDao = applicationContext.getBean("userDao", UserDao.class);
        System.out.println(userDao.queryUserName("10001"));
        System.out.println(userDao.queryUserName("10002"));
        System.out.println(userDao.queryUserName("10003"));

    }

    @Test
    public void test_12() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");


        AwareBean awareBean = applicationContext.getBean("awareBean", AwareBean.class);
        System.out.println(awareBean.toString());
    }

    private PropertyValues initCPropertyValues() {

        PropertyValues propertyValues = new PropertyValues();
        PropertyValue username = new PropertyValue("username", "mac");
        PropertyValue password = new PropertyValue("password", "pro");
        propertyValues.addPropertyValue(username);
        propertyValues.addPropertyValue(password);
        return propertyValues;
    }

    private PropertyValues initBPropertyValues() {

        PropertyValues propertyValues = new PropertyValues();
        PropertyValue token = new PropertyValue("token", "offer");
        PropertyValue a = new PropertyValue("a", new BeanReference("A"));
        PropertyValue c = new PropertyValue("c", new BeanReference("C"));
        propertyValues.addPropertyValue(token);
        propertyValues.addPropertyValue(a);
        propertyValues.addPropertyValue(c);
        return propertyValues;
    }

    private PropertyValues initAPropertyValues() {

        PropertyValues propertyValues = new PropertyValues();
        PropertyValue token = new PropertyValue("token", "show");
        PropertyValue b = new PropertyValue("b", new BeanReference("B"));
        PropertyValue c = new PropertyValue("c", new BeanReference("C"));
        propertyValues.addPropertyValue(token);
        propertyValues.addPropertyValue(b);
        propertyValues.addPropertyValue(c);
        return propertyValues;
    }
}
