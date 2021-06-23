package com.example.chatroom.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_server_to_browser_message")
/**
 * 服务器给浏览器发的消息
 */
public class ServerToBrowserMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键选择策略
    @Column(name = "server_to_browser_message_id")//定义数据库的列名如果与字段名一样可以省略
    private Integer serverToBrowserMessageId;

    //发送用户
    @Column(name = "from_user_id")
    private Integer fromUserId;

    //收到用户
    @Column(name = "to_user_id")
    private Integer toUserId;

    //判断是哪个群收到的
    @Column(name="group_chat_id")
    private Integer groupChatId;

    //0为系统消息 ; 1为私聊信息 ; 2为群聊信息 ; 3为图片信息 ; 4为文件
    private Integer type;

    @Column(name = "is_system")
    private Boolean isSystem;

    @Column(name = "is_private_message")
    private Boolean isPrivateMessage;

    private String message;

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "send_time")
    private Date sendTime;


    public ServerToBrowserMessage(Integer fromUserId, Integer toUserId, Integer type, String message, Date sendTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.type = type;
        this.message = message;
        this.sendTime = sendTime;
    }


    public ServerToBrowserMessage(Integer fromUserId, Integer toUserId, Integer groupChatId, Integer type, String message, Date sendTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.groupChatId = groupChatId;
        this.type = type;
        this.message = message;
        this.sendTime = sendTime;
    }

    //群聊信息构造器
    public ServerToBrowserMessage(Integer fromUserId, Integer toUserId, Integer groupChatId, Integer type, Boolean isSystem, Boolean isPrivateMessage, String message, Date sendTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.groupChatId = groupChatId;
        this.type = type;
        this.isSystem = isSystem;
        this.isPrivateMessage = isPrivateMessage;
        this.message = message;
        this.sendTime = sendTime;
    }

    //私人信息构造器
    public ServerToBrowserMessage(Integer fromUserId, Integer toUserId, Integer type, Boolean isSystem, Boolean isPrivateMessage, String message, Date sendTime) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
        this.type = type;
        this.isSystem = isSystem;
        this.isPrivateMessage = isPrivateMessage;
        this.message = message;
        this.sendTime = sendTime;
    }


}
