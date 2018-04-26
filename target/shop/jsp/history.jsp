<%--
  Created by IntelliJ IDEA.
  User: wg
  Date: 2017/11/16
  Time: 10:43
  Description:
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--只做当次的浏览记录存储 Session--%>
<div style="width:1210px;margin:0 auto; padding: 0 9px;border: 1px solid #ddd;border-top: 2px solid #999;height: 246px;">

    <h4 style="width: 50%;float: left;font: 14px/30px ">浏览记录</h4>

    <div style="clear: both;"></div>

    <div style="overflow: hidden;">

        <ul style="list-style: none;">
            <c:forEach items="${sessionScope.history.productMap}" var="pd">
                <li style="width: 150px;height: 216;float: left;margin: 0 8px 0 0;padding: 0 18px 15px;text-align: center;">
                    <a href="${pageContext.request.contextPath}/productInfo?pid=${pd.value.pid}">
                        <img src="${pageContext.request.contextPath}/${pd.value.pimage}" width="130px"
                             height="130px"/>
                        <p class="text-center">${fn:substring(pd.value.pname, 0, 10)}</p>
                    </a>

                </li>
            </c:forEach>
        </ul>
    </div>
</div>
