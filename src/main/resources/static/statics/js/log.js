$(function () {

    $("#dg").datagrid({
        url: "/logList",
        columns: [[
            {field: 'userid', title: '操作人id', width: 1, align: 'center'},
            {field: 'optime', title: '操作时间', width: 1, align: 'center'},
            {field: 'ip', title: '操作ip', width: 1, align: 'center'},
            {field: 'function', title: '调用方法', width: 1, align: 'center'},
            {field: 'params', title: '传入参数', width: 1, align: 'center'},
        ]],
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
    });
})