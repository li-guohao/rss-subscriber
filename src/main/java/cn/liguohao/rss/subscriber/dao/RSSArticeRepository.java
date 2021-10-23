package cn.liguohao.rss.subscriber.dao;

import cn.liguohao.rss.subscriber.entity.RSSArtice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RSSArticeRepository extends JpaRepository<RSSArtice, Long> {
    boolean existsByTitleEqualsAndReleaseTimeIs(String title, Date releaseTime);

    List<RSSArtice> findByTitleContains(String title);

    List<RSSArtice> findByRsidOrderByReleaseTimeDescIdDesc(Long rsid);



}