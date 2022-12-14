package cn.liguohao.rss.subscriber.entity;

import cn.liguohao.rss.subscriber.rss.RSSSit;
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

    @Column(name = "outline")
    private String outline;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "release_time")
    private Date releaseTime;

    @Column(name = "author")
    private String author;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "rsid", nullable = false)
    private Long rsid;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "page_url")
    private String pageUrl;

    @Column(name = "content_length")
    private Long contentLength;

    @Enumerated
    @Column(name = "rss_sit", nullable = false)
    private RSSSit rssSit;

    public RSSSit getRssSit() {
        return rssSit;
    }

    public void setRssSit(RSSSit rssSit) {
        this.rssSit = rssSit;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getRsid() {
        return rsid;
    }

    public void setRsid(Long rsid) {
        this.rsid = rsid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}