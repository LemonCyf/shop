<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
				width: 100%;
			}
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
		<jsp:include page="head.jsp"/>
		<div class="row" style="width:1210px;margin:0 auto;">
			<c:forEach items="${requestScope.page.list}" var="items">
				<div class="col-md-2">
					<a href="${pageContext.request.contextPath}/productInfo?pid=${items.pid}">
						<img src="${pageContext.request.contextPath}/${items.pimage}" width="170" height="170" style="display: inline-block;">
					</a>
					<p><a href="${pageContext.request.contextPath}/productInfo?pid=${items.pid}" style='color:green'>${fn:substring(items.pname,0,12)}</a></p>
					<p><font color="#FF0000">商城价：&yen;${items.shop_price}</font></p>
				</div>
			</c:forEach>

		</div>

		<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:50px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<c:if test="${requestScope.page.total-1 > 0}">
				<!--上一页-->
				<!--判断当前页是否为首页-->
				<!--当前页是首页,设置不可点：disabled,并取消链接功能-->
				<c:if test="${requestScope.page.currPage==0}">
					<li class="disabled"><a href="javascript:void(0)" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>
				<!--当前页不是首页,可点,保留链接-->
				<c:if test="${requestScope.page.currPage!=0}">
					<li><a href="${pageContext.request.contextPath}/productList?cid=${requestScope.cid}&currPage=${requestScope.page.currPage-1}" aria-label="Previous">
						<span aria-hidden="true">&laquo;</span>
					</a></li>
				</c:if>

				<!--展示所有页码-->

				<c:forEach begin="${requestScope.page.start}" end="${requestScope.page.total-1}" var="i" step="1">
					<!--判断是否为当前页-->
					<!--是的话加上active,并取消跳转-->
					<c:if test="${i==requestScope.page.currPage}">
						<li class="active"><a href="javascript:void(0)">${i+1}</a></li>
					</c:if>
					<!--若不是，保留超链接功能-->
					<c:if test="${i!=requestScope.page.currPage}">
						<li><a href="${pageContext.request.contextPath}/productList?cid=${requestScope.cid}&currPage=${i}">${i+1}</a></li>
					</c:if>
				</c:forEach>



				<!--下一页-->
				<c:if test="${requestScope.page.currPage==requestScope.page.total-1}">
					<li class="disabled"><a href="javascript:void(0)" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>
				<c:if test="${requestScope.page.currPage!=requestScope.page.total-1}">
					<li><a href="${pageContext.request.contextPath}/productList?cid=${requestScope.cid}&currPage=${requestScope.page.currPage+1}" aria-label="Next">
						<span aria-hidden="true">&raquo;</span>
					</a></li>
				</c:if>

				</c:if>
			</ul>
		</div>
		<!-- 分页结束=======================        -->


		<jsp:include page="history.jsp"/>
		<jsp:include page="foot.jsp"/>

	</body>

</html>