const linkAjaxUrl = "/alllinks/";
let context, form;

function enable(chkbox, id) {
    const enabled = chkbox.is(":checked");
    $.ajax({
        url: linkAjaxUrl + id,
        type: "POST",
        data: "active=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-linkEnabled", enabled);
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

$(function () {
    makeEditable({
        ajaxUrl: linkAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": linkAjaxUrl,
                "dataSrc": ""
            },
            "paging": true,
            "info": false,
            "columns": [
                {
                    "data": "url"
                },
                {
                    "data": "shortLink"
                },
                {
                    "data": "createdDate",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return data.substring(0, 10);
                        }
                        return data;
                    }
                },
                {
                    "data": "endDate",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return data.substring(0, 10);
                        }
                        return data;
                    }
                },
                {
                    "data": "active",
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                        }
                        return data;
                    }
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (!data.enabled) {
                    $(row).attr("data-linkEnabled", false);
                }
            }
        }),
        updateTable: function () {
            $.get(linkAjaxUrl, updateTableByData);
        }
    })
});

function makeEditable(ctx) {
    context = ctx;
    form = $('#detailsForm');
    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='updateRow(" + row.id + ");'><span class='fa fa-pencil'></span></a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}

function updateRow(id) {
    $("#modalTitle").html("Update link");
    $.get(context.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            debugger;
            form.find("input[name='" + key + "']").val(value);
        });
        $('#editRow').modal();
    });
}

function deleteRow(id) {
    if (confirm("Are you sure?")) {
        $.ajax({
            url: context.ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            context.updateTable();
        });
    }
}

function updateTableByData(data) {
    context.datatableApi.clear().rows.add(data).draw();
}

function save() {
    $.ajax({
        type: "POST",
        url: context.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        context.updateTable();
    });
}