/**
 * 
 */
$(function(){
	$('#grid').datagrid({
		url:"ordersAction_getListByPage?t1.type=1",
		columns:[[
			{field:'uuid',title:'编号',width:100},
			{field:'createtime',title:'生成日期',width:100,formatter:formatDate},
			{field:'checktime',title:'审核日期',width:100,formatter:formatDate},
			{field:'starttime',title:'确认日期',width:100,formatter:formatDate},
			{field:'endtime',title:'入库日期',width:100,formatter:formatDate},
			/*{field:'type',title:'1:采购 2:销售',width:100},*/
			{field:'createrName',title:'下单员',width:100},
			{field:'checkerName',title:'审核员',width:100},
			{field:'starterName',title:'采购员',width:100},
			{field:'enderName',title:'库管员',width:100},
			{field:'supplierName',title:'供应商或客户',width:100},
			{field:'totalmoney',title:'合计金额',width:100},
			/*{field:'state',title:'采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库',width:100},*/
			{field:'state',title:'状态',width:100,formatter:getState},
			{field:'waybillsn',title:'运单号',width:100}
		]],
		singletSelect:true,
		pagination: true,
		//列宽度自动调整
		fitColumns:true,
		onDblClickRow:function(rowIndex,rowData){
			
			//显示详情
			$("#uuid").html(rowData.uuid);
			$("#suppliername").html(rowData.supplierName);
			$("#state").html(rowData.state);
			$("#creater").html(rowData.createrName);
			$("#checker").html(rowData.checkerName);
			$("#starter").html(rowData.starterName);
			$("#ender").html(rowData.enderName);
			$("#createtime").html(formatDate(rowData.createtime));
			$("#checktime").html(formatDate(rowData.checktime));
			$("#starttime").html(formatDate(rowData.starttime));
			$("#endtime").html(formatDate(rowData.endtime));
			$("#orderDlg").dialog("open");
			//加载明细列表
			$("#itemgrid").datagrid("loadData",rowData.orderDetails);
		}
	});
	
	$("#itemgrid").datagrid({
		columns:[[
			{field:'uuid',title:'编号',width:100},
			{field:'goodsuuid',title:'商品编号',width:100},
			{field:'goodsname',title:'商品名称',width:100},
			{field:'price',title:'价格',width:100},
			{field:'num',title:'数量',width:100},
			{field:'money',title:'金额',width:100},
			{field:'state',title:'状态',width:100,formatter:getDetailState}
		]],
		fitColumns:true,
		singletSelect:true
	});
})
/**
 * 日期格式化器
 * @param value
 * @returns
 */
function formatDate(value){
	return new Date(value).Format('yyyy-MM-dd');
}

/**
 * 获取订单状态
 * @param value
 * @returns
 */
function getState(value){
	switch(value * 1){
	case 0:return '未审核';
	case 1:return '已审核';
	case 2:return '已确认';
	case 3:return '已入库';
	default:return '';
	}
}
/**
 * 获取订单明细状态
 * @param value
 * @returns
 */
function getDetailState(value){
	switch(value * 1){
	case 0:return '未入库';
	case 1:return '已入库';
	default:return '';
	}
}