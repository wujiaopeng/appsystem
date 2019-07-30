<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/head.jsp"%>
<form id="myform" class="form-horizontal form-label-left" method="post"  enctype="multipart/form-data"
            action="${pageContext.request.contextPath }/dev/saveAppInfo" novalidate>
  <span class="section">新增APP基础信息 <i class="fa fa-user"></i><small>&nbsp;&nbsp;${devUserSession.devName}</small></span>

  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">软件名称 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input class="form-control col-md-7 col-xs-12" id="softwareName"  name="softwareName" placeholder="请输入软件名称" required="required" type="text">
    </div>
    <font class="message1" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APK名称 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" id="APKName" name="APKName" required="required" placeholder="请输入APK名称" class="form-control col-md-7 col-xs-12">
    </div>
    <font class="message2" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">支持ROM<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" id="supportROM" name="supportROM" placeholder="请输入支持的ROM" required="required" class="form-control col-md-7 col-xs-12">
    </div>
    <font class="message3" color="red"></font>
  </div>
   <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">界面语言 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" id="interfaceLanguage" name="interfaceLanguage" placeholder="请输入软件支持的界面语言" class="optional form-control col-md-7 col-xs-12">
    </div>
    <font class="message4" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">软件大小 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="number" id="softwareSize" name="softwareSize" required="required" placeholder="请输入软件大小，单位为Mb" data-validate-minmax="1,100" class="form-control col-md-7 col-xs-12">
    </div>
    <font class="message5" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">下载次数<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="number" id="downloads" name="downloads" required="required" placeholder="请输入下载次数"  data-validate-minmax="10,10000"  class="form-control col-md-7 col-xs-12">
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="occupation">所属平台 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
    	<select id="flatformId" name="flatformId" class="optional form-control col-md-7 col-xs-12">
    		<option>--请选择--</option>
    	</select>
    </div>
    <font class="message7" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="telephone">一级分类 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <select name="categoryLevel1" id="categoryLevel1" class="optional form-control col-md-7 col-xs-12">
      		<option>--请选择--</option>
      </select>
    </div>
    <font class="message8" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="telephone">二级分类 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <select name="categoryLevel2" id="categoryLevel2" class="optional form-control col-md-7 col-xs-12"></select>
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="telephone">三级分类 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
       <select name="categoryLevel3" id="categoryLevel3" class="optional form-control col-md-7 col-xs-12"></select>
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      	待审核
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <textarea id="textarea" required="required" name="appInfo"  placeholder="请输入本软件的相关信息，本信息作为软件的详细信息进行软件的介绍。"  class="form-control col-md-7 col-xs-12"></textarea>
    </div>
    <font class="message9" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">LOGO图片<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
     <input type="hidden" name="errorMsg" id="errorMsg" value="${errorMsg }">
      <input type="file" name="a_logoPicPath" required="required" data-validate-length-range="8,20" class="form-control col-md-7 col-xs-12">
    </div>
    <font class="message10" color="red"></font>
  </div>
  <div class="ln_solid"></div>
  <div class="form-group">
    <div class="col-md-6 col-md-offset-3">
    	<button id="send" type="submit" class="btn btn-success">保存</button>
      	<p id="back" class="btn btn-primary">退出</p>   
    </div>
  </div>
</form>
<div class="clearfix"></div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/localjs/appinfoadd.js"></script>