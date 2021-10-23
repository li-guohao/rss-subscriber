package cn.liguohao.rss.subscriber.dao;

import cn.liguohao.rss.subscriber.entity.RSSSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RSSSubscribeRepository extends JpaRepository<RSSSubscribe, Long> {
    boolean existsByAddressEqualsIgnoreCase(String address);

    List<RSSSubscribe> findByStatusIs(RSSSubscribe.Status status);
}