<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>FreeMarker</title>

    <link rel="stylesheet" href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"/>
    <script type="text/javascript" src="webjars/jquery/3.5.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.6.0/css/bootstrap.min.css"></script>
    <link rel="stylesheet" href="/fontawesome-free-5.11.2-web/css/all.css">
    <link rel="stylesheet" href="css/login.css">




</head>
<style>
    .result {
        position: absolute;
        top: 100%;
        left: 50%;
        margin-left: -7%;
        color: red;
        font-weight: bold;
        display: none;
    }
</style>

<body>
<div class="container-fluid  jumbotron">
    <div class="container">
        <div class="row  bg-white justify-content-center align-content-center">
            <div class="login-box col-sm-9  ">
                <div class="title">用户登入</div>
                <form method="post" action="" id="login_form">
                    <div class="form-group">
                        <i class="fa fa-user"></i>
                        <input id="username" class="form-control" type="text" name="username" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock"></i>
                        <input id="password" class="form-control" type="text" name="password" placeholder="密码">
                    </div>
                    <button type="button" id="login_btn" onclick="login()" class="btn btn-success col-sm-12">登入</button>
                </form>
                <div class="forget-password border-bottom-0 border-dark"><a href="#">忘记密码？</a></div>
                <div class="register" ><a href="/register">创建用户 </a><i class="fa fa-arrow-right"></i></div>
                <div class="result">验证失败！</div>
            </div>
        </div>
    </div>
</div>
</body>
<script>

    function login(){
        let username=$("#username").val();
        let password=$("#password").val();
        $.ajax({
            type:"POST",
            // dataType:"json",//服务器返回数据类型,不写就自动选择
            url:"/login",
            data:"username=" + username + "&password=" + password,
            success:function(result){
                if(result!=null){
                  console.log(JSON.stringify(result));
                    if (result['result']===true) {
                        window.location.href = "/chatroom";
                    }else{
                        alert("登入失败");
                        $("#username").val("");
                        $("#password").val("");
                        $(".result").css("display","block");
                    }

                }
            },
            error:function () {
                alert("异常");
                window.location.href="/login";
            }
        })
    }


</script>
</html>