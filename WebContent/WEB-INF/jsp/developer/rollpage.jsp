<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
</script>
</head>
<body>
<div class="row">
	<div class="col-sm-5">
		<div class="dataTables_info" id="datatable_info" role="status" aria-live="polite">
			共 ${param.totalCount} 条记录 ${param.currentPageNo}/${param.totalPageCount}页
		</div>
	</div>
	<div class="col-sm-7">
		<div class="dataTables_paginate paging_simple_numbers" id="datatable_paginate">
			<ul class="pagination">
				<c:if test="${param.currentPageNo > 1}">
					<li class="paginate_button"><a href="javascript:page_nav(document.forms[0],1);">首页</a></li>
					<li class="paginate_button"><a href="javascript:page_nav(document.forms[0],${param.currentPageNo-1});">上一页</a></li>
				</c:if>
				<c:if test="${param.currentPageNo < param.totalPageCount }">
					<li class="paginate_button"><a href="javascript:page_nav(document.forms[0],${param.currentPageNo+1 });">下一页</a></li>
					<li class="paginate_button"><a href="javascript:page_nav(document.forms[0],${param.totalPageCount });">最后一页</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/localjs/rollpage.js"></script>
</html>