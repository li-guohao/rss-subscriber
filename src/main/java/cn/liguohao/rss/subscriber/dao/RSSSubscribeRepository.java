package cn.liguohao.rss.subscriber.dao;

import cn.liguohao.rss.subscriber.entity.RSSSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RSSSubscribeRepository extends JpaRepository<RSSSubscribe, Long> {
    boolean existsByAddressEqualsIgnoreCase(String address);
}