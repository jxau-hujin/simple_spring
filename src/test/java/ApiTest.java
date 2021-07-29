import org.junit.Test;
import top.jxau.BeanDefinition;
import top.jxau.User;
import top.jxau.UserService;
import top.jxau.support.factory.impl.DefaultBeanFactory;

public class ApiTest {


    @Test
    public void test_01() {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_02() {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(User.class);
        beanFactory.registerBeanDefinition("user", beanDefinition);

        User user = beanFactory.getBean("user", "username", "password");

        System.out.println(user.toString());
    }
}
