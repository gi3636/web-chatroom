package com.example.chatroom.entity;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_browser_to_server_message")
/**
 * 浏览器给服务端发的消息
 */
public class BrowserToServerMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键选择策略
    @Column(name = "browser_to_server_message_id")//定义数据库的列名如果与字段名一样可以省略
    private Integer browserToServerMessageId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "to_user_names")
    private List<String> toUsernames;

    private String message;
    @Column(name = "from_user")
    private String fromUser;

    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "send_time")
    private Date sendTime;

    @Column(name = "message_type")
    private String messageType;

    @Column(name = "group_chat_id")
    private Integer groupChatId;


}
