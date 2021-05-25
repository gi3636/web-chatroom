
//自己的资料
var user;
//全体在线用户
var onlineUsers;
//全体在线用户，但不包含自己
var onlineUserList;
var toUsers;//要send给谁
var flag=true;//默认情况


function showChat(name){
    flag=false;
    toUsers=name;
    //现在聊天框
    $(".chat").html("");

    $(".title-content").html("当前正与"+toUsers+"聊天");
    //从sessionStorage中获取历史信息
    var chatData = sessionStorage.getItem(toUsers);
    if (chatData != null){
        $(".chat").html(chatData);
    }
    refreshMessage();
};


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
            console.log(user);
            // $(".title-content").html("当前正与"+user["username"]+"聊天");
        },
        async:false //同步请求，只有上面好了才会接着下面
    });


    $.ajax({
        type:"GET",
        url:"/getOnlineUsers",
        success:function(data){
            let online=data;
            console.log("测试");
            console.log(online);
            // $(".title-content").html("当前正与"+user["username"]+"聊天");
        },
        async:false //同步请求，只有上面好了才会接着下面
    });

    //建立websocket连接
    //获取host解决后端获取httpSession的空指针异常
    let host=window.location.host;
    let ws=new WebSocket("ws://"+host+"/chat");

    //给ws绑定事件
    ws.onopen=function (evt) {
    }

    //接收到服务端推送的消息后触发
    ws.onmessage=function(evt){
        //获取服务端推送过来的消息
        let dataStr=evt.data;
        let res=JSON.parse(dataStr);
        console.log(res);

        //判断是否是系统消息
        if(res["system"]){
            //是系统消息
            console.log("系统消息");
            console.log(res);
            //更新用户名单

            onlineUsers=res["toUser"];
            arrRemove(user["username"],onlineUsers);

            if (flag){
                toUsers=onlineUsers;
            }
            console.log("onlineUser"+onlineUsers);
            console.log("onlineUserList"+onlineUserList);
            console.log("测试目前在线用户")
            console.log(onlineUsers);


            let time=formatDate(new Date(parseInt(res["sendTime"])));
            console.log("时间"+time);
            // 例子，比如需要这样的格式：yyyy-MM-dd hh:mm:ss
            // let date = new Date(parseInt(res["sendTime"]));
            //
            //
            // Y = date.getFullYear() + '-';
            // M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            // D = date.getDate() + ' ';
            // h = date.getHours() + ':';
            // // m = date.getMinutes() + ':';
            // m = date.getMinutes();
            // s = date.getSeconds();
            // console.log(Y+M+D+h+m+s);
            // let time=Y+M+D+h+m;

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
            $(".user-detail").html(userListStr);
            $(".chat").append(broadcastListStr);
        }else {
            let time=formatDate(new Date(parseInt(res["sendTime"])));
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

            console.log("要发送的用户："+toUsers);
            console.log("来自："+res["fromName"]);
            if (toUsers==res["fromName"]){
                //如果toUsers ==
                $(".chat").append(str);
            }
            let chatData=sessionStorage.getItem(res["fromName"]);
            if (chatData!=null){
                str=chatData+str;
            }
            sessionStorage.setItem(res["fromName"],str);
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
            let time = formatDate(new Date());
            console.log("发出时间："+time);
            //清空输入区的内容
            $("#textarea").val(null);
            console.log("发送的用户："+toUsers);

            let mess={
                toUsernames:toUsers,
                message: data,
                fromUser:user["username"],
                sendTime:time
            }
            let json={"toUsername":"test","message":data};
            let str="<li class=\"row\" id=\"time_message\"><p class=\"d-flex w-100 justify-content-center\">"+time+"</p></li> " +
                "<li class=\"row \" id=\"self_message\">\n" +
                "     <div class=\"row d-flex  justify-content-end col-sm-11\">\n" +
                "        <div class=\"row username w-100 justify-content-end\">"+user["username"]+"</div>\n" +
                "        <div class=\"row content \">"+data+"</div>\n" +
                "     </div>\n" +
                "     <img src=\"/show/"+user["username"]+"\" width=\"50px\" height=\"50px\">\n" +
                "  </li>";
            $(".chat").append(str);

            let chatData=sessionStorage.getItem(toUsers);
            if (chatData!=null){
                str=chatData+str;
            }
            sessionStorage.setItem(toUsers,str);
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

//格式日期
function formatDate(date) {
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    // m = date.getMinutes() + ':';
    m = date.getMinutes();
    s = date.getSeconds();
    console.log(Y+M+D+h+m+s);
    let result=Y+M+D+h+m;
    return result;
};