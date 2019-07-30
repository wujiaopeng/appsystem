<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/head.jsp"%>
<form id="myform" class="form-horizontal form-label-left" method="post"  enctype="multipart/form-data"
            action="${pageContext.request.contextPath }/dev/saveModifyAppInfo" novalidate>
  <span class="section">修改APP基础信息 <i class="fa fa-user"></i><small>&nbsp;&nbsp;${devUserSession.devName}</small></span>
	<input type="hidden" id="id" name="id" value="${appinfo.id }" />
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">软件名称 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input class="form-control col-md-7 col-xs-12" id="softwareName" required="required"  value="${appinfo.softwareName }"  name="softwareName" placeholder="请输入软件名称" type="text">
    </div>
    <font class="message1" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">APK名称 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" id="APKName"   value="${appinfo.APKName }" readonly="readonly" placeholder="请输入APK名称" class="form-control col-md-7 col-xs-12">
    </div>
    <font class="message2" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">支持ROM<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" id="supportROM" name="supportROM" value="${appinfo.supportROM }" placeholder="请输入支持的ROM" class="form-control col-md-7 col-xs-12">
    </div>
    <font class="message3" color="red"></font>
  </div>
   <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">界面语言 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="text" id="interfaceLanguage" value="${appinfo.interfaceLanguage }" name="interfaceLanguage"  required="required"  placeholder="请输入软件支持的界面语言" class="optional form-control col-md-7 col-xs-12">
    </div>
    <font class="message4" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">软件大小 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="number" id="softwareSize" name="softwareSize" value="${appinfo.softwareSize }"   required="required"  placeholder="请输入软件大小，单位为Mb" data-validate-minmax="1,100" class="form-control col-md-7 col-xs-12">
    </div>
    <font class="message5" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">下载次数<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <input type="number" id="number" name="downloads" value="${appinfo.downloads }" placeholder="请输入下载次数" required="required"   data-validate-minmax="10,10000"  class="form-control col-md-7 col-xs-12">
    </div>
    <font class="message6" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="occupation">所属平台 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
    	<input  type="hidden" id="fid" value="${appinfo.flatformId }" />
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
    	<input  type="hidden" id="cl1" value="${appinfo.categoryLevel1 }" />
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
      <input  type="hidden" id="cl2" value="${appinfo.categoryLevel2 }" />
      <select name="categoryLevel2" id="categoryLevel2" class="optional form-control col-md-7 col-xs-12"></select>
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="telephone">三级分类 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
    	<input  type="hidden" id="cl3" value="${appinfo.categoryLevel3 }" />
       <select name="categoryLevel3" id="categoryLevel3" class="optional form-control col-md-7 col-xs-12"></select>
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态 <span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
    	<input type="hidden" id="status" name="status" value="${appinfo.status }">
      	<input type="text" value="${appinfo.statusName }"  readonly="readonly" placeholder="请输入APK名称" class="form-control col-md-7 col-xs-12">
    </div>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">应用简介<span class="required">*</span>
    </label>
    <div class="col-md-6 col-sm-6 col-xs-12">
      <textarea id="textarea" name="appInfo"  placeholder="请输入本软件的相关信息，本信息作为软件的详细信息进行软件的介绍。"  required="required"   class="form-control col-md-7 col-xs-12"> ${appinfo.appInfo }</textarea>
    </div>
    <font class="message9" color="red"></font>
  </div>
  <div class="item form-group">
    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">LOGO图片<span class="required">*</span>
    </label>
    <div id="logoFile" class="col-md-6 col-sm-6 col-xs-12">
    	<input type="hidden" id="logoPicPath" value="${appinfo.logoPicPath }">
    	<input type="file" id="uploadfile" name="a_logoPicPath" class="form-control col-md-7 col-xs-12" style="display:none;"/>
    </div>
    <font class="message10" color="red"></font>
  </div>
  <div class="ln_solid"></div>
  <div class="form-group">
    <div class="col-md-6 col-md-offset-3">
        <button id="send2" type="submit" class="btn btn-success" style="display:none;">保存并再次提交审核</button>
    	<button id="send" type="submit" class="btn btn-success">保存</button>
      	<p id="back" class="btn btn-primary">退出</p>   
    </div>
  </div>
</form>
<div class="clearfix"></div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/localjs/appinfomodify.js"></script>