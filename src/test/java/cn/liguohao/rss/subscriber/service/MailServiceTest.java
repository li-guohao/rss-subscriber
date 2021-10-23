package cn.liguohao.rss.subscriber.service;

import cn.liguohao.rss.subscriber.RSSSubscriberApplication;
import cn.liguohao.rss.subscriber.entity.RSSArtice;
import cn.liguohao.rss.subscriber.entity.RSSSubscribe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
@SpringBootTest(classes = RSSSubscriberApplication.class)
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void publishRSSArticeMail() {
        String mailTargetAddress = "liguohao_cn@qq.com";
        RSSArtice rssArtice = new RSSArtice();
        rssArtice.setTitle("测试RSS内容更新推送邮件");
        rssArtice.setContent("<h1>测试邮件标题</h1><br><p>测试邮件内容</p>");
        mailService.publishRSSArticeMail(mailTargetAddress, rssArtice);
    }

}
