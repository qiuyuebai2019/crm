$(function () {

    $("#dg").datagrid({
        url:"/customerList",
        columns:[[
            {field:'cusName',title:'客户姓名',width:100,align:'center'},
            {field:'cusTel',title:'客户电话',width:100,align:'center'},
            {field:'cusAddress',title:'客户联系地址',width:100,align:'center'},
            {field:'cusVisitTime',title:'拜访时间',width:100,align:'center'},
            {field:'cusEmployee',title:'所属业务员',width:100,align:'center'}
        ]],
        fit:true,
        fitColumns:true,
        rownumbers:true,
        pagination:true,
        singleSelect:true,
        striped:true,
        toolbar:"#tb",
    });

    /*监听添加按钮*/
    $("#add").click(function () {
        /*设置标签*/
        $("#cusEmployee").hide();
        $("#dialog").dialog("setTitle","添加客户");
        $("#customerForm").form("clear");
        $("#dialog").dialog("open");
    });

    /*对话框*/
    $("#dialog").dialog({
        width:300,
        height:300,
        closed:true,
        buttons:[{
            text:'保存',
            handler:function () {

                /*判断当前是添加还是编辑*/
                var id = $("[name='id']").val();
                var url;
                if(id){
                    /*编辑*/
                    url = "updateCustomer"
                }else {
                    /*添加*/
                    url = "saveCustomer";
                }

                /*提交表单*/
                $("#customerForm").form("submit",{
                    url:url,
                    success:function (data) {
                        data = $.parseJSON(data);
                        if(data.success){
                            $.messager.alert("提示",data.mes);
                            /*关闭对话框*/
                            $("#dialog").dialog("close");
                            /*重新加载数据表格*/
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert("提示",data.mes);
                        }
                    }
                })
            }
        },{
            text:'关闭',
            handler:function () {
                $("#dialog").dialog("close");
            }
        }
        ]
    });

    /*监听编辑按钮点击*/
    $("#edit").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("提示","选择一行数据进行编辑");
            return;
        }
        $("#cusEmployee").hide();
        /*弹出对话框*/
        $("#dialog").dialog("setTitle","修改客户信息");
        $("#dialog").dialog("open");
        /*选中数据的显示*/
        $("#customerForm").form("load",rowData);
    });

    /*监听编辑按钮点击*/
    $("#distribution").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("提示","选择一个客户进行分配");
            return;
        }
        /*弹出对话框*/
        $("#dialog2").dialog("setTitle","分配客户");
        $("#dialog2").dialog("open");
        /*选中数据的显示*/
        $("#customerForm2").form("load",rowData);
    });

    /*对话框2*/
    $("#dialog2").dialog({
        width:300,
        height:200,
        closed:true,
        buttons:[{
            text:'保存',
            handler:function () {
                /*提交表单*/
                $("#customerForm2").form("submit",{
                    url:"distribution",
                    success:function (data) {
                        data = $.parseJSON(data);
                        if(data.success){
                            $.messager.alert("提示",data.mes);
                            /*关闭对话框*/
                            $("#dialog2").dialog("close");
                            /*重新加载数据表格*/
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert("提示",data.mes);
                        }
                    }
                })
            }
        },{
            text:'关闭',
            handler:function () {
                $("#dialog2").dialog("close");
            }
        }
        ]
    });

    $("#out").click(function () {
        window.open('/download')
    });

    $("#excelUpload").dialog({
        width:260,
        height:180,
        title:"导入Excel",
        buttons:[{
            text:'保存',
            handler:function(){
                $("#uploadForm").form("submit",{
                        url:"uploadExcelFile",
                        success:function (data) {
                            data = $.parseJSON(data);
                            if(data.success){
                                $.messager.alert("温馨提示",data.mes);
                                $("#excelUpload").dialog("close");
                                $("#dg").datagrid("reload");
                            }else {
                                $.messager.alert("提示",data.mes);
                            }
                        }
                    }

                )
            }
        },{
            text:'关闭',
            handler:function () {
                $("#excelUpload").dialog("close");
            }
        }
        ],
        closed:true
    });

    $("#in").click(function () {
        $("#excelUpload").dialog("open");
    });

    $("#downloadTml").click(function () {
        window.open('/downloadExcelTpl')
    })

    /*监听未分配*/
    $("#nodistribution").click(function () {
        var attr = $('#nodistribution').linkbutton("options").text;
        if(attr=='未分配'){
            $('#nodistribution').linkbutton({text:'所有'});
            var keyword = 0;
            $("#dg").datagrid("load",{keyword:keyword});
        }else {
            $('#nodistribution').linkbutton({text:'未分配'});
            var keyword ;
            $("#dg").datagrid("load",{keyword:keyword});
        }
    });

})