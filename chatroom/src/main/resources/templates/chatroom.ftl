<!DOCTYPE html>
<html lang="en">
<!--
 * @Author       : fenggi123
 * @Date         : 2021-04-28 14:37:08
 * @LastEditors  : fenggi123
 * @LastEditTime : 2021-05-09 10:41:08
 * @FilePath     : \前端\bootstrap\homework\1.html
 * @Description  :
 * @Version      : 1.0
-->

<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Bootstrap CSS -->
<#--    不用这个添加好友窗口跳不出来-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script type="text/javascript" src="webjars/jquery/3.5.1/dist/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/fontawesome-free-5.11.2-web/css/all.css">
    <link rel="stylesheet" href="css/chatroom.css">
    <script src='/js/chatroom.js'></script>

</head>

<body>
<div class="container-fluid  jumbotron">
    <div class="container">
        <div class="row">
            <div class="chatroom row col-sm-12">
                <!-- 第一个列 功能表 -->
                <div class="option-list">
                    <div class="row">
                        <div class="avatar">
                            <img src="/img/WeChat Image_20210519080830.jpg" width="100%" height="100%">
                        </div>
                    </div>
                    <div class="row">
                        <div class="chat-option row">
                            <i class="fas fa-comment"></i>
                        </div>
                    </div>
                    <div class="row">
                        <div class="contact-option row">
                            <i class="fas fa-users"></i>
                        </div>
                    </div>
                    <div class="footer">
                        <div class="row">
                            <div class="setting-option">
                                <i class="fas fa-list-ul"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- 第二个列 聊天列表 -->
                <div class="chat-list col-sm-3">
                    <!-- 搜索框和按钮 -->
                    <div class="row">
                        <div class="search-bar">
                            <input type="text"><i class="fa fa-search"></i>
                        </div>
                        <button type="button" class="btn-add-friend " data-toggle="modal" aria-label="Close" data-target="#exampleModalCenter">
                            <i class="fa fa-plus"></i>
                        </button>
                    </div>
                    <!-- 中部 -->
                    <div class="row">
                        <div class="contact-list col-sm-12">
                            <!-- 聊天列表 -->
                            <div class="list-group">
                                <div class="list-group-item list-group-item-action active">
                                    <div class="row">
                                        <div class="contact-img col-sm-2">
                                            <img src="/img/WeChat%20Image_20210519080830.jpg" width="50px" height="50px" alt="">
                                        </div>
                                        <div class="col-sm-10">
                                            <div class="row d-flex w-100 justify-content-between">
                                                <div class="contact-title mb-1">标题测试2312432423434</div>
                                                <small class="received-message-time">3 days ago</small>
                                            </div>
                                            <div class="row ">
                                                <div class="recent-message">1232345234343431232324343434</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>



                <!-- 第三个列 聊天窗口 -->
                <div class="chat-frame col-sm-8">
                    <!-- 标题和按钮 -->
                    <div class="row  chatroom-title">
                        <#--标题-->
                        <div class="title-content  col-sm-10">标题</div>
                        <#--按钮-->
                        <i class="fas fa-clipboard-list col-sm-2" id="click_side" onclick="showChatDetail()" value="0"></i>

                    </div>
                    <!-- 聊天内容 -->
                    <div class="row col-sm-12">
                        <div class="chat-content col-sm-12">
                            <#--聊天区-->
                            <ul class="chat">
                                <li class="row" id="received_message">
                                    <img src="/img/WeChat Image_20210519080830.jpg" width="50px" height="50px">
                                    <div class="row col-sm-11">
                                        <div class="row username col-sm-12">123123123</div>
                                        <div class="row content">fwefwefwefawefafeafwefawefawefwefawefawefawefawfawefw</div>
                                    </div>
                                </li>
                                <li class="row " id="self_message">
                                    <div class="row d-flex  justify-content-end col-sm-11">
                                        <div class="row username w-100 justify-content-end">123123123</div>
                                        <div class="row content ">fwefwefwefawefafeafwefawefawefwefawefawefawefawfawefw</div>
                                    </div>
                                    <img src="/img/WeChat Image_20210519080830.jpg" width="50px" height="50px">
                                </li>
                                <li class="row" id="system_message">
                                    <p class="d-flex w-100 justify-content-center"> 某某某已上线</p>
                                </li>
                                <li class="row" id="time_message">
                                    <p class="d-flex w-100 justify-content-center"> 15:49</p>
                                </li>
                                <li class="row" id="system_message">
                                    <p class="d-flex w-100 justify-content-center"> 某某某已上线</p>
                                </li>

                            </ul>
                            <!-- 聊天详情 -->
                            <div class="chat-detail">
                                <ul class="user-detail">
                                    <li onclick='showChat("test")'>
                                        <img src="/img/WeChat Image_20210519080830.jpg" height="80%" width="80%">
                                        <p>用户名</p>
                                    </li>
                                    <li>
                                        <img src="/img/WeChat Image_20210519080830.jpg" height="80%" width="80%">
                                        <p>用户名fefwefawefwefawefwefawefawe</p>
                                    </li>

                                </ul>

                            </div>

                        </div>
                    </div>
                    <!-- 输入框 -->
                    <div class="row col-sm-12">
                        <div class="input-frame col-sm-12">
                            <div class="row function-list">
                                <i class="far fa-laugh"></i>
                                <i class="fas fa-image"></i>
                                <i class="far fa-file"></i>
                            </div>
                            <textarea class="input" name="" id="textarea" cols="20" rows="1" placeholder="写点东西吧..."></textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 查询好友界面 -->
<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalCenterTitle">添加好友</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-fluid">
                    <div class="row">
                        <div class="friend-list list-group col-sm-12">
                            <div class="list-group-item ">
                                <div class="row">
                                    <div class="contact-img col-sm-2">
                                        <img src="/img/WeChat Image_20210519080830.jpg" width="50px" height="50px" alt="">
                                    </div>
                                    <p class="col-sm-8">
                                        用户名
                                    </p>
                                    <div class="col-sm-2">
                                        <button class="btn-add-friend"><i class="fa fa-plus"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>

        </div>
    </div>
</div>

</body>

<script>


    function showChatDetail() {
        var isTrue = parseInt($('#click_side').attr('value'));
        $(function() {
            if (isTrue === 0) {
                $('.chat-detail').css("width", "18%");
                $('.user-detail>li').css("width", "80px");
                $('#click_side').attr('value', 1);
            } else {
                $('.user-detail>li').css("width", "0px");
                $('.chat-detail').css("width", "0%");
                $('#click_side').attr('value', 0);
            }
            return false;
        });
    }
    function showChat(name){
        toUsers=name;
        //现在聊天框
        $(".chat").html("");
        $(".chat").css("visibility","visible");
        $(".title-content").html("当前正与"+toUsers+"聊天");
        // //从sessionStorage中获取历史信息
        // var chatData = sessionStorage.getItem(toName);
        // if (chatData != null){
        //     $("#content").html(chatData);
        // }
    }




</script>

</html>