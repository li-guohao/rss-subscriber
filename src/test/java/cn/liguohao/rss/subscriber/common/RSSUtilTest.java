package cn.liguohao.rss.subscriber.common;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
public class RSSUtilTest {

    @Test
    public void parseFeed() {
        String url = "http://www.comicat.org/rss-%E6%A1%9C%E9%83%BD%E5%AD%97%E5%B9%95%E7%BB%84.xml";
        SyndFeed syndFeed = RSSUtil.parseFeed(url);
//        System.out.println("Title: " + syndFeed.getTitle());
//        System.out.println("Author: " + syndFeed.getAuthor());
//        System.out.println("FeedType: " + syndFeed.getFeedType());
//        System.out.println("Description: " + syndFeed.getDescription());
//        System.out.println("Copyright: " + syndFeed.getCopyright());
//        System.out.println("Link: " + syndFeed.getLink());
//        System.out.println("Language: " + syndFeed.getLanguage());
//        System.out.println("Encoding: " + syndFeed.getEncoding());
//        System.out.println("Generator: " + syndFeed.getGenerator());
//        System.out.println("Uri: " + syndFeed.getUri());
//        System.out.println("Authors: " + syndFeed.getAuthors());
//        System.out.println("Docs: " + syndFeed.getDocs());
//        System.out.println("Entries Size: " + syndFeed.getEntries().size());
        assertNotNull(syndFeed);
        assertNotNull(syndFeed.getEntries());
        assertTrue(syndFeed.getEntries().size() > 0);
    }

    @Test
    public void generateFeed() {
        String title = "hello rss";
        String description = "rss feed desc";
        String feedLink = "https://liguohao.cn/rss";
        SyndFeedImpl syndFeed = new SyndFeedImpl();
        syndFeed.setTitle(title);
        syndFeed.setDescription(description);
        syndFeed.setLink(feedLink);
        assertNotNull(RSSUtil.generateFeed(syndFeed));
    }
}
