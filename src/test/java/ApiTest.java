import org.junit.Test;
import top.jxau.BeanDefinition;
import top.jxau.BeanFactory;
import top.jxau.UserService;

public class ApiTest {


    @Test
    public void test_01() {
        BeanFactory beanFactory = new BeanFactory();

        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
