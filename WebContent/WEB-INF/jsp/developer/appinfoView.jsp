<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="common/head.jsp"%>
<form id="myform" class="form-horizontal form-label-left" novalidate>
  <span class="section">查看APP信息 <i class="fa fa-user"></i><small>&nbsp;&nbsp;${devUserSession.devName}</small></span>
  <span class="section" style="font-size: 16px;">APP基础信息</span>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">软件名称 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input class="form-control col-md-7 col-xs-12" value="${appinfo.softwareName }" readonly="readonly" required="required" type="text">
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APK名称 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" value="${appinfo.APKName }" readonly="readonly" class="form-control col-md-7 col-xs-12">
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">支持ROM<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" value="${appinfo.supportROM }" readonly="readonly" class="form-control col-md-7 col-xs-12">
    </div>
  </div>
   <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">界面语言 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" value="${appinfo.interfaceLanguage }" readonly="readonly" class="optional form-control col-md-7 col-xs-12">
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">软件大小 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" value="${appinfo.softwareSize }"  readonly="readonly"  class="form-control col-md-7 col-xs-12">
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">下载次数<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" value="${appinfo.downloads }" readonly="readonly"  class="form-control col-md-7 col-xs-12">
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="occupation">所属平台 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
    	<input type="text" value="${appinfo.flatformName }" readonly="readonly"  class="form-control col-md-7 col-xs-12">
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="telephone">所属分类<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      	<input type="text" value="${appinfo.categoryLevel1Name }-->${appinfo.categoryLevel2Name}-->${appinfo.categoryLevel3Name}" readonly="readonly"  class="form-control col-md-7 col-xs-12">
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      	<input type="text" value="${appinfo.statusName }" readonly="readonly"  class="form-control col-md-7 col-xs-12">
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <textarea id="textarea" readonly="readonly" name="appInfo" class="form-control col-md-7 col-xs-12">
      	${appinfo.appInfo }
      </textarea>
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">LOGO图片<span class="required">*</span></label>
    <div class="col-md-6 col-sm-6 col-xs-12">
    	<img alt="" src="${appinfo.logoPicPath }" width="100px">
    </div>
  </div>
</form>
<div class="ln_solid"></div>
  
<div class="col-md-6 col-sm-6 col-xs-12">
  <div class="x_panel" style="width:1060px">
    <div class="x_title">
      <h4>历史版本列表 <i class="fa fa-user"></i><small>&nbsp;&nbsp;${devUserSession.devName}</small></h4>
      <div class="clearfix"></div>
    </div>
    <div class="x_content">
      <table class="table table-bordered">
        <thead style="font-size: 14px;">
          <tr>
            <th width="22%">软件名称</th>
            <th width="8%">版本号</th>
            <th width="16%">版本大小(单位：M)</th>
            <th width="8%">发布状态</th>
            <th width="32%">APK文件下载</th>
            <th width="10%">最新更新时间</th>
          </tr>
        </thead>
        <tbody  style="font-size: 12px;">
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
  <div class="form-group">
    <div class="col-md-6 col-md-offset-3">
      	<button type="submit" id="back" class="btn btn-primary">返回</button>   
    </div>
  </div>
<div class="clearfix"></div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/localjs/appinfoview.js"></script>