<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="/js/common.js" defer></script>

<div class="container h-100">
        <div class="row">
            <div class="col-sm-12">
                <h3 class="text-center pt-2 text-success"><span class="float-left"><a class="btn btn-info btn-sm" href="">Home</a></span>Links</h3>
            </div>
        </div>

    <table id="datatable" class="table table-striped">
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
    </table>
    <div class="row">
        <form action="/statistic" method="get" class="col-md-12">
            <div>
                <label for="basic-url">Enter your short url here to get statistic</label>
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon3">http://localhost:8080/1wnhzq0</span>
                    </div>
                    <input name="shortUrl" type="url" class="form-control" id="basic-url" aria-describedby="basic-addon3" required>
                </div>
                <button type="submit" class="btn btn-primary">Statistic</button>
            </div>
        </form>
    </div>
    <div class="output">

    </div>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="url" class="col-form-label">Url</label>
                        <input type="url" class="form-control" id="url" name="url"
                               placeholder="http://example.com">
                    </div>

                    <div class="form-group">
                        <label for="shortLink" class="col-form-label">Short link</label>
                        <input type="url" class="form-control" id="shortLink" name="shortLink"
                               placeholder="http://localhost:8080/tg2A3V5">
                    </div>

                    <div class="form-group">
                        <input type="hidden" id="createdDate" name="createdDate">
                    </div>

                    <div class="form-group">
                        <label for="endDate" class="col-form-label">End date</label>
                        <input type="date" class="form-control" id="endDate" name="endDate">
                    </div>

                    <div class="form-group">
                        <label for="active" class="col-form-label">Is Active</label>
                        <input type="checkbox" class="form-control" id="active" name="active">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>Cancel
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    Save
                </button>
            </div>
        </div>
    </div>
</div>

</body>
</html>