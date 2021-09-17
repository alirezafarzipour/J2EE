<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AR
  Date: 4/4/2021
  Time: 4:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #2980B9;">
    <a class="navbar-brand" href="/person/index.jsp"><b>J2EE</b></a>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/person/index.jsp"><b>Home</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/personAct/setUserChannel.do"><b>Chat</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/personAct/showAllProcess.do"><b>Process</b></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/logout.do"><b>Log out</b></a>
            </li>
        </ul>
    </div>
    <c:if test="${sessionScope.username != null}">
        <a style="text-align: right; color: #0d0d0d; font-size: 20px" href="#">Hi ${sessionScope.username}</a>
    </c:if>
</nav>

<script>

</script>