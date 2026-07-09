package org.example.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "space_member")
public class SpaceMember {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long spaceId;


    private Long userId;


    private String role;


    private LocalDateTime joinTime;



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getSpaceId() {
        return spaceId;
    }


    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }


    public Long getUserId() {
        return userId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


    public LocalDateTime getJoinTime() {
        return joinTime;
    }


    public void setJoinTime(LocalDateTime joinTime) {
        this.joinTime = joinTime;
    }

}