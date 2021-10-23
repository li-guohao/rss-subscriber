package cn.liguohao.rss.subscriber.service;

import cn.liguohao.rss.subscriber.common.RSSUtil;
import cn.liguohao.rss.subscriber.dao.RSSArticeRepository;
import cn.liguohao.rss.subscriber.dao.RSSSubscribeRepository;
import cn.liguohao.rss.subscriber.entity.RSSArtice;
import cn.liguohao.rss.subscriber.entity.RSSSubscribe;
import com.rometools.rome.feed.synd.SyndFeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
@Service
public class RSSService {
    private final static Logger LOGGER = LoggerFactory.getLogger(RSSService.class);

    @Autowired private RSSSubscribeRepository rssSubscribeRepository;
    @Autowired private RSSArticeRepository rssArticeRepository;
    @Autowired private MailService mailService;
    @Value("${app.targetEmail}") private String targetEmail;

    public List<RSSSubscribe> findAll() {
        return rssSubscribeRepository.findAll();
    }

    /**
     * 添加RSS订阅
     * @param rssSubscribe 待添加的订阅
     */
    public void addSubscriber(RSSSubscribe rssSubscribe) {
        if(rssSubscribeRepository.existsByAddressEqualsIgnoreCase(rssSubscribe.getAddress())) {
            LOGGER.warn("add fail, because already exist, feed name={} address={}", rssSubscribe.getName(), rssSubscribe.getAddress());
        } else {
            rssSubscribeRepository.save(rssSubscribe);
        }
    }

    public List<RSSSubscribe>  findByName(String name) {
        return rssSubscribeRepository.findByStatusAndNameContains(RSSSubscribe.Status.NORMAL, name);
    }

    public List<RSSArtice>  findArticeByTitle(String title) {
        return rssArticeRepository.findByTitleContains(title);
    }

    public List<RSSArtice>  findArticeByRsid(Long rsid) {
        return rssArticeRepository.findByRsidOrderByReleaseTimeDescIdDesc(rsid);
    }

    public void deleteLatestArtice(Long rsid) {
        Optional<RSSArtice> rssArticeOptional = rssArticeRepository.findByRsidOrderByReleaseTimeDescIdDesc(rsid).stream().findFirst();
        if(rssArticeOptional.isPresent()) {
            rssArticeRepository.delete(rssArticeOptional.get());
        }
    }

    /**
     * 扫描RSS更新，更新内容保存数据库 默认推送最近一天的新内容
     */
    public void scanRSSUpdate(){
        // 获取正常状态的RSS订阅
        List<RSSSubscribe> rssSubscribes = rssSubscribeRepository.findByStatusIs(RSSSubscribe.Status.NORMAL);
        // 请求订阅链接获取内容
        for(RSSSubscribe rssSubscribe : rssSubscribes) {
            try {
                String address = rssSubscribe.getAddress();
                SyndFeed syndFeed = RSSUtil.parseFeed(address);
                if(syndFeed == null|| syndFeed.getEntries().size() == 0) {
                    LOGGER.warn("fetch content fail, current rss subscribe address is {}", rssSubscribe.getAddress());
                    break;
                }
                AtomicInteger newContentCount = new AtomicInteger(0);

                syndFeed.getEntries().forEach(syndEntry -> {
                    String title = syndEntry.getTitle();
                    Date publishedDate = syndEntry.getPublishedDate();
                    // 如数据库不存在则添加到数据库，并进行消息推送
                    if(!rssArticeRepository.existsByTitleEqualsAndReleaseTimeIs(title, publishedDate)) {
                        RSSArtice rssArtice = new RSSArtice();
                        rssArtice.setRsid(rssSubscribe.getId());
                        rssArtice.setTitle(title);
                        rssArtice.setAuthor(syndEntry.getAuthor());
                        rssArtice.setDescription(syndEntry.getDescription().getValue());
                        String pageUri = syndEntry.getUri();
//                        String htmlUrlContent = HttpUtil.doGet(pageUri);
//                        rssArtice.setContent(htmlUrlContent);
                        rssArtice.setPageUrl(pageUri);
                        rssArtice.setReleaseTime(publishedDate);
                        rssArticeRepository.save(rssArtice);
                        // 最近1天内的新内容会发送邮件
                        Date date = new Date();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.DATE, -1);
                        date = calendar.getTime();
                        if(date.before(publishedDate)) {
                            publishNewContent(rssArtice);
                        }
                        newContentCount.incrementAndGet();
                    }
                });

                if(newContentCount.get() > 0 ){
                    LOGGER.info("add {} subscriber to database, and publish new content to target", newContentCount.get());
                    newContentCount.set(0);
                }

            } catch (Exception e) {
                LOGGER.error("fetch rss feed fail, exception: ", e);
            }
        }
    }

    /**
     * 推送消息
     * 目前支持如下:
     * <ul>
     *     <li>邮件推送</li>
     * </ul>
     * @param rssArtice 待推送的内容
     */
    private void publishNewContent(RSSArtice rssArtice) {
        if(targetEmail == null || "".equals(targetEmail)) {
            throw new IllegalArgumentException("rss subscriber target email is not set");
        }
        mailService.publishRSSArticeMail(targetEmail, rssArtice);
    }
}
