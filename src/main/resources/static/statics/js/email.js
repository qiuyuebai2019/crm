$(function () {
    $("#dg").datagrid({
        url:"/myEmail",
        columns:[[
            {field:'id',title:'id',width:100,align:'center',hidden:"true"},
            {field:'title',title:'邮件标题',width:100,align:'center'},
            {field:'sName',title:'发件人姓名',width:100,align:'center'},
            {field:'createTime',title:'发送时间',width:100,align:'center'}
        ]],
        fit:true,
        fitColumns:true,
        rownumbers:true,
        pagination:true,
        singleSelect:true,
        striped:true,
        toolbar:"#tb",
    });

    $("#send").click(function () {
        /*弹出对话框*/
        $("#dialog").dialog("setTitle","发送邮件");
        $("#dialog").dialog("open");
    });

    /*对话框*/
    $("#dialog").dialog({
        width:300,
        height:400,
        closed:true,
        buttons:[{
            text:'发送',
            handler:function () {
                /*提交表单*/
                $("#emailForm").form("submit",{
                    url:"sendEmail",
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

    $("#see").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("提示","选择一行数据进行查看");
            return;
        }
        $("#title").hide();
        $("#rId").hide();
        /*弹出对话框*/
        $("#dialog1").dialog("setTitle","邮件正文");
        $("#dialog1").dialog("open");
        /*选中数据的显示*/
        $("#emailForm1").form("load",rowData);
    });

    /*对话框*/
    $("#dialog1").dialog({
        width:265,
        height:385,
        closed:true,
        buttons:[{
            text:'以读',
            handler:function () {
                /*提交表单*/
                $("#emailForm1").form("submit",{
                    url:"readEmail",
                    success:function (data) {
                        data = $.parseJSON(data);
                        if(data.success){
                            $.messager.alert("提示",data.mes);
                            /*关闭对话框*/
                            $("#dialog1").dialog("close");
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
                $("#dialog1").dialog("close");
            }
        }
        ]
    });

    $("#read").click(function () {
        var attr = $('#read').linkbutton("options").text;
        if(attr=='查看以读'){
            $('#read').linkbutton({text:'查看未读'});
            var isRead = 1;
            $("#dg").datagrid("load",{isRead:isRead});
        }else {
            $('#read').linkbutton({text:'查看以读'});
            var isRead = 0;
            $("#dg").datagrid("load",{isRead:isRead});
        }
    });
})