import org.junit.Before;
import org.junit.Test;
import top.jxau.*;
import top.jxau.support.bean.BeanDefinition;
import top.jxau.support.bean.BeanReference;
import top.jxau.support.bean.PropertyValue;
import top.jxau.support.bean.PropertyValues;
import top.jxau.support.factory.impl.DefaultBeanFactory;

public class ApiTest {
    DefaultBeanFactory beanFactory = null;

    @Before
    public void before() {
        beanFactory = new DefaultBeanFactory();
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
