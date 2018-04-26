<%@ page import="com.zjitc.domain.Order" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>

	<jsp:include page="head.jsp"/>

		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<c:if test="${empty requestScope.page.list}">
						<h2 class="text-center">还没有订单~~</h2>
					</c:if>
					<c:if test="${not empty requestScope.page.list}">
						<strong>我的订单</strong>
					</c:if>

					<table class="table table-bordered">
						<c:forEach items="${requestScope.page.list}" var="list">
							<tbody>
							<tr class="success">
								<th colspan="5">订单编号:${list.oid} </th>

							</tr>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
							<c:forEach items="${list.items}" var="item">
								<tr class="active">
									<td width="60" width="45%">
										<input type="hidden" name="id" value="22">
										<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
									</td>
									<td width="30%">
										<a target="_blank">${item.product.pname}</a>
									</td>
									<td width="20%">
										￥${item.product.shop_price}
									</td>
									<td width="10%">
											${item.count}
									</td>
									<td width="22%">
										<span class="subtotal">￥${item.subtotal}</span>
									</td>
								</tr>
							</c:forEach>

							</tbody>
							<tr class="success">
								<th colspan="3"></th>
								<th>订单总价</th>
								<th>￥${list.total}
									<c:if test="${list.state == 0}">
										<a href="${pageContext.request.contextPath}/orderPay?oid=${list.oid}">付款</a>
									</c:if>
									<c:if test="${list.state == 1}">
										<a style="color: indigo;" href="${pageContext.request.contextPath}/search?oid=${list.oid}">查询物流</a>&nbsp;|
										<a href="${pageContext.request.contextPath}/jsp/payIndex.jsp?returnOid=${list.oid}&returnPay=${list.total}">退款</a>
									</c:if>
									<c:if test="${list.state == 2}">
										<span style="color: red">已退款</span>
										<a href="${pageContext.request.contextPath}/deleteOrder?oid=${list.oid}">删除</a>

									</c:if>
								</th>
							</tr>
							<tr>
								<th colspan="5" height="5px"></th>
							</tr>
						</c:forEach>

					</table>


				</div>
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
							<li><a href="${pageContext.request.contextPath}/orderList?currPage=${requestScope.page.currPage-1}" aria-label="Previous">
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
								<li><a href="${pageContext.request.contextPath}/orderList?currPage=${i}">${i+1}</a></li>
							</c:if>
						</c:forEach>



						<!--下一页-->
						<c:if test="${requestScope.page.currPage==requestScope.page.total-1}">
							<li class="disabled"><a href="javascript:void(0)" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
						<c:if test="${requestScope.page.currPage!=requestScope.page.total-1}">
							<li><a href="${pageContext.request.contextPath}/orderList?currPage=${requestScope.page.currPage+1}" aria-label="Next">
								<span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>

					</c:if>
				</ul>
			</div>
			<!-- 分页结束=======================        -->

		</div>

<jsp:include page="foot.jsp"/>
	</body>

</html>