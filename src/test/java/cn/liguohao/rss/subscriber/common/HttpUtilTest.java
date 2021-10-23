package cn.liguohao.rss.subscriber.common;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
public class HttpUtilTest {

    @Test
    public void doGet() {
        String url = "https://liguohao.cn";
        String html = HttpUtil.doGet(url);
        assertNotNull(html);
    }
}
