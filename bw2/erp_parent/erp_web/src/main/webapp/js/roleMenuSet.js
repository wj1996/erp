/**
 * 
 */
$(function(){
	$("#grid").datagrid({
		url:"roleAction_list",
		columns:[[
			{field:'uuid',title:'编号',width:100},
			{field:'name',title:'名称',width:100}
		]],
		singleSelect:true,
		onClickRow:function(rowIndex,rowData){
			$("#tree").tree({
				url:'roleAction_readRoleMenus?id=' + rowData.uuid,
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
			url:"roleAction_updateRoleMenu",
			data:formdata,
			dataType:'json',
			type:'post',
			success:function(rtn){
				$.messager.alert("提示",rtn.message,"info");
			}
		})
	});
})