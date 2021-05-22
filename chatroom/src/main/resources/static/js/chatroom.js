
var user;
var onlineUsers;
var toUsers;//要send给谁

//不包含自己
var onlineUserList;
var isIncludeSelf=true;


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

    //建立websocket连接
    //获取host解决后端获取httpSession的空指针异常
    let host=window.location.host;
    let ws=new WebSocket("ws://"+host+"/chat");

    //给ws绑定事件
    ws.onopen=function (evt) {
        console.log(evt.data);
    }

    //接收到服务端推送的消息后触发
    ws.onmessage=function(evt){
        //获取服务端推送过来的消息
        let dataStr=evt.data;
        let res=JSON.parse(dataStr);

        //判断是否是系统消息
        if(res["system"]){
            //是系统消息
            console.log("系统消息");
            console.log(res);
            //更新用户名单
            let toUsers=res["toUser"];
            onlineUsers=toUsers;
            onlineUserList=arrRemove(user["username"],onlineUsers);
            console.log("测试目前在线用户")
            console.log(onlineUserList);

            // 例子，比如需要这样的格式：yyyy-MM-dd hh:mm:ss
            let date = new Date(parseInt(res["sendTime"]));
            Y = date.getFullYear() + '-';
            M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            D = date.getDate() + ' ';
            h = date.getHours() + ':';
            // m = date.getMinutes() + ':';
            m = date.getMinutes();
            s = date.getSeconds();
            console.log(Y+M+D+h+m+s);
            let time=Y+M+D+h+m;

            //1.好友列表的展示
            let userListStr="";
            //2.系统消息展示
            let broadcastListStr="";
            //初始化界面
            for (let name of toUsers)
            {
                userListStr += "<li onclick='showChat(\""+name+"\")'><img src=\"/img/WeChat Image_20210519080830.jpg\" height=\"80%\" width=\"80%\"> <p>"+name+"</p></li>";
            }
            broadcastListStr+="<li class=\"row\" id=\"time_message\"><p class=\"d-flex w-100 justify-content-center\">"+time+"</p></li>"
            broadcastListStr+="<li class=\"row\" id=\"system_message\"><p class=\"d-flex w-100 justify-content-center\">"+res["fromName"]+"已上线</p></li>"
            $(".user-detail").append(userListStr);
            $(".chat").append(broadcastListStr);

        }else {
            //不是系统消息
            //显示信息
            // var str = "<li class=\"row\"><img src=\"/img/WeChat Image_20210519080830.jpg\" width=\"50px\" height=\"50px\"><p id=\"item\">"+res.message+"</p></li>";
            let str="<li class=\"row\" id=\"received_message\">\n" +
                "   <img src=\"/img/WeChat Image_20210519080830.jpg\" width=\"50px\" height=\"50px\">\n" +
                "    <div class=\"row col-sm-11\">\n" +
                "       <div class=\"row username col-sm-12\">123123123</div>\n" +
                "              <div class=\"row content\">"+res.message+"</div>\n" +
                "          </div>\n" +
                "   </li>";
            $(".chat").append(str);
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
           let data=$("#textarea").val();

           //清空输入区的内容
           $("#textarea").val(null);
           console.log("发送的用户："+onlineUserList);
           let mess={
               toUsernames:onlineUserList,
               message: data,
               fromUser:user["username"]
           }
           let json={"toUsername":"test","message":data};
           let str=" <li class=\"row \" id=\"self_message\">\n" +
               "     <div class=\"row d-flex  justify-content-end col-sm-11\">\n" +
               "        <div class=\"row username w-100 justify-content-end\">123123123</div>\n" +
               "        <div class=\"row content \">"+data+"</div>\n" +
               "     </div>\n" +
               "     <img src=\"/img/WeChat Image_20210519080830.jpg\" width=\"50px\" height=\"50px\">\n" +
               "  </li>";
           $(".chat").append(str);
           refreshMessage();
           //发送数据
           ws.send(JSON.stringify(mess));
       }
    });

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
    }

    // 消息滚动条显示至底部
    function  refreshMessage(){
        $(".chat-content")[0].scrollTop=$(".chat-content")[0].scrollHeight;
    }
})