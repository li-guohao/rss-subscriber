package cn.liguohao.rss.subscriber.controller;

import cn.liguohao.rss.subscriber.common.Constants;
import cn.liguohao.rss.subscriber.common.util.MD5Util;
import cn.liguohao.rss.subscriber.entity.Setting;
import cn.liguohao.rss.subscriber.service.SettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/11/7
 */
@Controller
@RequestMapping("/app")
public class AppController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AppController.class);
    private final SettingService settingService;

    public AppController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/init")
    public String init() {
        settingService.init();
        return "redirect:/";
    }

}
