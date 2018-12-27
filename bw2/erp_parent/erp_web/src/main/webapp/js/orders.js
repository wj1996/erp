/**
 * 
 */
$(function(){
	
	var url = "ordersAction_getListByPage";
	
	var btnText = "";
	var inoutTitle = "";
	if(Request['oper'] == "myOrders"){
		
		if(Request['type'] * 1 == 1){
			url = "ordersAction_myListByPage?t1.type=1";
			document.title="我的采购订单";
			btnText = "采购申请";
			inoutTitle = "入库";
			//显示供应商
			$("#addOrdersSupplier").html("供应商：");
		}
		
		if(Request['type'] * 1 == 2){
			url = "ordersAction_myListByPage?t1.type=2&t1.state=0";
			document.title="我的销售订单";
			btnText = "销售订单录入";
			inoutTitle = "出库";
			$("#addOrdersSupplier").html("客户：");
		}
		
		
	}
	
	if(Request['oper'] == 'orders'){
		
		if(Request['type'] * 1 == 1){
			url += "?t1.type=1";
			document.title="采购订单查询";
		}
		if(Request['type'] * 1 == 2){
			url += "?t1.type=2";
			document.title="销售订单查询";
		}
		
	}
	//如果是审核业务，加上state=0，只查询未审核的订单
	if(Request['oper'] == 'doCheck'){
		url += "?t1.type=1&t1.state=0";
		document.title="采购订单审核";
	}
	
	if(Request['oper'] == 'doStart'){
		url += "?t1.type=1&t1.state=1";
		document.title="采购订单确认";
	}
	
	//入库业务
	if(Request['oper'] == 'doInStore'){
		url += "?t1.type=1&t1.state=2";
		document.title="采购订单入库";
		inoutTitle = "入库";
	}
	
	//出库业务
	if(Request['oper'] == "doOutStore"){
		url += "?t1.type=2&t1.state=0";
		document.title = "销售订单出库";
		inoutTitle = "出库";
	}
	
	$('#grid').datagrid({
		url:url,
		columns:getColumns(),
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
			$("#waybillsn").html(rowData.waybillsn);
			
			if(rowData.state * 1 == 1){
				//添加运单详情的按钮
				var options = $("#orderDlg").dialog("options");
				var toolbar = options.toolbar;
				if(null != toolbar){
					toolbar.push({
						text:"订单详情",
						iconCls:'icon-search',
						handler:function(){
							$("#waybillDlg").dialog("open");
							$("#waybillgrid").datagrid({
								url:"ordersAction_waybilldetailList?waybillsn=" + $("#waybillsn").html(),
								columns:[[
									{field:'exedate',title:'执行日期',width:100},
						  		    {field:'exetime',title:'执行时间',width:100},
						  		    {field:'info',title:'执行信息',width:100}
								]],
								rownumbers:true
							})
						}
					});
					
					//重新渲染工具栏
					$("#orderDlg").dialog({
						toolbar:toolbar
					});
				}else{
					$("#orderDlg").dialog({
						toolbar:[{
							text:"订单详情",
							iconCls:'icon-search',
							handler:function(){
								$("#waybillDlg").dialog("open");
								$("#waybillgrid").datagrid({
									url:"ordersAction_waybilldetailList?waybillsn=" + $("#waybillsn").html(),
									columns:[[
										{field:'exedate',title:'执行日期',width:100},
							  		    {field:'exetime',title:'执行时间',width:100},
							  		    {field:'info',title:'执行信息',width:100}
									]],
									rownumbers:true
								})
							}
						}]
					});
				}
			}
			
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
		singleSelect:true
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
				},
				{
					text:"导出",
					iconCls:'icon-excel',
					handler:function(){
						doExport();
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
	
	//出入库窗口
	$("#itemDlg").dialog({
		width:300,
		height:200,
		title:inoutTitle,
		modal:true,
		closed:true,
		buttons:[
			{
				text:inoutTitle,
				iconCls:'icon-save',
				handler:doInOutStore
			}
		]
	})
	
	
	/**
	 * 出入库添加双击事件
	 */
	if(Request['oper'] == 'doInStore' || Request['oper'] == 'doOutStore'){
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
	
	//添加采购申请
	if(Request['oper'] = "myOrders"){
		$("#grid").datagrid({
			toolbar:[
				{
					text:btnText,
					iconCls:'icon-add',
					handler:function(){
						$("#addOrderDlg").dialog("open");
					}
				}
			]
		});
	}
	
	//加载增加订单
	$("#addOrderDlg").dialog({
		width:800,
		height:400,
		title:'增加订单',
		modal:true,
		closed:true
	})
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
 * 采购：0：未审核 1：已审核 2：已确认 3：已入库
 * 销售：0：未出库 1：已出库
 * @param value
 * @returns
 */
function getState(value){
	
	if(Request['type'] * 1 == 1){
		switch(value * 1){
		case 0:return '未审核';
		case 1:return '已审核';
		case 2:return '已确认';
		case 3:return '已入库';
		default:return '';
		}
	}
	
	if(Request['type'] * 1 == 2){
		switch(value * 1){
		case 0:return '未出库';
		case 1:return '已出库';
		default:return '';
		}
	}
	
	
}
/**
 * 获取订单明细状态
 * @param value
 * @returns
 */
function getDetailState(value){
	if(Request['type'] * 1 == 1){
		switch(value * 1){
		case 0:return '未入库';
		case 1:return '已入库';
		default:return '';
		}
	}
	
	if(Request['type'] * 1 == 2){
		switch(value * 1){
		case 0:return '未出库';
		case 1:return '已出库';
		default:return '';
		}
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
 * 出入库
 * @returns
 */
function doInOutStore(){
	
	var message = "";
	var url = "";
	
	if(Request['type'] * 1 == 1){
		message = "确认要入库吗？";
		url = "orderdetailAction_doInStore";
	}
	if(Request['type'] * 1 == 2){
		message = "确认要出库吗？";
		url = "orderdetailAction_doOutStore";
	}
	
	
	var formdata = $("#itemForm").serializeJSON();
	if(formdata.storeuuid == ''){
		$.messager.alert("提示","请选择仓库！","info");
		return;
	}
	
	$.messager.confirm("确认",message,function(yes){
		if(yes){
			$.ajax({
				url:url,
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
							$('#itemgrid').datagrid('getSelected').state = "1";
							var data = $("#itemgrid").datagrid("getData");
							
							$("#itemgrid").datagrid("loadData",data);
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
								$("#orderDlg").dialog("close");
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
/**
 * 根据订单类型，获取不同的列
 * @returns
 */
function getColumns(){
	if(Request['type'] * 1 == 1){
		return [[
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
			{field:'supplierName',title:'供应商',width:100},
			{field:'totalmoney',title:'合计金额',width:100},
			/*{field:'state',title:'采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库',width:100},*/
			{field:'state',title:'状态',width:100,formatter:getState},
			{field:'waybillsn',title:'运单号',width:100}
		]];
	}
	
	if(Request['type'] * 1 == 2){
		return [[
			{field:'uuid',title:'编号',width:100},
			{field:'createtime',title:'生成日期',width:100,formatter:formatDate},
			{field:'endtime',title:'出库日期',width:100,formatter:formatDate},
			/*{field:'type',title:'1:采购 2:销售',width:100},*/
			{field:'createrName',title:'下单员',width:100},
			{field:'enderName',title:'库管员',width:100},
			{field:'supplierName',title:'客户',width:100},
			{field:'totalmoney',title:'合计金额',width:100},
			/*{field:'state',title:'采购: 0:未审核 1:已审核, 2:已确认, 3:已入库；销售：0:未出库 1:已出库',width:100},*/
			{field:'state',title:'状态',width:100,formatter:getState},
			{field:'waybillsn',title:'运单号',width:100}
		]];
	}
}

function doExport(){
	$.download("ordersAction_export",{"id":$("#uuid").html()});
}


