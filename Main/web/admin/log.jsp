<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AR
  Date: 8/13/2021
  Time: 8:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>تاریخچه عملیات</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>
<body>
<jsp:include page="/admin/Amenu.jsp"/>


<ul class="nav nav-tabs" role="tablist">
    <li class="nav-item">
        <a class="nav-link active" href="#profile" role="tab" data-toggle="tab">SHOW ALL</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#buzz" role="tab" data-toggle="tab">SHOW BY USERNAME</a>
    </li>
</ul>

<div class="tab-content">
    <div role="tabpanel" class="tab-pane fade in active" id="profile">
        <form action="/adminAct/showAllLog.do">
            <input class="btn btn-info" type="submit" value="CLICK TO SHOW" style="margin-top: 40px">
        </form>
        <div class="panel panel-primary">
            <div class="panel-body">
                <table class="table table-hover" style="width: 100%">
                    <tr>
                        <td>USERNAME : EVENT</td>
                    </tr>
                    <c:forEach items="${requestScope.logList}" var="log">
                        <tr>
                            <td><input type="text" class="form-control" readonly name="username" value="${log}" style="width: 100%"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

    <div role="tabpanel" class="tab-pane fade" id="buzz">
        <form action="/adminAct/showLogByUsername.do">
            <input class="form-control" type="text" name="username" placeholder="Enter a username" style="margin-top: 20px; width: 20%">
            <input class="btn btn-info" type="submit" value="SEARCH">
        </form>
        <div class="panel panel-primary">
            <div class="panel-body">
                <table class="table table-hover" style="width: 100%">
                    <tr>
                        <td>USERNAME : EVENT</td>
                        <td></td>
                    </tr>
                    <c:forEach items="${requestScope.logListByUsername}" var="log2">
                        <tr>
                            <td><input type="text" class="form-control" readonly name="username" value="${log2}" style="width: 100%"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
