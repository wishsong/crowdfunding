<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/6/24
  Time: 下午 3:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/css/login.css">
    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.htm" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form id="loginForm"  method="POST" action="${APP_PATH}/doLogin.do"  class="form-signin" role="form">

        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback" >
            <input type="text" class="form-control" id="floginacct" name="loginacct" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" class="form-control" id="fuserpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select id="ftype" class="form-control" name="type">
                <option value="member" selected>会员</option>
                <option value="user">管理</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                <input id="rememberme" type="checkbox" value="1"> 记住我2周
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="reg.html">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="login()"> 登录</a>
    </form>

    <div align="center" class="col-md-4 col-md-offset-4 alert alert-warning alert-dismissible msgTip" role="alert" id="in" type="hidden">
<%--        <a type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></a>--%>
    您尚未登录！
    <strong >${requestScope.exception.message}</strong>
    </div>

</div>
<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/jquery/layer/layer.js"></script>
<script>

    $(function(){
        $("#in").hide();
    });

    function login() {

        var floginacct = $("#floginacct");
        var fuserpswd = $("#fuserpswd");
        var ftype = $("#ftype");
        var badMag = $("#badMag");


        if($.trim(floginacct.val())==""){

            // floginacct.focus();
            floginacct.val("");
            floginacct.css("borderColor","red");
            $("#in").show();
            $("#in").html("<strong id=\"inn\"></strong>");
            $("#inn").text("用户名不能为空!");
            return false;
        }

        $.ajax({
                type:"POST",
                data:{"loginacct" : floginacct.val(),"userpswd":fuserpswd.val(),"type":ftype.val()},
                url:"${APP_PATH}/doLogin.do",

                beforeSend: function(){
                    return true;
                },
                success : function(result){
                    if(result.success){
                      window.location.href = "${APP_PATH}/main.htm";
                    }else{
                        alert("not ok");
                    }
                },
                error : function(){
                    alert("error");
                },

            });
    }

</script>
</body>
</html>