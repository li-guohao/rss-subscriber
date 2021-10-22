package cn.liguohao.rss.subscriber.dao;

import cn.liguohao.rss.subscriber.entity.RSSArtice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RSSArticeRepository extends JpaRepository<RSSArtice, Long> {
}