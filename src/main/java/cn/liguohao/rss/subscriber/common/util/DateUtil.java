package cn.liguohao.rss.subscriber.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/11/28
 */
public class DateUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);

    public static Date parseByDateFormat(String dateStr, String dateFormatStr) {
        Date date = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatStr);
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error("parse date str to Date instance fail, exception: ", e);
        }
        return date;
    }
}
