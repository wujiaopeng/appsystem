<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="common/head.jsp"%>


<div class="location">
	<strong>你现在所在的位置是:</strong> <span>用户信息管理页面</span>
</div>

<form method="post"
	action="${pageContext.request.contextPath}/backendApp/getUserList"
	role="form">
	<!--分頁設置（rollpage.js）-->
	<input type="hidden" name="pageIndex" value="1" />
</form>
<div class="col-md-12 col-sm-12 col-xs-12" style="margin-top: 50px">
	<div class="x_panel">
		<div class="x_content">
			<p class="text-muted font-13 m-b-30"></p>
			<div id="datatable-responsive_wrapper"
				class="dataTables_wrapper form-inline dt-bootstrap no-footer">
				<div class="row">
					<div class="col-md-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>开发者列表</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>

									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">

								<p>Better manage users</p>

								<!-- start project list -->
								<table class="table table-hover  table-striped projects">
									<thead>
										<tr>
											<th style="width: 1%"><i class="fa fa-user"></i></th>
											<th style="width: 10%">DevCode</th>
											<th style="width: 20%">DevName</th>
											<th style="width: 19%">DevEmail</th>
											<th>Violate Degree</th>
											<th style="width: 20%">Status</th>
											<th style="width: 10%">Delete</th>
										</tr>
									</thead>

									<tbody id="changetable">
										<c:forEach var="devUser" items="${devUserList}">
											<tr>
												<td><i class="fa fa-user" style="padding-top: 15px"></i></td>
												<td style="padding-top: 20px">${devUser.devCode}</td>
												<td><a>${devUser.devName}</a> <br> <small>Created
														<fmt:formatDate value="${devUser.creationDate }" pattern="yyyy-MM-dd"/></small></td>

												<td style="padding-top: 20px">${devUser.devEmail}</td>

												<td class="project_progress" id="td1"
													style="padding-top: 15px">
													<div class="progress progress_sm">
														<div class="progress-bar bg-red jindu " role="progressbar"
															data-transitiongoal="57"
															style="width: ${devUser.status}%;" aria-valuenow="56"></div>
													</div> <small>违规次数：${devUser.status }</small>
												</td>
												<td><a
													href="${pageContext.request.contextPath}/backendApp/updateUserStatus?id=${devUser.id}"><button
															type="button" class="btn btn-success btn-xs change">Change</button></a>
												</td>

												<td style="padding-top: 15px"><a
													href="${pageContext.request.contextPath}/backendApp/deleteUser?id=${devUser.id}"
													class="btn btn-danger btn-xs delete"><i
														class="fa fa-trash-o"></i> Delete </a></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
								<!-- end project list -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="clearfix"></div>


<%@include file="common/footer.jsp"%>