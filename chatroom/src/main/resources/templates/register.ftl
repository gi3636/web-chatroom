<!doctype html>
<html lang="en">

<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="webjars/bootstrap/4.6.0/css/bootstrap.min.css"/>
    <script type="text/javascript" src="webjars/jquery/3.5.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/jquery/3.5.1/dist/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/4.6.0/css/bootstrap.min.css"></script>
    <script type="text/javascript" src="jquery-validation-1.19.3/dist/jquery.validate.js"></script>
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
                <div class="title">用户注册</div>
                <form method="post" id="register_form" action="">
                    <div class="form-group">
                        <i class="fa fa-user"></i>
                        <input id="username" class="form-control" type="text" name="username" placeholder="用户名">
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock"></i>
                        <input id="password" class="form-control" type="text" name="password" placeholder="密码">
                    </div>
                    <div class="form-group">
                        <i class="fa fa-lock"></i>
                        <input id="confirm_password" class="form-control" type="text" name="confirm_password" placeholder="确认密码">
                    </div>
                    <button type="button" class="btn btn-success col-sm-12" onclick="register()" >注册</button>
                </form>
                <div class="result">验证失败！</div>

            </div>
        </div>
    </div>

</div>
</body>

<script>

    function register(){
        let username=$("#username").val();
        let password=$("#password").val();
        let confirm_password=$("#confirm_password").val();
        $.ajax({
            type:"POST",
            // dataType:"json",//服务器返回数据类型,不写就自动选择
            url:"/register",
            data:"username=" + username + "&password=" + password + "&confirm_password="+ confirm_password,
            success:function(result){
                if(result!=null){
                    console.log(JSON.stringify(result));
                    if (result===true) {
                        window.location.href = "/login";
                        alert("注册成功");
                    }else{
                        alert("注册失败");
                        $("#username").val("");
                        $("#password").val("");
                        $("#confirm_password").val("");
                        $(".result").css("display","block");
                    }

                }
            },
            error:function () {
                alert("异常");
                window.location.href="/register";
            }
        })
    }
</script>
</html>