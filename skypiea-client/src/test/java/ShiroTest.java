import com.skypiea.client.AppClient;
import com.skypiea.client.realm.UserRealm;
import com.skypiea.system.model.UserInfo;
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

    @Test
    public void testShiro() {
        UsernamePasswordToken token = new UsernamePasswordToken("song17", "1234");

        userRealm.setOnUserCallback(new UserRealm.IUserCallback() {
            @Override
            public void OnUserCallback(UserInfo userInfo) {
                System.out.println(userInfo.getId());
                System.out.println(userInfo.getUsername());
                System.out.println(userInfo.getPassword());
                System.out.println(userInfo.getAuthorization());
            }
        });

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
}
