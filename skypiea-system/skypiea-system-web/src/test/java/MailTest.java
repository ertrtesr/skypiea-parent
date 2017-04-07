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

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Properties;

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

    /**
     * pop3是接收协议
     *
     * @throws Exception
     */
    @Test
    public void testReceiveMail() throws Exception {

        /**
         * 如果您的邮件客户端不在上述列出的范围内，您可以尝试如下通用配置：
         接收邮件服务器：imap.qq.com
         发送邮件服务器：smtp.qq.com
         账户名：您的QQ邮箱账户名（如果您是VIP邮箱，账户名需要填写完整的邮件地址）
         密码：您的QQ邮箱密码
         电子邮件地址：您的QQ邮箱的完整邮件地址

         如何设置IMAP服务的SSL加密方式？
         使用SSL的通用配置如下：
         接收邮件服务器：imap.qq.com，使用SSL，端口号993
         发送邮件服务器：smtp.qq.com，使用SSL，端口号465或587
         账户名：您的QQ邮箱账户名（如果您是VIP帐号或Foxmail帐号，账户名需要填写完整的邮件地址）
         密码：您的QQ邮箱密码
         电子邮件地址：您的QQ邮箱的完整邮件地址
         */
        // 准备连接服务器的会话信息
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", "imap.qq.com");
        props.setProperty("mail.imap.port", "143");
       /*
       *Session类定义了一个基本的邮件对话。
       */
        Session session = Session.getDefaultInstance(props);

       /*
        * Store类实现特定邮件协议上的读、写、监视、查找等操作。
        * 通过Store类可以访问Folder类。
        * Folder类用于分级组织邮件，并提供照Message格式访问email的能力。
        */
        Store store = session.getStore("imap");
        store.connect("631236933@qq.com", "irtxefjozdtrbaij");
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);       //一定要写这句,否则的话会显示java.lang.IllegalStateException: This operation is not allowed on a closed folder
        Message[] messages = inbox.getMessages();
        for (Message message : messages) {
            System.out.println(message.getSubject());
        }
    }
}
