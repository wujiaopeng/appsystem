<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="common/head.jsp"%>
<form id="myform" class="form-horizontal form-label-left" method="post"
	action="${pageContext.request.contextPath }/backend/appList" novalidate>
	<span class="section">APP 审核列表 <i class="fa fa-user"></i> <span
		style="font-size: 16px; color: grey">系统管理员-您可以通过搜索或者其他的筛选项对APP的信息进行审核操作哦。^_^</span></span>
	<div class="x_content">
		<table style="width: 1050px; margin-bottom: 20px; margin-top: 10px">
			<tr style="height: 50px;">
				<td width="10%" align="center">软件名称</td>
				<td width="12%" align="center"><input type="text"
					id="softwareName" name="softwareName"
					value="${appinfo.softwareName }" class="form-control input-sm"
					placeholder="" aria-controls="datatable"></td>
				<td width="10%" align="center">所属平台</td>
				<td width="12%"><input type="hidden" id="fid"
					value="${appinfo.flatformId }"> <select id="flatformId"
					name="flatformId" class="form-control input-sm">

				</select></td>
				<td width="10%" align="center">第一分类</td>
				<td width="12%"><input type="hidden" id="cl1"
					value="${appinfo.categoryLevel1 }"> <select
					id="categoryLevel1" name="categoryLevel1"
					class="form-control input-sm">

				</select></td>
			</tr>
			<tr style="height: 50px;">
				<td width="10%" align="center">第二分类</td>
				<td width="12%"><input type="hidden" id="cl2"
					value="${appinfo.categoryLevel2 }"> <select
					id="categoryLevel2" name="categoryLevel2"
					class="form-control input-sm">

				</select></td>
				<td width="10%" align="center">第三分类</td>
				<td width="12%"><input type="hidden" id="cl3"
					value="${appinfo.categoryLevel3 }"> <select
					id="categoryLevel3" name="categoryLevel3"
					class="form-control input-sm">

				</select></td>
				<td width="10%" align="center">
					<p style="margin-left: 20px; margin-bottom: -5px">
						<button type="submit" style="width: 80px;" class="btn btn-primary">查询</button>
					</p>
				</td>
			</tr>
		</table>
	</div>
	<input type="hidden" name="pageIndex" value="1" />
</form>
<div class="clearfix"></div>
<div class="x_content">
	<table id="datatable" class="table table-striped table-bordered"
		style="width: 1050px">
		<thead style="font-size: 15px;">
			<tr>
				<th width="15%">软件名称</th>
				<th width="17%">APK名称</th>
				<th width="10%">软件大小(单位：M)</th>
				<th width="5%">所属平台</th>
				<th width="27%">所属分类(一级分类、二级分类、三级分类)</th>
				<th width="7%">状态</th>
				<th width="4%">下载次数</th>
				<th width="6%">最新版本号</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody style="font-size: 14px">
			<c:forEach var="appinfo" items="${appinfoList }">
				<tr>
					<td>${appinfo.softwareName }</td>
					<td>${appinfo.APKName }</td>
					<td>${appinfo.softwareSize }</td>
					<td>${appinfo.flatformName }</td>
					<td>${appinfo.categoryLevel1Name }->${appinfo.categoryLevel2Name }->${appinfo.categoryLevel3Name }</td>
					<td>${appinfo.statusName }</td>
					<td>${appinfo.downloads }</td>
					<td>${appinfo.versionNo }</td>
					<td>
						<div class="btn-group">
							<button type="button" data-toggle="modal"
                                data-target="#myModal" class="checkApp btn btn-success" href="javascript:;"
								appinfoid="${appinfo.id }" status="${appinfo.status }"
								statusName="${appinfo.statusName }"
								versionid="${appinfo.versionId }"
								versionNo="${appinfo.versionNo }">审核
							</button>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<input type="hidden" id="totalPageCount" value="${totalPageCount}" />

<c:import url="rollpage.jsp">
	<c:param name="totalCount" value="${totalCount}" />
	<c:param name="currentPageNo" value="${currentPageNo}" />
	<c:param name="totalPageCount" value="${totalPageCount}" />
</c:import>

