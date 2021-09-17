<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AR
  Date: 8/14/2021
  Time: 12:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>فرآیند های من</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="/person/Pmenu.jsp"/>
<div class="container">

    <div class="panel panel-primary">
        <div class="panel-heading"><h2 style="margin-top: 20px">Y O U R      P R O C E S S</h2></div>
        <div class="panel-body">
            <table class="table table-hover" style="width: 100%">
                <tr>
                    <td>ID</td>
                    <td>PROCESS NAME</td>
                    <td>ROLE FOR ACCEPT</td>
                    <td>OPER</td>
                </tr>
                <c:forEach items="${requestScope.processList}" var="process">
                    <tr>
                        <form action="/personAct/sendSignal.do">
                            <td><input type="text" class="form-control" readonly name="pid" value="${process.pid}" style="width: 100%"/></td>
                            <td><input type="text" class="form-control" readonly name="pname" value="${process.pname}" style="width: 100%"/></td>
                            <td><input type="text" class="form-control" readonly name="uname" value="${process.uname}" style="width: 100%"/></td>
                            <td><input type="submit" class="btn btn-warning" value="ACCEPT" style="width: 100%"/></td>
                        </form>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
