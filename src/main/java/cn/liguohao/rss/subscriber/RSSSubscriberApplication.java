package cn.liguohao.rss.subscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>RSS订阅微程序，用于订阅RSS，并进行消息推送。</p>
 * 实现思路：
 * <ol>
 *     <li>获取RSS订阅源，从后台获取，后台使用服务器Session的方式进行登陆，并且是用户名密码+手机号验证码登陆</li>
 *     <li>开启定时任务，定时获取RSS订阅源信息，比对数据库，是否有新内容</li>
 *     <li>如果有新内容，则保存到数据库，并进行消息推送（多种方式：邮件、短信），同时预留钩子</li>
 * </ol>
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
@SpringBootApplication
public class RSSSubscriberApplication {
    public static void main(String[] args) {
        SpringApplication.run(RSSSubscriberApplication.class, args);
    }
}
