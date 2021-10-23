package cn.liguohao.rss.subscriber.configure;

import cn.liguohao.rss.subscriber.service.RSSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
@Configuration
@EnableScheduling
public class ApplicationConfiguration {
    private final static Logger LOGGER = LoggerFactory.getLogger(ApplicationConfiguration.class);

    @Autowired private RSSService rssService;

    /**
     * 半小时扫描一次
     */
    @Scheduled(cron = "0 */30 * * * ?")
    public void execScanRSSUpdate() {
        long startTime = System.currentTimeMillis();
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("start exec scan rss update ...");
        }
        rssService.scanRSSUpdate();
        if(LOGGER.isDebugEnabled()) {
            LOGGER.debug("end exec scan rss update, use {} ms", (System.currentTimeMillis() - startTime));
        }
    }
}
