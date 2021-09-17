<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: AR
  Date: 8/15/2021
  Time: 11:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>شروع فرآیند جدید</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="/admin/process/PRmenu.jsp"/>

<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading"><h2 style="margin-top: 20px">ALL PROCESS</h2></div>
        <div class="panel-body">
            <table class="table table-hover" style="width: 50%">
                <tr>
                    <td>PROCESS NAME</td>
                    <td>OPER</td>
                </tr>
                <c:forEach items="${requestScope.processNames}" var="process">
                    <tr>
                        <form action="/adminAct/executionProcess.do">
                            <td><input type="text" class="form-control" readonly name="pname" value="${process.pname}" style="width: 100%"/></td>
                            <td><input type="submit" class="btn btn-info" value="START" style="width: 100%"/></td>
                        </form>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
