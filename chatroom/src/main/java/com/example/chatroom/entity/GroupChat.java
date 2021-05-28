package com.example.chatroom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_group_chat")
public class GroupChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键选择策略
    @Column(name = "group_chat_id")//定义数据库的列名如果与字段名一样可以省略
    private Integer groupChatId;

    @Column(name = "group_name")//定义数据库的列名如果与字段名一样可以省略
    private String groupName;

    @ManyToMany(mappedBy = "groupChatList",fetch = FetchType.EAGER)
    @Column(name = "group_chat_users")
    //@JoinTable(name = "user_group_chat",joinColumns = @JoinColumn(name = "group_chat_id"))
    //定义数据库的列名如果与字段名一样可以省略
    //1、关系维护端，负责多对多关系的绑定和解除
    //2、@JoinTable注解的name属性指定关联表的名字，joinColumns指定外键的名字，关联到关系维护端(User)
    //3、inverseJoinColumns指定外键的名字，要关联的关系被维护端(GroupChat)
    //4、其实可以不使用@JoinTable注解，默认生成的关联表名称为主表表名+下划线+从表表名，
    //即表名为user_authority
    //关联到主表的外键名：主表名+下划线+主表中的主键列名,即user_id
    //关联到从表的外键名：主表中用于关联的属性名+下划线+从表的主键列名,即authority_id
    //主表就是关系维护端对应的表，从表就是关系被维护端对应的表
    private List<User> groupChatUsers=new ArrayList<>();

    private String avatar="/default.jpg";

    public Integer getGroupChatId() {
        return groupChatId;
    }

    public void setGroupChatId(Integer groupChatId) {
        this.groupChatId = groupChatId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getGroupChatUsers() {
        return groupChatUsers;
    }

    public void setGroupChatUsers(List<User> groupChatUsers) {
        this.groupChatUsers = groupChatUsers;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
