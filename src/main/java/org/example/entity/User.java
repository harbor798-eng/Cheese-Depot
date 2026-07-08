package org.example.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="user")
public class User {


    @Id
    @GeneratedValue(strategy    = GenerationType.IDENTITY)
    private Integer id;


    private String username;


    private String password;


    private String email;


    @Column(
            updatable = false
    )
    private LocalDateTime createTime;

    @PrePersist
    public void prePersist(){

        this.createTime = LocalDateTime.now();

    }

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
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


    public LocalDateTime getCreateTime() {
        return createTime;
    }


    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

}