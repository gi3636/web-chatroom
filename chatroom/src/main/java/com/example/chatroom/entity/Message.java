package com.example.chatroom.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 浏览器给服务端发的消息
 */
public class Message {
    private List<String> toUsernames;
    private String message;
    private String fromUser;
    private Date sendTime;
}
