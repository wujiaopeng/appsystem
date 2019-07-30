<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="common/head.jsp"%>
<span class="section">新增APP版本信息</span>
<div class="col-md-6 col-sm-6 col-xs-12">
  <div class="x_panel" style="width:1060px">
    <div class="x_title">
      <h2>历史版本列表 <i class="fa fa-user"></i><small>&nbsp;&nbsp;${devUserSession.devName}</small></h2>
      <div class="clearfix"></div>
    </div>
    <div class="x_content">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th width="22%">软件名称</th>
            <th width="8%">版本号</th>
            <th width="16%">版本大小(单位：M)</th>
            <th width="8%">发布状态</th>
            <th width="32%">APK文件下载</th>
            <th width="10%">最新更新时间</th>
          </tr>
        </thead>
        <tbody>
        	<c:forEach var="appVersion" items="${versionList }" varStatus="status">
				<tr>
					<td>${appVersion.appName }</td>
					<td>${appVersion.versionNo }</td>
					<td>${appVersion.versionSize }</td>
					<td>${appVersion.publishStatusName }</td>
					<td>${appVersion.apkFileName }</td>
					<td>
						<fmt:formatDate value="${appVersion.modifyDate }" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
			</c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

<div class="clearfix"></div>

<form id="myform" class="form-horizontal form-label-left" method="post"  enctype="multipart/form-data"
            action="${pageContext.request.contextPath }/dev/saveAppversion" novalidate>
  <span class="section">新增版本信息</span>
	
  <input type="hidden" name="appId" id="appId" value="${appId }">
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">版本号 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input class="form-control col-md-7 col-xs-12" id="versionNo"  name="versionNo" placeholder="请输入版本号" required="required" type="text">
    </div>
    <font class="message1" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">版本大小 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="number" id="versionSize" name="versionSize" required="required" placeholder="请输入版本大小，单位为Mb" data-validate-minmax="1,100" class="form-control col-md-7 col-xs-12">
    </div>
    <font class="message2" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">发布状态 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      	预发布
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">版本简介<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <textarea id="versionInfo" required="required" name="versionInfo"  placeholder="请输入本版本的相关信息，本信息作为版本的详细信息进行软件的介绍。"  class="form-control col-md-7 col-xs-12"></textarea>
    </div>
    <font class="message3" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">apk文件<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
     <input type="hidden" name="errorMsg" id="errorMsg" value="${errorMsg }">
      <input type="file" name="a_apkLocPath" required="required" data-validate-length-range="8,20" class="form-control col-md-7 col-xs-12">
    </div>
  </div>
  <div class="ln_solid"></div>
  <div class="form-group">
    <div class="col-md-6 col-md-offset-3">
    	<button id="send" type="submit" class="btn btn-success">保存</button>
      	<p id="back" class="btn btn-primary">返回</p>   
    </div>
  </div>
</form>
<div class="clearfix"></div>

<%@include file="common/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/localjs/appversionadd.js"></script>