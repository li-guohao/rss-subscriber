package cn.liguohao.rss.subscriber.service;

import cn.liguohao.rss.subscriber.common.Constants;
import cn.liguohao.rss.subscriber.common.util.MD5Util;
import cn.liguohao.rss.subscriber.controller.AppController;
import cn.liguohao.rss.subscriber.dao.SettingRepository;
import cn.liguohao.rss.subscriber.entity.Setting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author <a href="http://liguohao.cn" target="_blank">liguohao</a>
 * @date 2021/11/7
 */
@Service
public class SettingService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SettingService.class);

    private final SettingRepository settingRepository;

    public SettingService(SettingRepository settingRepository) {
        this.settingRepository = settingRepository;
    }

    public boolean isBeenInitialized() {
        Optional<Setting> settingOptional = settingRepository.findByTypeIsAndNameEqualsIgnoreCase(Setting.TYPE.APP, "INIT");
        if(settingOptional.isPresent()) {
            Setting setting = settingOptional.get();
            return Constants.LIGHT.equalsIgnoreCase(setting.getValue());
        }
        return false;
    }

    public Setting save(Setting setting) {
        return settingRepository.save(setting);
    }

    public Setting saveAndFlush(Setting setting) {
        return settingRepository.saveAndFlush(setting);
    }

    @Transactional(rollbackOn = Exception.class)
    public void init() {
        String[] initSettingItem = Constants.INIT_SETTING_ITEM;
        for(String item : initSettingItem) {
            Setting setting = new Setting();
            String[] split = item.split(Constants.COLON);
            if(split.length != 3) {
                String msg = "App init setting exception, setting init item is " + item;
                LOGGER.error(msg, new RuntimeException(msg));
            }
            setting.setType(Setting.TYPE.valueOf(split[0]));
            setting.setName(split[1]);
            setting.setValue("TOKEN".equalsIgnoreCase(split[1])? MD5Util.md5(split[2]) :split[2]);
            settingRepository.save(setting);
        }
    }
}
