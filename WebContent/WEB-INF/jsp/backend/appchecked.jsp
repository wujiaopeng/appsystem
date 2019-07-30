<%--
  Created by IntelliJ IDEA.
  User: 杨学艺
  Date: 2018/12/18
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/head.jsp" %>

<div class="location" style="margin-bottom: 30px">
    <strong>你现在所在的位置是:</strong>
    <span>已审核页面</span>
</div>
<div class="container">
    <div class="row" >

        <div class="row top_tiles" >

            <div class="animated flipInY col-lg-4 col-md-3 col-sm-6 col-xs-12" >

                <div class="tile-stats">
                    <div class="icon" style="padding-top: 20px"><i class="fa fa-spinner"></i></div>
                    <div class="count">${totalCount}</div>
                    <h3>当前App总数</h3>
                    <br>
                    <p>Total number of App.</p>
                </div>
            </div>
            <div class="animated flipInY col-lg-4 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats">
                    <div class="icon"style="padding-top: 20px"><i class="fa fa-check"></i></div>
                    <div class="count">${totalCount-noCheckedNum}</div>
                    <h3>审核通过的App</h3>
                    <br>
                    <p>Audit through App.</p>
                </div>
            </div>
            <div class="animated flipInY col-lg-4 col-md-3 col-sm-6 col-xs-12">
                <div class="tile-stats">
                    <div class="icon"style="padding-top: 20px"><i class="fa fa-times" aria-hidden="true"></i></div>
                    <div class="count">${noCheckedNum}</div>
                    <h3>审核未通过的APP</h3>
                    <br>
                    <p>Audit of failed APP.</p>
                </div>
            </div>
        </div>




        <form method="post" action="${pageContext.request.contextPath}/backend/checkedlist" role="form">
            <!--分頁設置（rollpage.js）-->
            <input type="hidden" name="pageIndex" value="1"/>
        </form>

    <div class="col-md-12 col-sm-12 col-xs-12" style="margin-top: 50px">
        <div class="x_panel">
            <div class="x_content">
                <p class="text-muted font-13 m-b-30"></p>
                <div id="datatable-responsive_wrapper"
                     class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                    <div class="row">
                        <div class="col-sm-12">

                            <table id="datatable-responsive"
                                   class="table table table-hover table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
                                   cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info"
                                   style="width: 100%;">
                                <thead>
                                <tr role="row" class="headings">

                                    <div class="row x_title">
                                        <div class="col-md-6">
                                            <h3>App已审核列表
                                            </h3>
                                        </div>
                                    <th class="sorting_asc" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        style="width: 70px;text-align: center" aria-label="First name: activate to sort column descending"
                                        aria-sort="ascending">软件名称
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        style="width:0px;text-align: center"
                                        aria-label="Last name: activate to sort column ascending">
                                        APK名称
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        style="width: 90px;text-align: center"
                                        aria-label="Last name: activate to sort column ascending">
                                        软件大小(单位:M)
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        style="width: 50px;text-align: center"
                                        aria-label="Last name: activate to sort column ascending">
                                        所属平台
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        style="width: 170px;text-align: center"
                                        aria-label="Last name: activate to sort column ascending">
                                        所属分类
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        style="width: 30px;text-align: center"
                                        aria-label="Last name: activate to sort column ascending">
                                        状态
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        style="width: 50px;text-align: center"
                                        aria-label="Last name: activate to sort column ascending">
                                        下载次数
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        style="width: 64px;text-align: center"
                                        aria-label="Last name: activate to sort column ascending">
                                        最新版本号
                                    </th>
                                    </div>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="appInfo" items="${appInfoList }" varStatus="status">
                                    <tr role="row" class="odd">
                                        <td tabindex="0" class="sorting_1">${appInfo.softwareName}</td>
                                        <td>${appInfo.APKName }</td>
                                        <td>${appInfo.softwareSize }</td>
                                        <td>${appInfo.flatformName }</td>
                                        <td>${appInfo.categoryLevel1Name } -> ${appInfo.categoryLevel2Name }
                                            -> ${appInfo.categoryLevel3Name }</td>
                                        <td>${appInfo.statusName }</td>
                                        <td>${appInfo.downloads }</td>
                                        <td>${appInfo.versionNo }</td>


                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <input type="hidden" id="totalPageCount" value="${totalPageCount}"/>
					<c:import url="rollpage.jsp">
						<c:param name="totalCount" value="${totalCount}"/>
						<c:param name="currentPageNo" value="${currentPageNo}"/>
						<c:param name="totalPageCount" value="${totalPageCount}"/>
					</c:import>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<%@include file="common/footer.jsp" %>




