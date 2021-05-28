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

    @Column(name = "is_sytem")
    private boolean isSystem;
    @Column(name = "group_chat_id")
    private Integer groupChatId;
    @Column(name = "from_name")
    private String fromName;

    @ElementCollection
    @Column(name = "to_user")
    private List<String> toUser;

    private String message;

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "send_time")
    private Date sendTime;

    public ServerToBrowserMessage(boolean isSystem, Integer groupChatId, String fromName,  List<String> toUser, String message, Date sendTime) {
        this.isSystem = isSystem;
        this.groupChatId = groupChatId;
        this.fromName = fromName;
        this.toUser = toUser;
        this.message = message;
        this.sendTime = sendTime;
    }
}
