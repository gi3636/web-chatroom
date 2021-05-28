
//自己的资料
var user;
//全体在线用户
var onlineUsers;
//全体在线用户，但不包含自己
var onlineUserList;
var toUser=null;//要send给谁
//默认发送对象为在线的人
var flag=true;
var groupChatId=1;//群聊的ID，默认是全体群


function showChat(name){
    //表示对象不是群聊
    groupChatId=null;
    toUser=name;
    //现在聊天框
    $(".chat").html("");

    $(".title-content").html("当前正与"+toUser+"聊天");
    //从sessionStorage中获取历史信息
    var chatData = sessionStorage.getItem(toUser);
    if (chatData != null){
        $(".chat").html(chatData);
    }

    refreshMessage();
};


function showGroupChat(id,obj){
    toUser=null;
    groupChatId=id;
    //现在聊天框
    $(".chat").html("");

    let url="/getGroupChat/"+groupChatId;
    //设置点击效果
    $(".chat-history-list li").removeClass("active");
    $(obj).addClass("active");
    initialGroupChat(url);

}
function initialGroupChat(url){
    $(function () {
        $.ajax({
            type: "GET",
            url:url,
            success(data){
                let result=data;
                console.log("groupChat:"+result);
                //获取群聊的群名
                $(".title-content").html(result["groupName"]);
                //从sessionStorage中获取历史信息
                let chatData = sessionStorage.getItem(groupChatId);
                if (chatData != null){
                    $(".chat").html(chatData);
                }
                refreshMessage();
            },
            error(data){
                alert(data);
            },
            async: false
        })
    });
}


//用来移除自己
var arrRemove = function (content, arr) {
    if (!arr || arr.length == 0) {
        return ""
    }
    let flag = arr.indexOf(content)
    if (flag > -1) {
        arr.splice(flag, 1)
        return arr
    } else {
        console.log("未查找到该元素")
    }
};


