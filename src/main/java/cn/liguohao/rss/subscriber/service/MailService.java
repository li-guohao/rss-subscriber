package cn.liguohao.rss.subscriber.service;

import cn.liguohao.rss.subscriber.entity.RSSArtice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.UUID;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
@Service
public class MailService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * @see #publishRSSArticeMail(String, RSSArtice, boolean)
     */
    public void publishRSSArticeMail(String mailAddress, RSSArtice rssArtice) {
        publishRSSArticeMail(mailAddress, rssArtice, true);
    }

    /**
     * 发送RSS内容更新邮件
     *
     * @param mailAddress 目标地址
     * @param rssArtice   邮件内容
     * @param isDesc      是否是概要内容 默认为true
     */
    public void publishRSSArticeMail(String mailAddress, RSSArtice rssArtice, boolean isDesc) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(mailAddress);
            helper.setSubject(rssArtice.getTitle());
            if (isDesc) {
                String prefix = "<p>详细链接：" + rssArtice.getPageUrl() + "</p><br>";
                helper.setText(prefix + rssArtice.getDescription(), true);
            } else {
                helper.setText(rssArtice.getContent(), true);
            }
            LOGGER.info("send mail to {} success, mail title is {}", mailAddress, rssArtice.getTitle());
            mailSender.send(message);
        } catch (MessagingException e) {
            LOGGER.error("send mail fail, message exception: ", e);
        } catch (Exception e) {
            LOGGER.error("send mail fail, exception: ", e);
        }
    }

}
