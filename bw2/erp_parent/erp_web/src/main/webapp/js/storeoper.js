/**
 * 
 */
$(function(){
	$("#grid").datagrid({
		url:"storeoperAction_getListByPage",
			columns:[[
				{field:'uuid',title:'编号',width:100},
				{field:'empName',title:'操作员工',width:100},
				{field:'opertime',title:'操作日期',width:100,formatter:formateDate},
				{field:'storeName',title:'仓库',width:100},
				{field:'goodsName',title:'商品',width:100},
				{field:'num',title:'数量',width:100},
				{field:'type',title:'类型',width:100,formatter:function(value){
					if(value * 1 == 1){
						return "入库";
					}
					if(value *1 == 2){
						return "出库";
					}
				}}
			]],
			singletSelect:true,
			pagination: true,
			fitColumns:true
	})
	
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

function formateDate(value){
	return new Date(value).Format("yyyy-MM-dd hh:mm:ss");
}