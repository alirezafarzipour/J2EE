<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AR
  Date: 8/14/2021
  Time: 12:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #2980B9;">
    <a class="navbar-brand" href="/admin/index.jsp"><b>J2EE</b></a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/admin/index.jsp"><b>Home</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/adminAct/showAllProcess.do"><b>My process</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/adminAct/showAllProcessName.do"><b>Start new process</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/process/newProcess.jsp"><b>Add new process</b></a>
            </li>
        </ul>
    </div>
    <c:if test="${sessionScope.username != null}">
        <a style="text-align: right; color: #0d0d0d; font-size: 20px" href="#">Hi ${sessionScope.username}</a>
    </c:if>

</nav>