<div class="clearfix"></div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content" style="height: 1200px; width: 800px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">APP基础信息</h4>
			</div>
			<div class="modal-body">

				<div class="form-group sr-only">
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name1" name="id"
							readonly="readonly">

					</div>
				</div>


				<div class="form-group">
					<label for="name1" class="col-sm-2 control-label">软件名称</label>
					<div class="col-sm-10">

						<input type="text" class="form-control" id="name2" name=""
							readonly="readonly">

					</div>
				</div>

				<br /> <br />

				<div class="form-group">
					<label for="name3" class="col-sm-2 control-label">APK名称</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name3" name=""
							readonly="readonly">

					</div>
				</div>
				<br /> <br />

				<div class="form-group">
					<label for="name4" class="col-sm-2 control-label">支持ROM</label>
					<div class="col-sm-10">

						<input type="text" class="form-control" id="name4" name=""
							readonly="readonly">

					</div>

				</div>

				<br /> <br />
				<div class="form-group">
					<label for="name4" class="col-sm-2 control-label">界面语言</label>
					<div class="col-sm-10">

						<input type="text" class="form-control" id="name5" name=""
							readonly="readonly">

					</div>
				</div>
				<br /> <br />
				<div class="form-group">
					<label for="name5" class="col-sm-2 control-label">软件大小</label>
					<div class="col-sm-10">

						<input type="text" class="form-control" id="name6" name=""
							readonly="readonly">

					</div>
				</div>
				<br /> <br />
				<div class="form-group">
					<label for="name6" class="col-sm-2 control-label">下载次数</label>
					<div class="col-sm-10">

						<input type="text" class="form-control" id="name7" name=""
							readonly="readonly">

					</div>
				</div>
				<br /> <br />
				<div class="form-group">
					<label for="name7" class="col-sm-2 control-label">所属平台</label>
					<div class="col-sm-10">

						<input type="text" class="form-control" id="name8" name=""
							readonly="readonly">

					</div>
				</div>
				<br /> <br />
				<div class="form-group">
					<label for="name8" class="col-sm-2 control-label">所属分类</label>
					<div class="col-sm-10">

						<input type="text" class="form-control" id="name9" name=""
							readonly="readonly">

					</div>
				</div>
				<br /> <br />
				<div class="form-group">
					<label for="name9" class="col-sm-2 control-label">APP状态</label>
					<div class="col-sm-10">

						<input type="text" class="form-control" id="name10" name=""
							readonly="readonly">

					</div>
				</div>

				<br /> <br />

				<div class="form-group">
					<label for="name10" class="col-sm-2 control-label">应用简介</label>
					<div class="col-sm-10">

						<textarea type="text" class="form-control" id="name11" name=""
							readonly="readonly"></textarea>

					</div>
				</div>
				<br /> <br /> <br />
				<!--显示logo-->
				<div class="form-group">
					<label class="col-sm-2 control-label">LOGO图片</label> <img src=""
						name="logoPicPath" class="logo" style="width: 110px; height: 90px;">
				</div>

				<br /> <br /> <br />
				<div class="modal-header">

					<h4 class="modal-title" id="myModalLabe">最新版本信息</h4>
				</div>

				<br /> <br />

				<div class="form-group">
					<label class="col-sm-2 control-label">版本号</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="versionNo"
							name="versionNo" readonly="readonly">

					</div>
				</div>

				<br /> <br />
				<div class="form-group">
					<label class="col-sm-2 control-label">版本大小</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="versionSize" name=""
							readonly="readonly">

					</div>
				</div>


				<br /> <br />
				<div class="form-group">
					<label class="col-sm-2 control-label">发布状态</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="publishStatusName"
							name="" readonly="readonly">

					</div>
				</div>
				<br /> <br />
				<div class="form-group">
					<label class="col-sm-2 control-label">版本简介</label>
					<div class="col-sm-10">
						<textarea type="text" class="form-control" id="versionInfo"
							name="" readonly="readonly">
                                                </textarea>
					</div>
				</div>
				<br /> <br /> <br /> <br />
				<!--下载链接 -->
				<div class="form-group">
					<label class="col-sm-2 control-label">apk文件</label>
					<div class="col-sm-8">

						<span>
							<h5 id="apkFileName"></h5> 
							<button type="button" id="downloadAPK" href="javascript:;" 
							apkFileName="">下载</button>
						</span>

					</div>

				</div>

				<br /> <br /> <br /> <br />

				<div class="modal-footer">

						<button type="button" appInfoId="" status="2" versionid=""  class="changeStatus btn btn-primary ">审核通过</button>
					
						<button type="button" appInfoId="" status="3" versionid="" class="changeStatus btn btn-primary ">审核未通过</button>
					

					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</div>
<%@include file="common/footer.jsp"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/localjs/applist.js"></script>

