package cn.liguohao.rss.subscriber.controller;

import cn.liguohao.rss.subscriber.service.SettingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/11/7
 */
@Controller
public class PageController {

    private final SettingService settingService;
    private AtomicBoolean appHasBeenInitialized = null;

    public PageController(SettingService settingService) {
        this.settingService = settingService;
    }

    @GetMapping("/")
    public String home(Model model) {
        if(appHasBeenInitialized == null || !appHasBeenInitialized.get()) {
            appHasBeenInitialized = new AtomicBoolean(settingService.isBeenInitialized());
        }
        if(!appHasBeenInitialized.get()) {
            return "redirect:/app/init";
        }
        return "index";
    }

    @GetMapping("/index")
    public String index() {
        return "redirect:/";
    }

}
