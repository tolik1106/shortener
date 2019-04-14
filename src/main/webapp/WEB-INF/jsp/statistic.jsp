<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anatoliy
  Date: 13.04.2019
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>

<h6>Link ${link.url} statistic</h6>

<ul>
    <li>Total click: ${follow}</li>
    <c:forEach items="${statistic}" var="stat">
        <div>
            ${stat.link}
        </div>
        <div>
                ${stat.browser}
        </div>
        <div>
                ${stat.followDate}
        </div>
        <div>
                ${stat.IPAddress}
        </div>
    </c:forEach>
</ul>

</body>
</html>
