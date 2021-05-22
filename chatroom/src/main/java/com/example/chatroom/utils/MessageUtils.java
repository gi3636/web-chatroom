package com.example.chatroom.utils;

import com.example.chatroom.entity.ResultMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

//封装发送的消息内容,返回json格式
public class MessageUtils {
    public static String getMessage(boolean isSystemMessage, String fromName, String message, Date time,Object toUser){
        try{
            ResultMessage resultMessage = new ResultMessage();
            resultMessage.setSystem(isSystemMessage);
            resultMessage.setMessage(message);
            resultMessage.setToUser(toUser);
            resultMessage.setSendTime(time);
            if(fromName != null){
                resultMessage.setFromName(fromName);
            }
//            if(toName !=null ){
//                resultMessage.setToName(toName);
//            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(resultMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
}