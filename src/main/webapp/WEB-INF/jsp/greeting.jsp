<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>


<div class="container">
    <div class="row mt-2">
        <div class="col text-right">
            <a href="links"><button id="links" class="btn btn-info btn-sm" type="submit">Links</button></a>
        </div>
    </div>

    <form action="/" method="post">

        <div>
            <label for="basic-url">Enter your url here to obtain short url</label>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon3">https://example.com/users/</span>
                </div>
                <input name="link" type="url" class="form-control" id="basic-url" aria-describedby="basic-addon3"
                       value="${shortUrl}" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
            <label for="date"></label>
            <input id="date" type="date" name="dateExpired">
            </div>
            <div class="col-md-4">
                <label for="days"></label>
                <input id="days" type="number" name="daysExpired">
            </div>
            <div class="col-md-4">
                <label for="activate"></label>
                <input id="activate" type="checkbox" name="active">
            </div>

        </div>
        <input class="btn bg-secondary btn-lg text-white" type="submit" value="Get short url">
    </form>
</div>
<script type="text/javascript" src="webjars/bootstrap/4.3.1/js/bootstrap.min.js" defer></script>

</body>
</html>