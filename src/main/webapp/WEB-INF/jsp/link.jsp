<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 14.04.2019
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>

<h3>Link editor</h3>

<form action="/links" method="post" name="link">
    <input type="hidden" value="${link.id}" name="id">
    <input type="url" value="${link.url}" name="url">
    <input type="hidden" value="${link.shortLink}" name="shortLink">
    <input type="hidden" value="<fmt:formatDate value="${link.createdDate}" pattern="yyyy-MM-dd"/>" name="createdDate">
    <input type="date" value="<fmt:formatDate value="${link.endDate}" pattern="yyyy-MM-dd"/>" name="endDate" required>
    <input type="checkbox" <c:if test="${link.active}">checked</c:if> name="active">
    <input type="submit" value="Update">
</form>

</body>
</html>
