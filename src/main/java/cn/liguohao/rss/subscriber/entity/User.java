package cn.liguohao.rss.subscriber.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    /**
     * 说明：密码不存储明文，只存储MD5加密后的，默认为123456，只能通过手机验证码进行更新
     */
    @Column(name = "password")
    private String password;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "email", unique = true)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}