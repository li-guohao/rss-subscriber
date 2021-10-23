package cn.liguohao.rss.subscriber.service;

import cn.liguohao.rss.subscriber.dao.RSSArticeRepository;
import cn.liguohao.rss.subscriber.dao.RSSSubscribeRepository;
import cn.liguohao.rss.subscriber.entity.RSSSubscribe;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
@Service
public class RSSService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RSSService.class);

    @Autowired private RSSSubscribeRepository rssSubscribeRepository;
    @Autowired private RSSArticeRepository rssArticeRepository;

    /**
     * 扫描RSS更新，更新内容保存数据库并进行推送
     */
    public void scanRSSUpdate(){
        // 获取正常状态的RSS订阅
        List<RSSSubscribe> rssSubscribes = rssSubscribeRepository.findByStatusIs(RSSSubscribe.Status.NORMAL);
        // TODO 请求订阅链接获取内容
        for(RSSSubscribe rssSubscribe : rssSubscribes) {
            String address = rssSubscribe.getAddress();
            try {
                URI uri = new URIBuilder(address).build();

            } catch (URISyntaxException e) {
                LOGGER.error("http request fail, exception: ", e);
            }
        }
        // TODO 比对数据库，查看是否有更新
        // TODO 如无更新则不作操作，有更新则将新内容保存到数据库，并进行消息推送
    }
}
