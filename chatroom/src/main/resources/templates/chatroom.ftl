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
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
<#--    不用这个添加好友窗口跳不出来-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script type="text/javascript" src="webjars/jquery/3.5.1/dist/jquery.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/fontawesome-free-5.11.2-web/css/all.css">
    <link rel="stylesheet" href="css/chatroom.css">

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
                                <div class="list-group-item list-group-item-action ">
                                    <div class="row">
                                        <div class="contact-img col-sm-2">
                                            <img src="/img/WeChat Image_20210519080830.jpg" width="50px" height="50px" alt="">
                                        </div>
                                        <div class="col-sm-10">
                                            <div class="row d-flex w-100 justify-content-between">
                                                <div class="contact-title mb-1">标题测试2312432423434</div>
                                                <small class="received-message-time">3 days ago</small>
                                            </div>
                                            <div class="row ">
                                                <div class="recent-message">19rxWcjug44Xft1T1Ai11ptDZr94wEdRTz</div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="list-group-item list-group-item-action">
                                    <div class="row">
                                        <div class="contact-img col-sm-2">
                                            <img src="/img/WeChat Image_20210519080830.jpg" width="50px" height="50px" alt="">
                                        </div>
                                        <div class="col-sm-10">
                                            <div class="row d-flex w-100 justify-content-between">
                                                <div class="contact-title mb-1">标题测试2312432423434</div>
                                                <small class="received-message-time">3 days ago</small>
                                            </div>
                                            <div class="row ">
                                                <div class="recent-message">19rxWcjug44Xft1T1Ai11ptDZr94wEdRTz</div>
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
                        <div class="title-content  col-sm-10">标题</div>
                        <i class="fas fa-clipboard-list col-sm-2" id="click_side" onclick="showChatDetail()" value="0"></i>

                    </div>
                    <!-- 聊天内容 -->
                    <div class="row col-sm-12">
                        <div class="chat-content col-sm-12">
                            <ul class="chat">
                                <li class="row">
                                    <img src="/img/WeChat Image_20210519080830.jpg" width="50px" height="50px">
                                    <p id="item">fwefwefwefawefafeafwefawefawefwefawefawefawefawefawefwefawefawefawefwefwefwefwefwefefawefawfawfawefawfwefawwefw发生的附件会计法就看得见发电房11将无法就开始多了几分疯狂的叫法刻录机复健科违法进口量九分裤了巨额罚款了问卷啊风口浪尖复健科了王府井看了就付了款解放路卡荆防颗粒二级分开了二级分开了了我家附近儿科林锋吉
                                        了九分裤垃圾分类就风口浪尖我看了房间里卡缴费e
                                    </p>
                                </li>
                            </ul>
                            <!-- 聊天详情 -->
                            <div class="chat-detail">
                                <ul class="user-detail">
                                    <li>
                                        <img src="/img/WeChat Image_20210519080830.jpg" height="80%" width="80%">
                                        <p>用户名</p>
                                    </li>
                                    <li>
                                        <img src="/img/WeChat Image_20210519080830.jpg" height="80%" width="80%">
                                        <p>用户名</p>
                                    </li>
                                    <li>
                                        <img src="/img/WeChat Image_20210519080830.jpg" height="80%" width="80%">
                                        <p>用户名</p>
                                    </li>
                                    <li>
                                        <img src="/img/WeChat Image_20210519080830.jpg" height="80%" width="80%">
                                        <p>用户名fefwefawefwefawefwefawefawe</p>
                                    </li>
                                    <li>
                                        <img src="/img/WeChat Image_20210519080830.jpg" height="80%" width="80%">
                                        <p>用户名啊</p>
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
                            <textarea class="input" name="" id="textarea" cols="20" rows="2" placeholder="写点东西吧..."></textarea>
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
                $('.chat-detail').css("width", "44%");
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
</script>

</html>