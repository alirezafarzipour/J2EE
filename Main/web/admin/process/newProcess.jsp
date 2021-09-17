<%--
  Created by IntelliJ IDEA.
  User: AR
  Date: 8/10/2021
  Time: 3:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>تعریف فرآیند جدید</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="/admin/process/PRmenu.jsp"/>

<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading"><h2 style="margin-top: 20px">ADD PROCESS</h2></div>
        <br>
        <div class="panel-body">
            <form method="post" action="/adminAct/UploadProcess" enctype="multipart/form-data">
                Choose your file *(.xml):
                <input class="form-control" type="file" name="fileToUpload" style="width: 30%; margin-bottom: 15px;"/>
                <input class="btn btn-info" type="submit" value="UPLOAD" style="width: 30%; margin-bottom: 40px;"/>
                <p style="color: red">${requestScope.errorMessage}</p>
            </form>

        </div>
    </div>
</div>
</body>
</html>

