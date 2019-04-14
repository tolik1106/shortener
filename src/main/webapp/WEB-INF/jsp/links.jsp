<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>

<div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <h3 class="text-center pt-2 text-success"><span class="float-left"><a class="btn btn-info btn-sm" href="/">Home</a></span>Links</h3>
            </div>
        </div>

    <table id="datatable" class="table">
        <thead class="text-dark">
        <tr>
            <th>Original url</th>
            <th>Short url</th>
            <th>Created date</th>
            <th>Expired date</th>
            <th>Active</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody class="bg-light">
        <c:forEach items="${links}" var="link">
            <jsp:useBean id="link" scope="page" type="com.zhitar.shortenerurl.domain.Link"/>
            <tr>
                <td>${link.url}</td>
                <td>${link.shortLink}</td>
                <td><fmt:formatDate value="${link.createdDate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${link.endDate}" pattern="yyyy-MM-dd"/></td>
                <td>${link.active}</td>
                <td><a href="/update/${link.id}">Update</a></td>
                <td><a href="/delete/${link.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>