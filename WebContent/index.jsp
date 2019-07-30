<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>入口</title>
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
    <div class="login_wrapper">
      <div class="animate form login_form">
        <section class="login_content">
          <form>
            <h1>APP信息管理平台</h1>
              <div>
                <a href="${pageContext.request.contextPath}/dev/login"><h3>开发者平台入口 </h3></a>
                <a href="${pageContext.request.contextPath}/backend/login"><h3>管理平台入口 </h3></a>
                <p>©2018 All Rights Reserved. cwx</p>
              </div>
          </form>
        </section>
      </div>
    </div>
  </div>
  </body>
</html>
