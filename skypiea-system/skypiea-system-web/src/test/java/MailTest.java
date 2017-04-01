import com.skypiea.system.AppSystem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 作者: huangwenjian
 * 描述: 邮件测试类
 * 创建时间: 2017-04-01 16:53
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppSystem.class)
public class MailTest {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * 测试简单发送邮件
     */
    @Test
    public void testSimpleSendMail() {
        System.out.println(mailSender);
    }

    /**
     * 测试复杂发送邮件
     */
    @Test
    public void testComplexSendMail() {

    }
}
