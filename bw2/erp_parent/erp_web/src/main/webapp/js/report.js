/**
 * 
 */
$(function(){
	$("#grid").datagrid({
		url:"reportAction_orderReport",
		columns:[[
			{field:'name',title:'商品类型',width:100},
			{field:'y',title:'销售额',width:100}
		]]
	})
	
	$("#btnSearch").bind("click",function(){
		var formData = $("#searchForm").serializeJSON();
		if(formData.endDate != null && formData.endDate.length > 0){
			formData.endDate += " 23:59:59";
		}
		$("#grid").datagrid("load",formData);
	})
	
	
})