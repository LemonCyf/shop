<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hello
  Date: 2017/12/8
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <title>查询快递</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
</head>
<body>
<jsp:include page="/jsp/head.jsp"/>
<br>
<h2 class="text-center" style="color: #1E6BAE" >快递信息</h2>
<div style="margin-left: 44.8%;margin-top:35px;font-size: medium">

    <div><strong style="color: #1d1d1d">物流运单号:</strong>${requestScope.gsonObject.logisticCode}</div>
    <div style="margin-top:18px"><strong style="color: #1d1d1d">用户ID:</strong>${requestScope.gsonObject.eBusinessID}</div>

    <c:forEach items="${requestScope.gsonObject.traces}" var="traces">
        <div style="margin-top:18px"><strong style="color: #1d1d1d">位置:</strong>${traces.acceptStation}</div>
        <div style="margin-top:18px"><strong style="color: #1d1d1d">时间:</strong>${traces.acceptTime}</div>
        <div style="margin-left: 10%">|</div>
    </c:forEach>
    <div style="margin-top:18px"><strong style="color: #1d1d1d">物流状态:</strong>
        <c:if test="${requestScope.gsonObject.state == 2}">在途中</c:if>
        <c:if test="${requestScope.gsonObject.state == 3}">已签收</c:if>
        <c:if test="${requestScope.gsonObject.state == 4}">问题件</c:if>
    </div>

</div>

</body>
</html>
