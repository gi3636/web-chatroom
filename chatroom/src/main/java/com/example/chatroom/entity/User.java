package com.example.chatroom.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;



@EntityListeners(AuditingEntityListener.class)//更新实体类的时间
@Entity //表明这是一个持久化类
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键选择策略
    @Column(name = "user_id")//定义数据库的列名如果与字段名一样可以省略
    private Integer userId;

    @Column(name = "username",unique = true,nullable = false)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "avatar")
    private String avatar="/default.jpg";

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "group_chat_id"))
    @Column(name = "group_chat_list")
    private List<GroupChat> groupChatList=new ArrayList<>();



    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<GroupChat> getGroupChatList() {
        return groupChatList;
    }

    public void setGroupChatList(List<GroupChat> groupChatList) {
        this.groupChatList = groupChatList;
    }


    public User(Integer userId, String username, String avatar) {
        this.userId = userId;
        this.username = username;
        this.avatar = avatar;
    }
}
