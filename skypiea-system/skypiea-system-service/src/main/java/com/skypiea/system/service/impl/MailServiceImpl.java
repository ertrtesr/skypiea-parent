package com.skypiea.system.service.impl;

import com.skypiea.common.result.SPResult;
import com.skypiea.common.utils.ExceptionUtils;
import com.skypiea.system.model.MailInfo;
import com.skypiea.system.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 作者: huangwenjian
 * 描述: 邮件收发service
 * 创建时间: 2017-04-01 14:59
 */

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public SPResult sendSimpleMail(MailInfo mailInfo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(mailInfo.getTo());
        message.setSubject(mailInfo.getSubject());
        message.setText(mailInfo.getContent());
        try {
            mailSender.send(message);
            return SPResult.ok();
        } catch (MailException e) {
            e.printStackTrace();
            return SPResult.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public SPResult sendHtmlMail(MailInfo mailInfo) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mailInfo.getTo());
            helper.setSubject(mailInfo.getSubject());
            helper.setText(mailInfo.getContent(), true);
            mailSender.send(message);
            return SPResult.ok();
        } catch (MessagingException e) {
            e.printStackTrace();
            return SPResult.error(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public SPResult sendAttachmentMail(MailInfo mailInfo, String filePath) {

        return null;
    }
}
