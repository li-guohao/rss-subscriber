package cn.liguohao.rss.subscriber.common;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.SyndFeedOutput;
import com.rometools.rome.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

/**
 * RSS 解析器 Rhome 简单封装
 *
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
public class RSSUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(RSSUtil.class);

    /**
     * 解析RSS Feed
     *
     * @param url rss feed str url
     * @return SyndFeed instance
     */
    public static SyndFeed parseFeed(String url) {
        SyndFeed feed = null;
        try {
            feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        } catch (FeedException e) {
            LOGGER.error("parse feed fail, feed exception: ", e);
        } catch (IOException e) {
            LOGGER.error("parse feed fail, io exception: ", e);
        }
        return feed;
    }

    /**
     * 生成RSS订阅(Feed)
     * @param feed SyndFeed对象 实现类可用SyndFeedImpl
     * @return xml格式字符串
     */
    public static String generateFeed(SyndFeed feed) {
        if(feed.getFeedType() == null || "".equalsIgnoreCase(feed.getFeedType())) {
            feed.setFeedType("rss_2.0");
        }
        if(feed.getTitle() == null || "".equals(feed.getTitle())) {
            throw new IllegalArgumentException("SyndFeed instance is not set title");
        }
        if(feed.getLink() == null || "".equals(feed.getLink())) {
            throw new IllegalArgumentException("SyndFeed instance is not set feed link");
        }
        String result = null;
        try {
            result = new SyndFeedOutput().outputString(feed);
        } catch (FeedException e) {
            LOGGER.error("generate feed fail, exception: ", e);
        }

        return result;
    }

}
