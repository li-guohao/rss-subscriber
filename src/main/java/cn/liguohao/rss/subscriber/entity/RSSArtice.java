package cn.liguohao.rss.subscriber.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "rss_artice")
@Entity
public class RSSArtice {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "rsid")
    private Integer rsid;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "outline")
    private String outline;

    @Temporal(TemporalType.DATE)
    @Column(name = "release_time")
    private Date releaseTime;

    @Column(name = "magnetic_link")
    private String magneticLink;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMagneticLink() {
        return magneticLink;
    }

    public void setMagneticLink(String magneticLink) {
        this.magneticLink = magneticLink;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getOutline() {
        return outline;
    }

    public void setOutline(String outline) {
        this.outline = outline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRsid() {
        return rsid;
    }

    public void setRsid(Integer rsid) {
        this.rsid = rsid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}