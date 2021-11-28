package cn.liguohao.rss.subscriber.common;

import cn.liguohao.rss.subscriber.rss.RSSSit;

import java.util.Locale;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/11/7
 */
public interface Constants {
    String DEFAULT_PW = "liguohao.cn";
    String DEFAULT_EMPTY = "empty";
    String LIGHT = "true";
    String DARK = "false";
    String COLON = ":";
    /**
     * 系统设置项初始化列表
     */
    String[] INIT_SETTING_ITEM = {
            // 应用是否初始化
            "APP:INIT:" + LIGHT,
            // 应用TOKEN令牌，和密码一样，使用MD5加密存储，默认123456，只能通过手机验证码进行更改。
            "APP:TOKEN:" + DEFAULT_PW,
            // 邮件模块开关，默认关闭
            "MODULE:MAIL:" + DARK,
            // 手机验证码开关，默认关闭
            "MODULE:PHONE_MSG:" + DARK,
            // 邮件SMTP协议服务器地址
            "MAIL:SMTP_HOST:" + DEFAULT_EMPTY,
            // 邮件SMTP协议发信地址
            "MAIL:SMTP_USERNAME:" + DEFAULT_EMPTY,
            // 邮件SMTP协议发信地址对应的SMTP密码
            "MAIL:SMTP_PASSWORD:" + DEFAULT_EMPTY,
    };

    interface RSS {
        String COMICAT = RSSSit.COMICAT.name().toLowerCase(Locale.ROOT);
        String MIKAN = RSSSit.MIKAN.name().toLowerCase(Locale.ROOT);
        String MIKAN_DATA_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    }

}
