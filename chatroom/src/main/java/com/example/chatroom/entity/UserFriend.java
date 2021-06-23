package com.example.chatroom.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EntityListeners(AuditingEntityListener.class)//更新实体类的时间
@Entity //表明这是一个持久化类
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_user_friend")
public class UserFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键选择策略
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "friend_id")
    private Integer friendId;
}
