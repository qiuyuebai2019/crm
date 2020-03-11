$(function () {

    $("#dg").datagrid({
        url:"/employeeList",
        columns:[[
            {field:'username',title:'姓名',width:100,align:'center'},
            {field:'inputtime',title:'入职时间',width:100,align:'center'},
            {field:'tel',title:'电话',width:100,align:'center'},
            {field:'email',title:'邮箱',width:100,align:'center'},
            {field:'department',title:'部门',width:100,align:'center',formatter:function (value,row,index) {
                    if(value){
                        return value.name;
                    }
                }},
            {field:'state',title:'状态',width:100,align:'center',formatter:function (value,row,index) {
                if(row.state){
                    return "在职";
                }else {
                    return "<font style='color: red'>离职</font>"
                }
                }},
            {field:'admin',title:'管理员',width:100,align:'center' ,formatter:function (value,row,index) {
                    if(row.admin){
                        return "是";
                    }else {
                        return "否"
                    }
                }}
        ]],
        fit:true,
        fitColumns:true,
        rownumbers:true,
        pagination:true,
        singleSelect:true,
        striped:true,
        toolbar:"#tb",
        onClickRow:function (rowIndex,rowData) {
            /*判断当前是否是离职状态*/
            if(!rowData.state){
                $("#delete").linkbutton("disable");
            }else {
                $("#delete").linkbutton("enable");
            }
        }
    });

    /*对话框*/
    $("#dialog").dialog({
       width:300,
       height:400,
        closed:true,
        buttons:[{
           text:'保存',
            handler:function () {

               /*判断当前是添加还是编辑*/
                var id = $("[name='id']").val();
                var url;
                if(id){
                    /*编辑*/
                    url = "updateEmployee"
                }else {
                    /*添加*/
                    url = "saveEmployee";
                }

                /*提交表单*/
                $("#employeeForm").form("submit",{
                    url:url,
                    onSubmit:function(param){
                        var values = $("#role").combobox("getValues");
                        for(var i= 0;i<values.length;i++){
                            var rid = values[i];
                            param["roles["+i+"].rid"] = rid;
                        }
                    },
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

    /*监听添加按钮*/
    $("#add").click(function () {
        /*设置标签*/
        $("#dialog").dialog("setTitle","编辑员工");
        $("#password").show ();
        $("#employeeForm").form("clear");
        /*取消密码验证*/
        $("[name='password']").validatebox({required:true});
        $("#dialog").dialog("open");
    });

    /*监听编辑按钮点击*/
    $("#edit").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("提示","选择一行数据进行编辑");
            return;
        }
        /*取消密码验证*/
        $("[name='password']").validatebox({required:false});
        $("#password").hide();
        /*弹出对话框*/
        $("#dialog").dialog("setTitle","编辑员工");
        $("#dialog").dialog("open");
        /*回显部门*/
        rowData["department.id"] = rowData["department"].id;
        /*回显管理员*/
        rowData["admin"] = rowData["admin"]+"";
        $.get("/getRoleByEid?id=" +rowData.id,function (data) {
            $("#role").combobox("setValues",data);
        });
        /*选中数据的显示*/
        $("#employeeForm").form("load",rowData);
    })

    /*部门选择 下拉列表*/
    $("#department").combobox({
        width:150,
        panelHeight:'auto',
        editable:false,
        url:"/department",
        textField:'name',
        valueField:'id',
        onLoadSuccess:function () {
            $("#department").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder",$(this).attr("placeholder"));
                }
            })
        }
    });

   /*是否为管理员*/
    $("#state").combobox({
        width:150,
        panelHeight:'auto',
        valueField:'value',
        editable:false,
        textField:'label',
        data:[{
            label:'是',
            value:'true'
        },{
            label:'否',
            value:'false'
        }],
        onLoadSuccess:function () {
            $("#state").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder",$(this).attr("placeholder"));
                }
            })
        }

    });

    /*角色选择 下拉列表*/
    $("#role").combobox({
        width:150,
        panelHeight:'auto',
        editable:false,
        url:"/roleList",
        textField:'rname',
        multiple:true,
        valueField:'rid',
        onLoadSuccess:function () {
            $("#role").each(function (i) {
                var span = $(this).siblings("span")[i];
                var targetInput = $(span).find("input:first");
                if(targetInput){
                    $(targetInput).attr("placeholder",$(this).attr("placeholder"));
                }
            })
        }
    });

    /*设置离职按钮点击*/
    $("#delete").click(function () {
        /*获取当前选中的行*/
        var rowData = $("#dg").datagrid("getSelected");
        if(!rowData){
            $.messager.alert("提示","选择一行数据进行编辑");
            return;
        }
        /*提醒用户是否做离职操作*/
        $.messager.confirm("确认","是否做离职操作",function (res) {
            if(res){
                /*离职操作*/
                $.get("/updateState?id=" +rowData.id,function (data) {
                    if(data.success){
                        $.messager.alert("提示",data.mes);
                        /*重新加载数据表格*/
                        $("#dg").datagrid("reload");
                    }else{
                        $.messager.alert("提示",data.mes);
                    }
                })
            }
        })
    });

    /*监听查询按钮*/
    $("#searchbtn").click(function () {
        /*获取搜索内容*/
        var keyword = $("[name='keyword']").val();
        /*重新加载列表*/
        $("#dg").datagrid("load",{keyword:keyword});
    });

    /*监听刷新按钮*/
    $("#reload").click(function () {
        var keyword = $("[name='keyword']").val('');
        $("#dg").datagrid("load",{});
    });
    


});