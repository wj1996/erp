/**
 * 
 */
var comment1;
var name1;
var w = 300;
var h = 200;
$(function(){
	
	//内容获取
	if(typeof(comment) != "undefined"){
		comment1 = comment;
	}
	if(typeof(name) != "undefined"){
		name1 = name;
	}
	if(typeof(width) != "undefined"){
		w = width;
	}
	if(typeof(height) != "undefined"){
		h = height;
	}
	
	$("#btnSearch").bind("click", function() {
		// 把表单数据转换成JSON对象
		var formData = $("#searchForm").serializeJSON();
		// 把JSON对象转换成字符串
		// alert(JSON.stringify(formData));

		$("#grid").datagrid("load", formData);
		// alert(JSON.stringify($("#grid").datagrid("getData")));
		/*
		 * $.ajax({ url: 'depAction_getList', data: formData, dataType: 'json',
		 * type: 'post', success:function(rtn){ $("#grid").datagrid("loadData",rtn); }
		 * });
		 */
	});

	$("#addDlg").dialog({
		title : "增加" + comment1,
		width : w,
		height : h,
		closed : true,// 窗口是否为关闭状体,true表示关闭
		modal : true
	});

	$("#editDlg").dialog({
		title : "修改" + comment1,
		width : w,
		height : h,
		closed : true,// 窗口是否为关闭状体,true表示关闭
		modal : true
	});

	$("#btnSave").bind("click", function() {
		var isValid = $("#addForm").form("validate");
		if(!isValid){
			return;
		}
		var formData = $("#addForm").serializeJSON();
		$.ajax({
			url : "http://localhost:9090/erp/" + name1 + "Action_add",
			data : formData,
			success : function(rtn) {
				$.messager.alert("提示", rtn.message, "info", function() {
					// 成功的话,关闭窗口
					$("#addDlg").dialog("close");
					// 刷新表格数据
					$("#grid").datagrid("reload");
				});
			}
		});
	});
	$("#btnEdit").bind("click", function() {
		var formData = $("#editForm").serializeJSON();
		$.ajax({
			url : "http://localhost:9090/erp/" + name1 + "Action_update",
			data : formData,
			success : function(rtn) {
				$.messager.alert("提示", rtn.message, "info", function() {
					// 成功的话,关闭窗口
					$("#editDlg").dialog("close");
					// 刷新表格数据
					$("#grid").datagrid("reload");
				})
			}
		});
	});
})
function edit(uuid) {
		// 弹出窗口
		$("#editDlg").dialog('open');
		// 清空表
		$("#editForm").form("clear");
		// 加载数据
		$("#editForm").form("load",
				"http://localhost:9090/erp/" + name1 + "Action_getData?id=" + uuid);
	}

	function del(uuid) {
		$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			if (r) {
				$.ajax({
					url : "http://localhost:9090/erp/" + name1 + "Action_delete",
					data : {
						"t.uuid" : uuid
					},
					success : function(rtn) {
						$.messager.alert("提示", rtn.message, "info", function() {
							// 刷新表格数据
							$("#grid").datagrid("reload");
						});
					}
				});
			}
		});
}
