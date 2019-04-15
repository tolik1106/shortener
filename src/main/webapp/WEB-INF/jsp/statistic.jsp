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
<div class="container h-100">
<div class="row h-25">
    <div class="col-sm-12">
        <h3 class="text-center pt-2 text-success"><span class="float-left"><a class="btn btn-info btn-sm" href="">Home</a></span></h3>
    </div>
</div>
<div>
<h6 class="text-center">Link ${link.url}</h6>
</div>

    <div class="row h-auto">
        <div class="col-12 text-center">
<ul class="list-group list-group-flush">
    <li class="list-group-item">Total click's: ${follow}</li>
    <li class="list-group-item">Follow by Dates:<br/>
        <div>
            <c:forEach items="${followByDate}" var="dates">
                <div>
                        ${dates[1]}: ${dates[0]};
                </div>
            </c:forEach>
        </div>
    </li>

    <li class="list-group-item">
        Follow by Browser:<br/>
        <div>
            <c:forEach items="${followByBrowser}" var="browsers">
                <div>
                        ${browsers[1]}: ${browsers[0]};
                </div>
            </c:forEach>
        </div>
    </li>

    <c:if test="${!empty refStat}">
    <li class="list-group-item">
        Follow by Referrer:<br/>
        <div>
            <c:forEach items="${refStat}" var="referrers">
                <div>
                        ${referrers[1]}: ${referrers[0]};
                </div>
            </c:forEach>
        </div>
    </li>

    </c:if>
</ul>
        </div>
    </div>
</div>

</body>
</html>
