/**
 * 
 */
$(function(){
	$("#grid").datagrid({
		url:"empAction_list",
		columns:[[
			{field:'uuid',title:'编号',width:100},
			{field:'name',title:'名称',width:100}
		]],
		singleSelect:true,
		onClickRow:function(rowIndex,rowData){
			$("#tree").tree({
				url:'empAction_readEmpRoles?id=' + rowData.uuid,
				animate:true,
				checkbox:true
			});
		}
	});
	
	$("#btnSave").bind("click",function(){
		var nodes = $("#tree").tree("getChecked");
		var ids = [];
		$.each(nodes,function(i,node){
			ids.push(node.id);
		});
		var formdata = {};
		formdata.id = $("#grid").datagrid("getSelected").uuid;
		var checkedStr = ids.join(",");
		formdata.checkedStr = checkedStr;
		
		$.ajax({
			url:"empAction_updateEmpRole",
			data:formdata,
			dataType:'json',
			type:'post',
			success:function(rtn){
				$.messager.alert("提示",rtn.message,"info");
			}
		})
	});
})