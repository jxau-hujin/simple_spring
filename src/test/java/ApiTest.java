import org.junit.Test;
import top.jxau.BeanDefinition;
import top.jxau.UserService;
import top.jxau.support.impl.DefaultBeanFactory;

public class ApiTest {


    @Test
    public void test_01() {
        DefaultBeanFactory beanFactory = new DefaultBeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
