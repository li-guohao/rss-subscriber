package cn.liguohao.rss.subscriber.service;

import cn.liguohao.rss.subscriber.dao.RSSArticeRepository;
import cn.liguohao.rss.subscriber.dao.RSSSubscribeRepository;
import cn.liguohao.rss.subscriber.entity.RSSSubscribe;
import org.eclipse.jetty.websocket.jsr356.endpoints.EndpointInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
@Service
public class RSSService {

    @Autowired private RSSSubscribeRepository rssSubscribeRepository;
    @Autowired private RSSArticeRepository rssArticeRepository;

    public void scanRSSUpdate(){
    }
}
