package cn.liguohao.rss.subscriber.rss;

import cn.liguohao.rss.subscriber.common.Constants;
import cn.liguohao.rss.subscriber.common.util.DateUtil;
import cn.liguohao.rss.subscriber.common.util.StringUtil;
import cn.liguohao.rss.subscriber.common.util.URLUtil;
import cn.liguohao.rss.subscriber.entity.RSSArtice;
import com.rometools.rome.feed.synd.SyndEntry;
import org.jdom2.Content;
import org.jdom2.Element;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/11/28
 */
@Component
public class RSSHandler {
    /**
     * 根据不同RSS连接数据格式，做对应的转化
     * @param rsid rss订阅的id
     * @param syndEntry 根据订阅源获取的单个订阅项
     * @param rssUrl rss源站提供的订阅URL
     * @return rss订阅项的具体内容
     */
    public RSSArtice convert(Long rsid, SyndEntry syndEntry, String rssUrl) {
        Assert.notNull(rsid, "rsid is not null");
        StringUtil.isEmpty(rssUrl);
        RSSSit rssSit = analyzingRSSUrl(rssUrl);
        RSSArtice rssArtice = convert(syndEntry, rssSit);
        rssArtice.setRsid(rsid);
        rssArtice.setRssSit(rssSit);
        return rssArtice;
    }

    /**
     * 根据RSS源提供的URL，分析出是哪家站点的
     * @param rssUrl rss源站提供的订阅URL
     * @return 源站
     */
    public RSSSit analyzingRSSUrl(String rssUrl) {
        RSSSit rssSit;
        StringUtil.isEmpty(rssUrl);
        String domain = URLUtil.parseDomain(rssUrl);
        if(domain.contains(Constants.RSS.COMICAT)) {
            rssSit = RSSSit.COMICAT;
        } else if(domain.contains(Constants.RSS.MIKAN)) {
            rssSit = RSSSit.MIKAN;
        } else {
            throw new IllegalArgumentException("sorry, rssUrl domain " + domain + " not supported for current app");
        }
        return rssSit;
    }

    /**
     * 根据源站类型，调用不同的逻辑处理转化
     * @param syndEntry 根据订阅源获取的单个订阅项
     * @param rssSit 提供订阅的源站
     * @return RSS订阅项
     */
    private RSSArtice convert(SyndEntry syndEntry, RSSSit rssSit) {
        RSSArtice rssArtice = null;
        if(RSSSit.COMICAT == rssSit) {
            rssArtice = convertRSSSitComicat(syndEntry);
        } else if(RSSSit.MIKAN == rssSit) {
            rssArtice = convertRSSSitMikan(syndEntry);
        }
        return rssArtice;
    }

    private RSSArtice convertRSSSitComicat(SyndEntry syndEntry) {
        RSSArtice rssArtice = new RSSArtice();
        rssArtice.setTitle(syndEntry.getTitle());
        rssArtice.setAuthor(syndEntry.getAuthor());
        rssArtice.setDescription(syndEntry.getDescription().getValue());
        String pageUri = syndEntry.getUri();
        rssArtice.setPageUrl(pageUri);
        rssArtice.setReleaseTime(syndEntry.getPublishedDate());
        return rssArtice;
    }

    private RSSArtice convertRSSSitMikan(SyndEntry syndEntry) {
        RSSArtice rssArtice = new RSSArtice();
        rssArtice.setTitle(syndEntry.getTitle());
        rssArtice.setAuthor(syndEntry.getAuthor());
        rssArtice.setDescription(syndEntry.getDescription().getValue());
        String pageUri = syndEntry.getUri();
        rssArtice.setPageUrl(pageUri);
        for(Element element : syndEntry.getForeignMarkup()) {
            if("torrent".equals(element.getName())) {
                for(Content content : element.getContent()) {
                    if(content instanceof Element) {
                        Element level2Ele = (Element) content;
                        switch (level2Ele.getName()) {
                            case "link" :{
                                rssArtice.setPageUrl(level2Ele.getContent().get(0).getValue());
                                break;
                            }
                            case "contentLength": {
                                rssArtice.setContentLength(Long.valueOf(level2Ele.getContent().get(0).getValue()));
                                break;
                            }
                            case "pubDate" : {
                                rssArtice.setReleaseTime(DateUtil.parseByDateFormat(
                                        level2Ele.getContent().get(0).getValue(),
                                        Constants.RSS.MIKAN_DATA_FORMAT
                                ));
                                break;
                            }
                            default: {}
                        }
                    } else {
                        throw new IllegalArgumentException("mikan rss format has changed, please contact this app developer to update code");
                    }
                }
            }
        }
        return rssArtice;
    }
}
