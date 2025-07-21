<%--
  Created by IntelliJ IDEA.
  User: du123
  Date: 25. 6. 12.
  Time: 오전 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" import="java.util.*"%>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
</head>
<body>
<h4><c:out value="${exception.getMessage()}"></c:out></h4>
<ul>
  <c:forEach items="${exception.getStackTrace() }" var="stack">
    <li><c:out value="${stack}"></c:out></li>
  </c:forEach>
</ul>

</body>
</html>
