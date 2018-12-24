/**
 * 
 */
$(function(){
	$('#grid').datagrid({
		url:"storedetailAction_getListByPage",
		columns:[[
			{field:'uuid',title:'编号',width:100},
			{field:'storeName',title:'仓库',width:100},
			{field:'goodsName',title:'商品',width:100},
			{field:'num',title:'数量',width:100}
		]],
		singletSelect:true,
		pagination: true
	});	
	
	$("#btnSearch").bind("click",function(){
		//把表单数据转换成JSON对象
		var formData = $("#searchForm").serializeJSON();
		//把JSON对象转换成字符串
		//alert(JSON.stringify(formData));
		
		$("#grid").datagrid("load",formData);
		//alert(JSON.stringify($("#grid").datagrid("getData")));
		/* $.ajax({
			url: 'depAction_getList',
			data: formData,
			dataType: 'json',
			type: 'post',
			success:function(rtn){
				$("#grid").datagrid("loadData",rtn);
			}
		}); */
	});
})


