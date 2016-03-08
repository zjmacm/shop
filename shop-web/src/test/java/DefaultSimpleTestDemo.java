import com.shop.controller.UserController;
import com.shop.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;

/**
 * 这种方式的缺点已经说过了，如不能走Spring MVC完整流程
 *（不能走Servlet的过滤器链、SpringMVC的类型转换、数据验证、数据绑定、拦截器等等），
 * 如果做基本的测试没问题，这种方式就是纯粹的单元测试
 * Created by ldz on 18/12/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext.xml")
/*
@ContextConfiguration("/shop-service.xml")
*/
public class DefaultSimpleTestDemo {
    @Autowired
    private UserService userService;
    private UserController userController;

        @Before
        public void setUp() {
            //userController = new UserController();
            //安装userCtroller依赖 比如userService
           // userController.setUserService(userService);
        }

        @Test
        public void registerTest() {
            MockHttpServletRequest req = new MockHttpServletRequest();
            ModelAndView mv = userController.register();

            ModelAndViewAssert.assertViewName(mv, "register");

        }


}
