package com.skypiea.system.service;

import com.skypiea.common.result.SPResult;
import com.skypiea.system.model.MailInfo;

/**
 * 作者: huangwenjian
 * 描述:
 * 创建时间: 2017-04-01 14:58
 */
public interface MailService {

    SPResult sendSimpleMail(MailInfo mailInfo);

    SPResult sendHtmlMail(MailInfo mailInfo);

    SPResult sendAttachmentMail(MailInfo mailInfo, String filePath);
}
