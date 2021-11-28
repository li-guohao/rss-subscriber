package cn.liguohao.rss.subscriber.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/11/29
 */
public class StringUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(StringUtil.class);

    public static void isEmpty(String str) {
        isEmpty(str, "str is empty, str: " + str);
    }

    public static void isEmpty(String str, String message) {
        if(str == null || "".equals(str)) {
            throw new IllegalArgumentException(message);
        }
    }
}
