<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: AR
  Date: 8/8/2021
  Time: 9:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>مدیریت کاربران</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="/admin/Amenu.jsp"/>
<div class="container">

  <div class="panel panel-primary">
    <div class="panel-heading"><h2 style="margin-top: 20px">U S E R   M A N A G E R</h2></div>
    <div class="panel-body">

      <form action="/adminAct/addUser.do">
        Name:
        <input class="form-control" type="text" name="name" style="width: 30%"/>
        Family:
        <input class="form-control" type="text" name="family" style="width: 30%"/>
        Username:
        <input class="form-control" type="text" name="username" style="width: 30%"/>
        Password:
        <input class="form-control" type="password" name="password" style="width: 30%"/>
        <br/>
        <input class="btn btn-info" type="submit" value="REGISTER" style="width: 30%;"/>
      </form><br>

      <div id="messageBox"></div>

      <table class="table table-hover" style="width: 100%">
        <tr>
          <td>ID</td>
          <td>NAME</td>
          <td>FAMILY</td>
          <td>USERNAME</td>
          <td>OPER</td>
          <td>OPER</td>
          <td>DIRECT</td>
        </tr>
        <c:forEach items="${requestScope.personList}" var="person">
          <tr>
            <form action="/adminAct/updateUser.do">
              <td><input type="text" class="form-control" readonly name="id" value="${person.id}" style="width: 100%"/></td>
              <td><input type="text" class="form-control" name="name" value="${person.name}" style="width: 100%"/></td>
              <td><input type="text" class="form-control" name="family" value="${person.family}" style="width: 100%"/></td>
              <td><input type="text" class="form-control" readonly id="username" name="username" value="${person.username}" style="width: 100%"/></td>
              <td><input type="submit" class="btn btn-info" value="UPDATE" style="width: 100%"/></td>
              <td><input type="button" class="btn btn-danger" onclick="removePerson(${person.id})" value="DELETE" style="width: 100%"/></td>
              <td><input type="button" class="btn btn-warning" onclick="setChannel(${person.id})" value="MESSAGE" style="width: 100%"/></td>
            </form>
          </tr>
        </c:forEach>
      </table>
    </div>
  </div>
</div>
<script>
  function removePerson(id) {
    if (confirm('are you sure?'))
      window.location = "/adminAct/deleteUser.do?id=" + id;
  }

  function setChannel(id){
    if (confirm('are you sure to send a message?')){
      window.location = "/adminAct/setAdminChannel.do?id="+ id;
    }
  }
</script>
</body>
</html>
