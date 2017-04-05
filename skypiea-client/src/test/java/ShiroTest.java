import com.skypiea.client.AppClient;
import com.skypiea.client.realm.UserRealm;
import com.skypiea.system.mapper.UserMapper;
import com.skypiea.system.model.RoleInfo;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-27 08:38
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppClient.class)
public class ShiroTest {

    @Autowired
    SecurityManager securityManager;

    @Autowired
    UserRealm userRealm;

    @Autowired
    Subject subject;

    @Autowired
    UserMapper userMapper;

    @Test
    public void testShiroLogin() {
        UsernamePasswordToken token = new UsernamePasswordToken("song17", "1234");

        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名找不到");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误");
        }
    }

    @Test
    public void testShiroPermission() {
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan1", "1234");
        subject.login(token);
        System.out.println("认证通过:" + subject.isAuthenticated());
        boolean role2 = subject.hasRole("role2");
        System.out.println("role2:" + role2);

        List<RoleInfo> roles = userMapper.getRolesByUsername("zhangsan3");
        System.out.println(roles.get(0).getName());
    }
}
