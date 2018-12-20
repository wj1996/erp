/**
 * 
 */
$(function(){
	
	var url = "ordersAction_getListByPage?t1.type=1";
	//如果是审核业务，加上state=0，只查询未审核的订单
	if(Request['oper'] == 'doCheck'){
		url += "&t1.state=0";
	}
	
	if(Request['oper'] == 'doStart'){
		url += "&t1.state=1";
	}
	
	
	if(Request['oper'] == 'doInStore'){
		url += "&t1.state=2";
	}
	
	
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
	
	//添加审核按钮
	if(Request['oper'] == 'doCheck'){
		$("#orderDlg").dialog({
			toolbar:[
				{
					text:"审核",
					iconCls:'icon-search',
					handler:function(){
						doCheck();
					}
				}
			]
		});
	}
	
	//添加确认按钮
	if(Request['oper'] == 'doStart'){
		$("#orderDlg").dialog({
			toolbar:[
				{
					text:"确认",
					iconCls:'icon-search',
					handler:function(){
						doStart();
					}
				}
			]
		});
	}
	
	$("#itemDlg").dialog({
		width:300,
		height:200,
		title:'入库',
		modal:true,
		closed:true,
		buttons:[
			{
				text:'入库',
				iconCls:'icon-save',
				handler:doInStore
			}
		]
	})
	
	/**
	 * 入库
	 */
	if(Request['oper'] == 'doInStore'){
		$("#itemgrid").datagrid({
			onDblClickRow:function(rowIndex,rowData){
				//提示数据
				$("#itemuuid").val(rowData.uuid);
				$("#goodsuuid").html(rowData.goodsuuid);
				$("#goodsname").html(rowData.goodsname);
				$("#goodsnum").html(rowData.num);
				//打开入库窗口
				$("#itemDlg").dialog('open');
			}
		});
	}
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
/**
 * 审核
 * @returns
 */
function doCheck(){
	$.messager.confirm("确认","确认要审核吗？",function(yes){
		if(yes){
			$.ajax({
				url:"ordersAction_doCheck?id=" + $("#uuid").html(),
				dataType:"json",
				type:"post",
				success:function(rtn){
					$.messager.alert("提示",rtn.message,'info',function(){
						//关闭窗口
						$("#orderDlg").dialog("close");
						//刷新表格
						$("#grid").datagrid("reload");
					})
				}
			})
		}
	})
}

/**
 * 确认
 * @returns
 */
function doStart(){
	$.messager.confirm("确认","确定要确认吗？",function(yes){
		if(yes){
			$.ajax({
				url:"ordersAction_doStart?id=" + $("#uuid").html(),
				dataType:"json",
				type:"post",
				success:function(rtn){
					$.messager.alert("提示",rtn.message,'info',function(){
						//关闭窗口
						$("#orderDlg").dialog("close");
						//刷新表格
						$("#grid").datagrid("reload");
					})
				}
			})
		}
	})
}
/**
 * 入库
 * @returns
 */
function doInStore(){
	var formdata = $("#itemForm").serializeJSON();
	if(formdata.storeuuid == ''){
		$.messager.alert("提示","请选择仓库！","info");
		return;
	}
	
	$.messager.confirm("确认","确认要入库吗？",function(yes){
		if(yes){
			$.ajax({
				url:"orderdetailAction_doInStore",
				data:formdata,
				type:"post",
				dataType:"json",
				success:function(rtn){
					$.messager.alert("提示",rtn.message,"info",function(){
						if(rtn.success){
							//关闭入库窗口
							$("#itemDlg").dialog('close');
							//刷新明细列
							var row = $("#itemgrid").datagrid("getSelected");
							console.log($("#itemgrid"));
							var get = $("#itemgrid").datagrid("getSelections");
							console.log(get);
							$('#itemgrid').datagrid('getSelected').state = "1";
							var data = $("#itemgrid").datagrid("getData");
							//如果所有明细都入库了，应该关闭订单详情，并且刷新订单列表
							var allDone = true;
							$.each(data.rows,function(i,row){
								if(row.state * 1 == 0){
									allDone = false;
									return false;
								}
							})
							
							if(allDone == true){
								//关闭详情窗口
								$("#ordersDlg").dialog("close");
								//刷新订单列表
								$("#grid").datagrid("reload");
							}
						}
					})	
				}
			})
		}
	})
}
