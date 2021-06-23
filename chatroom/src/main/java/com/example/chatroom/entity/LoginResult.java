package com.example.chatroom.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)//更新实体类的时间
@Entity //表明这是一个持久化类
@Table(name = "tbl_login_result")
public class LoginResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键选择策略
    @Column(name = "login_result_id")//定义数据库的列名如果与字段名一样可以省略
    private Integer loginResultId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "result")
    private Boolean result;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "login_time")
    private Date loginTime;

    @Column(name = "login_message")
    private String loginMessage;

    @Column(name = "ip_addr")
    private String ipAddr;	//ip地址
    private String browser;	//使用浏览器

    public LoginResult(Integer userId, Boolean result, Date loginTime, String loginMessage, String ipAddr, String browser) {
        this.userId = userId;
        this.result = result;
        this.loginTime = loginTime;
        this.loginMessage = loginMessage;
        this.ipAddr = ipAddr;
        this.browser = browser;
    }
}
