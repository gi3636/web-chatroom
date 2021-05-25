# web-chatroom 网页版聊天室
## 1. 项目介绍
这是一个仿微信的项目，使用Maven进行项目管理，基于springboot框架开发的项目，mysql底层数据库，前端采用freemarker模板引擎，Bootstrap作为前端UI框架，集成了jpa、mybatis等框架。使用websocket。
## 2. 框架介绍

#### 前端
|  技术   | 版本  | 官网 | 备注 |
|  ----  | ----  | ---- | ---- |
| freemarker  | 2.3.31 | https://freemarker.apache.org/ |
| Bootstrap  | 4.6.0 | http://www.bootcss.com/ | 
| Jquery  | 3.5.1 | https://jquery.com/ |
| Bootstrap  | 4.6.0 | http://www.bootcss.com/ | 
| Font-Awesome  | 5.11.2 | https://fontawesome.com/ | 图标库 |

**后端**
|  技术   | 版本  | 官网 |
|  ----  | ----  | ---  |
| SpringBoot  | 2.3.31 | https://spring.io/projects/spring-boot |
| JPA  | 2.4.5 | https://projects.spring.io/spring-data-jpa |
| Mybatis  | 2.1.4 | http://www.mybatis.org/mybatis-3 | 

## 3. 部署流程

1. 把username和password换成自己数据库账密
>application.properties
```java

server.port=8888
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/test?autoReconnect=true&useSSL=false&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=123456

```

2. 我安装的mysql是5.x版本的，如果是8.0以上的版本，把这个删掉就行了
>pom.xml
```java

 <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
 </dependency>

```
浏览地址：http://localhost:8888/login

## 4. 参考资料
* java聊天室开源 https://web03.cn/blog/109
* WebSocket打造在线聊天室【完结】 https://www.bilibili.com/video/BV1r54y1D72U/?spm_id_from=333.788.recommend_more_video.0
* userforgitee / websocket-demo https://gitee.com/userforgitee/websocket-demo/tree/master
* 2天开发一个聊天室，真香 https://www.bilibili.com/video/BV1yp4y1z7HF/?spm_id_from=333.788.recommend_more_video.5


## 5. 目前做好的功能
1. 聊天功能
2. 图片上传功能

## 6. 以后要做的功能
1. 群聊和私聊的区分
2. 添加好友功能
3. 好友列表展示
4. emoji的输入
5. 文件上传
6. 图片上传
7. 设置功能

# 最后
这项目刚开始，所以还有很多Bug，接下来会慢慢完成的。
       



