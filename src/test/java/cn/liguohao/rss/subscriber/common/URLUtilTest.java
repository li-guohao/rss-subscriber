package cn.liguohao.rss.subscriber.common;

import cn.liguohao.rss.subscriber.common.util.URLUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/11/28
 */
public class URLUtilTest {

    @Test
    public void parseDomain() {
        final String url = "https://liguohao.cn/test/hello";
        String domain = URLUtil.parseDomain(url);
        Assertions.assertEquals("liguohao.cn", domain);
    }
}
