<%--
  Created by IntelliJ IDEA.
  User: Hello
  Date: 2017/11/13
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>会员登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- 引入自定义css文件 style.css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

    <style>
        body{
            margin-top:20px;
            margin:0 auto;
        }
        .carousel-inner .item img{
            width:100%;
            height:300px;
        }
        .container .row div{
            /* position:relative;
            float:left; */
        }

        font {
            color: #666;
            font-size: 22px;
            font-weight: normal;
            padding-right:17px;
        }
    </style>
</head>
<body>

<jsp:include page="head.jsp"/>

    <div class="container-fluid text-center">
        <h2>${requestScope.msg},该页面将在<span id="sp">5</span>秒后跳转</h2>
    </div>

</div>


<jsp:include page="foot.jsp"/>
</body>

<script>
    var sp = $("#sp");
    var i = 5;
    window.setInterval(intervalFunction, 1000);

    function intervalFunction() {
        i--;
        sp.html(i);
        if (i == 0) {
            location.href = "${requestScope.address}";
        }
    }
</script>
</html>
