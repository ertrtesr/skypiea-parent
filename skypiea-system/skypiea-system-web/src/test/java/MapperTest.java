import com.skypiea.system.AppSystem;
import com.skypiea.system.mapper.UserMapper;
import com.skypiea.system.model.RoleInfo;
import com.skypiea.system.model.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-03-29 10:10
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppSystem.class)
public class MapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void userAndRoleMapperTest() {
        List<UserInfo> list = userMapper.getAllUsers();
        for (UserInfo user : list) {
            RoleInfo role = user.getRole();
            System.out.println(user.getUsername() + "==>" + role.getId() + ":" + role.getName());
        }
    }
}
