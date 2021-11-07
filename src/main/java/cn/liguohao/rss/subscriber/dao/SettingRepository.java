package cn.liguohao.rss.subscriber.dao;

import cn.liguohao.rss.subscriber.entity.RSSArtice;
import cn.liguohao.rss.subscriber.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author liguohao
 */
public interface SettingRepository extends JpaRepository<Setting, Long> {
    Optional<Setting> findByTypeIsAndNameEqualsIgnoreCase(Setting.TYPE type, String name);



}