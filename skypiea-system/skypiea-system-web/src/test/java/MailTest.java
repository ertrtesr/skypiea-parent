import com.skypiea.system.AppSystem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 作者: huangwenjian
 * 描述: 邮件测试类
 * 创建时间: 2017-04-01 16:53
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppSystem.class)
public class MailTest {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 测试简单发送邮件
     */
    @Test
    public void testSimpleMail() throws Exception {
        System.out.println(javaMailSender);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo("631236933@qq.com");
        message.setSubject("主题:简单邮件");
        message.setText("测试邮件内容");
        javaMailSender.send(message);
    }

    /**
     * 测试复杂发送邮件
     */
    @Test
    public void testAttachmentMail() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo("631236933@qq.com");
        helper.setSubject("主题：有附件");
        helper.setText("有附件的邮件");
        FileSystemResource file = new FileSystemResource(new File("/Users/huangwenjian/Desktop/1.jpeg"));
        helper.addAttachment("附件-1.jpg", file);
        javaMailSender.send(mimeMessage);
    }
}
