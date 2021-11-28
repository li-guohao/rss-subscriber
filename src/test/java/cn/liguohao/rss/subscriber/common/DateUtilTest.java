package cn.liguohao.rss.subscriber.common;

import cn.liguohao.rss.subscriber.common.util.DateUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/11/28
 */
public class DateUtilTest {

    @Test
    public void parseByDateFormat() {
        String dateStr = "2021-11-28T19:53:00.177";
        Date date = DateUtil.parseByDateFormat(dateStr, Constants.RSS.MIKAN_DATA_FORMAT);
        System.out.println(date);
    }
}
