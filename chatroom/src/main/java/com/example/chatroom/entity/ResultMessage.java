package com.example.chatroom.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 服务器给浏览器发的消息
 */
public class ResultMessage {
    private boolean isSystem;
    private String fromName;
    //private String toName;
    private Object toUser;
    private String message;//如果是系统消息是数组
    private Date sendTime;
}