$(function () {
    $.ajax({
        type:"GET",
        url:"/getUser",
        success:function(data){
            user=data;
            console.log(JSON.stringify(user));
        },
        async:false //同步请求，只有上面好了才会接着下面
    });




    //建立websocket连接
    //获取host解决后端获取httpSession的空指针异常
    let host=window.location.host;
    let ws=new WebSocket("ws://"+host+"/chat");

    //给ws绑定事件
    ws.onopen=function (evt) {
        let url="/getGroupChat/"+groupChatId;
        initialGroupChat(url);

    }

    //接收到服务端推送的消息后触发
    ws.onmessage=function(evt){
        //获取服务端推送过来的消息
        let dataStr=evt.data;
        let res=JSON.parse(dataStr);
        console.log(res);

        //判断是否是系统消息
        if(res["system"]){
            console.log("系统消息");
            //更新用户名单
            onlineUsers=res["toUser"];
            arrRemove(user["username"],onlineUsers);
            console.log("onlineUser"+onlineUsers);
            console.log("onlineUserList"+onlineUserList);
            console.log("测试目前在线用户")
            console.log(onlineUsers);

            let time=Common.formatTime(res["sendTime"],'yyyy-MM-dd HH:mm:ss');
            console.log("收到时间: "+time);

            //1.好友列表的展示
            let userListStr="";
            //2.系统消息展示
            let broadcastListStr="";
            //初始化界面
            for (let name of onlineUsers)
            {
                userListStr += "<li onclick='showChat(\""+name+"\")'><img src=\"show/"+name+"\" height=\"80%\" width=\"80%\"> <p>"+name+"</p></li>";
            }
            broadcastListStr+="<li class=\"row\" id=\"time_message\"><p class=\"d-flex w-100 justify-content-center\">"+time+"</p></li>"
            broadcastListStr+="<li class=\"row\" id=\"system_message\"><p class=\"d-flex w-100 justify-content-center\">"+res["fromName"]+" "+res["message"]+"</p></li>"
            //因为目前只有一个全体群
            if (groupChatId===res["groupChatId"]){
                $(".user-detail").html(userListStr);
                $(".chat").append(broadcastListStr);
            }

            let chatData=sessionStorage.getItem(res["groupChatId"]);
            if (chatData!=null){
                broadcastListStr=chatData+broadcastListStr;
            }
            sessionStorage.setItem(res["groupChatId"],broadcastListStr);
        }else {
            console.log("收到信息");
            let time=Common.formatTime(res["sendTime"],'yyyy-MM-dd HH:mm:ss');
            //不是系统消息
            //显示信息
            // var str = "<li class=\"row\"><img src=\"/img/WeChat Image_20210519080830.jpg\" width=\"50px\" height=\"50px\"><p id=\"item\">"+res.message+"</p></li>";
            let str="<li class=\"row\" id=\"time_message\"><p class=\"d-flex w-100 justify-content-center\">"+time+"</p></li>" +
                "<li class=\"row\" id=\"received_message\">\n" +
                "   <img src=\"show/"+res["fromName"]+"\" width=\"50px\" height=\"50px\">\n" +
                "    <div class=\"row col-sm-11\">\n" +
                "       <div class=\"row username col-sm-12\">"+res["fromName"]+"</div>\n" +
                "              <div class=\"row content\">"+res.message+"</div>\n" +
                "          </div>\n" +
                "   </li>";

            console.log("要发送的用户："+toUser);
            console.log("来自："+res["fromName"]);

            //收到的信息是私聊，但打开其他人的私聊界面
            if (res["groupChatId"]==null && toUser !=null){
                //私聊时才显示信息
                if (toUser==res["fromName"] ){
                    $(".chat").append(str);
                }
                let chatData=sessionStorage.getItem(res["fromName"]);
                if (chatData!=null){
                    str=chatData+str;
                }
                sessionStorage.setItem(res["fromName"],str);

            }else if(res["groupChatId"]==null && toUser ==null)//收到的信息是私聊,且目前打开的是群聊界面
            {
                let chatData=sessionStorage.getItem(res["fromName"]);
                if (chatData!=null){
                    str=chatData+str;
                }
                sessionStorage.setItem(res["fromName"],str);
            } else {
                //是群聊
                //现在打开的群聊是发送信息的群聊才显示信息
                if (res["groupChatId"]==groupChatId && toUser ==null){
                    $(".chat").append(str);
                }
                let chatData=sessionStorage.getItem(res["groupChatId"]);
                if (chatData!=null){
                    str=chatData+str;
                }
                sessionStorage.setItem(res["groupChatId"],str);
            }
        }
        refreshMessage();
    }


    ws.onclose=function () {
        //显示离线消息

    }






    //清空输入区的内容
    $("#textarea").val(null);
    $("#textarea").selectionStart = 1;  // 选中区域的左边界


    //textarea发送消息触发事件
    $("#textarea").keydown(function (e) {

        //按enter键
        if (e.keyCode===13){
            //获取输入的内容
            let data=$("#textarea").val().trim();
           // let time = formatDate(new Date());
            let time=Common.formatTime(new Date(),'yyyy-MM-dd HH:mm:ss');
            console.log("发出时间："+time);
            //清空输入区的内容
            $("#textarea").val(null);
            console.log("发送的用户："+toUser);

            let mess={
                toUsernames:toUser,
                message: data,
                fromUser:user["username"],
                sendTime:time,
                groupChatId:groupChatId,
            }

            let str="<li class=\"row\" id=\"time_message\"><p class=\"d-flex w-100 justify-content-center\">"+time+"</p></li> " +
                "<li class=\"row \" id=\"self_message\">\n" +
                "     <div class=\"row d-flex  justify-content-end col-sm-11\">\n" +
                "        <div class=\"row username w-100 justify-content-end\">"+user["username"]+"</div>\n" +
                "        <div class=\"row content \">"+data+"</div>\n" +
                "     </div>\n" +
                "     <img src=\"/show/"+user["username"]+"\" width=\"50px\" height=\"50px\">\n" +
                "  </li>";
            $(".chat").append(str);

            //如果是群聊
            if (groupChatId!=null && toUser==null){
                let chatData=sessionStorage.getItem(groupChatId);
                if (chatData!=null){
                    str=chatData+str;
                }
                sessionStorage.setItem(groupChatId,str);
            }else {
                //如果不是群聊
                let chatData=sessionStorage.getItem(toUser);
                if (chatData!=null){
                    str=chatData+str;
                }
                sessionStorage.setItem(toUser,str);
            }

            console.log("要发送的数据："+JSON.stringify(mess))
            //发送数据
            ws.send(JSON.stringify(mess));
            refreshMessage();
        }
    });





})
// 消息滚动条显示至底部
function  refreshMessage(){
    $(".chat-content")[0].scrollTop=$(".chat-content")[0].scrollHeight;
};
