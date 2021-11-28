package cn.liguohao.rss.subscriber.api;

import cn.liguohao.rss.subscriber.entity.RSSArtice;
import cn.liguohao.rss.subscriber.entity.RSSSubscribe;
import cn.liguohao.rss.subscriber.service.RSSService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/10/23
 */
@RestController
@RequestMapping("/api/rsssub")
public class RSSSubRestController {

    @Autowired private RSSService rssService;
    @Value("${app.token}") private String token;

    @GetMapping("/add")
    public String addRSSSubscriber(@RequestParam String token, @RequestParam String feed) {
        if(!this.token.equals(token)) {
            return "403 You do not have access";
        } else {
            RSSSubscribe rssSubscribe = new RSSSubscribe();
            rssSubscribe.setAddress(feed);
            rssSubscribe.setName("default name");
            rssSubscribe.setCreateTime(new Date());
            rssSubscribe.setStatus(RSSSubscribe.Status.NORMAL);
            rssService.addSubscriber(rssSubscribe);
            return "add rss subscriber success";
        }
    }

    @GetMapping("/findArticeByRsid")
    public String findArticeByRsid(@RequestParam String token, @RequestParam Long rsid) {
        if(!this.token.equals(token)) {
            return "403 You do not have access";
        } else {
            List<RSSArtice> rssArticeList = rssService.findArticeByRsid(rsid);
            rssArticeList.forEach(rssArtice -> {
                rssArtice.setContent(null);
                rssArtice.setDescription(null);
            });
            return JSON.toJSONString(rssArticeList);
        }
    }

    @GetMapping("/findAll")
    public String findAll(@RequestParam String token) {
        if(!this.token.equals(token)) {
            return "403 You do not have access";
        } else {
            List<RSSSubscribe> rssSubscribes = rssService.findAll();
            return JSON.toJSONString(rssSubscribes);
        }
    }

    @GetMapping("/scan")
    public String scanRSSUpdate(@RequestParam String token) {
        if(!this.token.equals(token)) {
            return "403 You do not have access";
        } else {
            try {
                rssService.scanRSSUpdate();
                return "success";
            } catch (Exception exception) {
                exception.printStackTrace();
                return "fail, exception msg is: " + exception.getMessage();
            }
        }
    }


}
