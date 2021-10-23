package cn.liguohao.rss.subscriber.service;

import cn.liguohao.rss.subscriber.RSSSubscriberApplication;
import cn.liguohao.rss.subscriber.entity.RSSArtice;
import cn.liguohao.rss.subscriber.entity.RSSSubscribe;
import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
@SpringBootTest(classes = RSSSubscriberApplication.class)
public class RSSServiceTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(RSSServiceTest.class);

    @Autowired
    private RSSService rssService;

    @Test
    public void addSubscriber() {
        RSSSubscribe rssSubscribe = new RSSSubscribe();
        rssSubscribe.setAddress("http://www.comicat.org/rss-%E6%A1%9C%E9%83%BD%E5%AD%97%E5%B9%95%E7%BB%84.xml");
        rssSubscribe.setName("桜都字幕组");
        rssSubscribe.setCreateTime(new Date());
        rssSubscribe.setStatus(RSSSubscribe.Status.NORMAL);
        rssService.addSubscriber(rssSubscribe);
    }

    @Test
    public void findByName() {
        rssService.findByName("桜都字幕组").forEach(rssSubscribe -> System.out.println(JSON.toJSON(rssSubscribe)));
    }

    @Test
    public void scanRSSUpdate() {
        rssService.scanRSSUpdate();
    }

    @Test
    public void findArticeByRsid() {
        List<RSSArtice> rssArticeList = rssService.findArticeByRsid(1L);
        rssArticeList.forEach(rssArtice -> {
            rssArtice.setContent(null);
            rssArtice.setDescription(null);
            System.out.println(JSON.toJSON(rssArtice));
        });
        LOGGER.info("find size={}", rssArticeList.size());
    }
    @Test
    public void findArticeByTitle() {
        List<RSSArtice> articeByTitle = rssService.findArticeByTitle("[桜都字幕组] 180秒能让你的耳朵感到幸福吗");
        if(articeByTitle.size() > 0) {
            articeByTitle.forEach(artice -> {
                artice.setContent(null);
                artice.setDescription(null);
                System.out.println(JSON.toJSON(artice));
            });
        }
    }

    @Test
    public void deleteLatestArtice() {
        rssService.deleteLatestArtice(1L);
    }

}
