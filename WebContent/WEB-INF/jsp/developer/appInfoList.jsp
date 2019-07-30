<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/head.jsp"%>
<form class="form-horizontal form-label-left" method="post"
            action="${pageContext.request.contextPath }/dev/appInfoList" novalidate>
	<span class="section">APP信息管理维护 <i class="fa fa-user"></i><small>&nbsp;&nbsp;${devUserSession.devName}</small></span>
	<div class="x_content">
	<table style="width:1000px; margin-bottom: 20px; margin-top:10px">
		<tr style="height:50px;">
			<td width="10%" align="center">软件名称</td>
			<td width="12%" align="center">
				<input type="text" name="softwareName" value="${queryappinfo.softwareName }" class="form-control input-sm" placeholder="" aria-controls="datatable">
			</td>
			<td width="10%" align="center">APP状态</td>
			<td width="12%">
				<input type="hidden" id="sta" value="${queryappinfo.status }">
				<select id="status" name="status" class="form-control input-sm">
					<option>--请选择--</option>
				</select>
			</td>
			<td width="10%" align="center">所属平台</td>
			<td width="12%">
				<input type="hidden" id="fid" value="${queryappinfo.flatformId }">
				<select id="flatformId" name="flatformId" class="form-control input-sm">
					<option>--请选择--</option>
				</select>
			</td>
		</tr>
		<tr style="height:50px;">
			<td width="10%" align="center">第一分类</td>
			<td width="12%">
				<input type="hidden" id="cl1" value="${queryappinfo.categoryLevel1 }">
				<select id="categoryLevel1" name="categoryLevel1" class="form-control input-sm">
					<option>--请选择--</option>
				</select>
			</td>
			<td width="10%" align="center">第二分类</td>
			<td width="12%">
				<input type="hidden" id="cl2" value="${queryappinfo.categoryLevel2 }">
				<select id="categoryLevel2" name="categoryLevel2" class="form-control input-sm"></select>
			</td>
			<td width="10%" align="center">第三分类</td>
			<td width="12%">
				<input type="hidden" id="cl3" value="${queryappinfo.categoryLevel3 }">
				<select id="categoryLevel3" name="categoryLevel3" class="form-control input-sm"></select>
			</td>
		</tr>
	</table>
      	<p style="margin-left:50px;margin-bottom: 30px"><button type="submit" style="width:80px;" class="btn btn-primary">查询</button></p>   
 </div>
 <input type="hidden" name="pageIndex" value="1"/>			
</form>
<div class="clearfix"></div>
<div class="x_title">
  <button id="addAppInfo" type="submit" class="btn btn-success">新增APP基础信息</button>              
  <div class="clearfix"></div>
</div>
<div class="x_content">
	<table id="datatable" class="table table-striped table-bordered" style="width:1060px">
	   <thead style="font-size: 15px;">
	     <tr>
	       <th width="13%">软件名称</th>
	       <th width="17%">APK名称</th>
	       <th width="10%">软件大小(单位：M)</th>
	       <th width="5%">所属平台</th>
	       <th width="24%">所属分类(一级分类、二级分类、三级分类)</th>
	       <th width="6%">状态</th>
	       <th width="4%">下载次数</th>
	       <th width="7%">最新版本号</th>
	       <th width="20%">操作</th>
	     </tr>
	   </thead>
	   <tbody style="font-size:14px">
	     <c:forEach var="appinfo" items="${appinfoList }" varStatus="status">
				<tr>
					<td>${appinfo.softwareName }</td>
					<td>${appinfo.APKName }</td>
					<td>${appinfo.softwareSize }</td>
					<td>${appinfo.flatformName }</td>
					<td>${appinfo.categoryLevel1Name }->${appinfo.categoryLevel2Name }->${appinfo.categoryLevel3Name }</td>
					<td id="appInfoStatus${appinfo.id }">${appinfo.statusName }</td>
					<td>${appinfo.downloads }</td>
					<td>${appinfo.versionNo }</td>
					<td>
			        	<div class="btn-group" >
			               <button type="button" class="btn btn-danger">点击操作</button>
			               <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
			                 <span class="sr-only"></span>
			                 <span class="caret" style="height:10px;margin-top: 10px"></span>
			               </button>
			               <ul class="dropdown-menu" role="menu">
			               	 <c:if test="${appinfo.status==2 || appinfo.status==5 }">
			               	 	<li title="上架"><a class="saleSwichOpen" href="javascript:;" appinfoid="${appinfo.id }"
			               	 		status="${appinfo.status }" saleSwitch="open" appsoftwarename="${appinfo.softwareName }" >上架</a></li>
			               	 </c:if>
			               	 <c:if test="${appinfo.status==4 }">
			               	 	<li title="下架"><a class="saleSwichClose" href="javascript:;" status="${appinfo.status }" 
			               	 	saleSwitch="close" appinfoid="${appinfo.id }"  appsoftwarename="${appinfo.softwareName }">下架</a></li>
			               	 </c:if>
			                 <li data-toggle="tooltip" title="新增版本"><a class="addVersion" appinfoid="${appinfo.id }" href="#">新增版本</a></li>
			                 <li>
			                 	<a class="modifyVersion" href="javascript:;" 
				                 	status="${appinfo.status }" versionid="${appinfo.versionId }" 
				                 	statusname="${appinfo.statusName }" appinfoid="${appinfo.id }">修改版本</a>
			                 </li>
			                 <li>
			                     <a class="modifyAppInfo" href="javascript:;" status="${appinfo.status }" 
			                     	appinfoid="${appinfo.id }" statusname="${appinfo.statusName }">修改</a></li>
			                 <li ><a class="viewApp" href="javascript:;" appinfoid="${appinfo.id }">查看</a></li>
			                 <li ><a class="deleteApp" href="javascript:;" appsoftwarename="${appinfo.softwareName }" appinfoid="${appinfo.id }">删除</a></li>
			               </ul>
			             </div>
			       </td>
				</tr>
			</c:forEach>
	  </tbody>
	</table>
</div>
<input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
<c:import url="rollpage.jsp">
	<c:param name="totalCount" value="${totalCount}"/>
	<c:param name="currentPageNo" value="${currentPageNo}"/>
	<c:param name="totalPageCount" value="${totalPageCount}"/>
</c:import>
<div class="clearfix"></div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/localjs/appinfolist.js"></script>