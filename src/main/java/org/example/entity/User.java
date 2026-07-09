package org.example.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    /**
     * 登录用户名
     */
    @Column(nullable = false, unique = true)
    private String username;



    /**
     * BCrypt加密密码
     */
    private String password;



    /**
     * 邮箱
     */
    private String email;



    /**
     * 手机号
     */
    private String phone;



    /**
     * 昵称
     */
    private String nickname;



    /**
     * 头像地址
     */
    private String avatar;



    /**
     * 账号状态
     * 1 正常
     * 0 禁用
     */
    private Integer status;



    /**
     * 账号类型
     * NORMAL 普通用户
     * ADMIN 管理员
     */
    private String accountType;



    private LocalDateTime createTime;


    private LocalDateTime updateTime;


    private LocalDateTime lastLoginTime;



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getAvatar() {
        return avatar;
    }


    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getAccountType() {
        return accountType;
    }


    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }


    public LocalDateTime getCreateTime() {
        return createTime;
    }


    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }


    public LocalDateTime getUpdateTime() {
        return updateTime;
    }


    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }


    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }


    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

}