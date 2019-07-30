<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\12\12 0012
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎登录APP信息管理系统</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath}/statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath}/statics/css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath}/statics/css/custom.min.css" rel="stylesheet">
</head>
<body class="login">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form action="${pageContext.request.contextPath}/backend/dologin" method="post">
                    <h1>后台管理系统</h1>
                    <div>
                        <input type="text" class="form-control" placeholder="Username" required="" name="userCode"/>
                    </div>
                    <div>
                        <input type="password" class="form-control" placeholder="Password" required=""  name="userPassword"/>
                    </div>
                    <span style="color:red">${error}</span>
                    <div>
                        <button type="submit" class="btn btn-success" >登录</button>
                        <button type="reset" class="btn btn-default" >重置</button>
                    </div>
                    <div class="clearfix"></div>
                    <div class="separator">
                        <div class="clearfix"></div>
                        <br />
                        <div>
                            <p>©2018 All Rights Reserved. cwx</p>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>
