package cn.liguohao.rss.subscriber.entity;

import javax.persistence.*;

@Table(name = "setting")
@Entity
public class Setting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value")
    private String value;

    @Enumerated
    @Column(name = "type", nullable = false)
    private TYPE type;

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 配置项的类型
     */
    public enum TYPE {
        /**
         * 应用
         */
        APP,
        /**
         * 邮件项标识
         */
        MAIL
        ,
        /**
         * 模块项表示，哪些模块是否启用
         */
        MODULE
    }

}