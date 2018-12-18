/**
 * 
 */
//当前编辑行
var existEditIndex = -1;
$(function(){
	$("#grid").datagrid({
		columns:[[
			{field:'goodsuuid',title:'商品编号',width:100,editor:{
				type:'numberbox',
				options:{
					disabled:true
				}
			}},
			{field:'goodsname',title:'商品名称',width:100,editor:{
				type:'combobox',
				options:{
					url:'goodsAction_list',
					valueField:'name',
					textField:'name',
					onSelect:function(good){
						console.log(good);
						//获取编辑器
						var edi = getEditor("goodsuuid");
						//target,指向真正使用element
//						$(edi.target).val(good.goodsuuid); //这样写不生效，原因未知
						$(edi.target).numberbox("setValue",good.uuid);
//						$("#_easyui_textbox_input2").val(good.uuid);
//						console.log($("#_easyui_textbox_input2"));
//						console.log($(edi));
//						$(edi.target).val("1");
						edi = getEditor("price");
						$(edi.target).numberbox("setValue",good.inprice);
						var numEditor = getEditor("num");
						$(numEditor.target).select();
						bindGridEditor(numEditor);
					}
				}
			}},
			{field:'price',title:'价格',width:100,editor:{type:'numberbox',options:{precision:2}}},
			{field:'num',title:'数量',width:100,editor:'numberbox'},
			{field:'money',title:'金额',width:100,editor:{type:'numberbox',options:{precision:2}}},
			{field:"-",title:"操作",formatter:function(value,row,rowIndex){
				if(row.num == "合计"){
					
				}else{
					return "<a href='javascript:void(0)' onclick='deleteRow(" + rowIndex + ")'>删除</a>";
				}
				
			}}
		]],
		singletSelect:true,
		//显示编号
		rownumbers:true,
		//显示行脚
		showFooter:true,
		toolbar:[
			{
				text:"新增",
				iconCls:'icon-add',
				handler:function(){
					if(existEditIndex > -1){
						//关闭编辑
						$("#grid").datagrid("endEdit",existEditIndex);	
					}
					//增加一行
					$("#grid").datagrid("appendRow",{num:0,money:0});
					//获取所有行记录
					var rows = $("#grid").datagrid("getRows");
					//设置当前编辑行的索引
					existEditIndex = rows.length - 1;
					//设置编辑器,才能开启编辑状态
					$("#grid").datagrid("beginEdit",rows.length - 1);
				}
			},"-",{
				text:"提交",
				iconCls:'icon-save',
				handler:function(){
					//关闭编辑状态
					if(existEditIndex > -1){
						$("#grid").datagrid("endEdit",existEditIndex);
					}
					
					var rows = $("#grid").datagrid("getRows");
					if(rows.length == 0){
						return;
					}
					var formdata = $("#orderForm").serializeJSON();
					//给formdata加一个json属性
					formdata.json = JSON.stringify(rows);
//					formdata['json'] = JSON.stringify(rows);  //两种方式都可以
					$.ajax({
						url:"ordersAction_add",
						data:formdata,
						dataType:"json",
						type:"post",
						success:function(rtn){
							$.messager.alert("提示",rtn.message,"info",function(){
								//清空供应商
								$("#supplier").combogrid("clear");
								//清空表格
								$("#grid").datagrid("loadData",{total:0,rows:[],footer:[{num:"合计",money:0}]});
							});
						}
					})
				}
			}
		],
		onClickRow:function(rowIndex,rowData){
			$("#grid").datagrid("endEdit",existEditIndex);	
			existEditIndex = rowIndex;
			$("#grid").datagrid("beginEdit",existEditIndex);	
		}
	});
	
	//加载行脚
	$("#grid").datagrid("reloadFooter",[{num:'合计',money:0}]);
	
	//加载供应商下拉表格
	$("#supplier").combogrid({
		panelWidth:700,
		idField:'uuid',
		textField:"name",
		url:"supplierAction_getList?t1.type=1",
		columns:[[
			{field:"uuid",title:"编号",width:100},
			{field:"name",title:"名称",width:100},
			{field:"address",title:"联系地址",width:100},
			{field:"contact",title:"联系人",width:100},
			{field:"tele",title:"联系电话",width:100},
			{field:"email",title:"邮件地址",width:100}
		]]
	});
});

function getEditor(_field){
	return $("#grid").datagrid("getEditor",{index:existEditIndex,field:_field});
}

function cal(){
	//获取编辑器
	var edi = getEditor("num");
	var num = $(edi.target).numberbox("getValue");
	edi = getEditor("price");
	var price = $(edi.target).numberbox("getValue");
	var total = num * price;
	total = total.toFixed(2);
	edi = getEditor("money");
	$(edi.target).numberbox("setValue",total);
	//上面这样设置没有实际设置到表格中，只是设置显示
	$("#grid").datagrid("getRows")[existEditIndex].money = total;
}
/**
 * 绑定事件（自动计算金额）
 * @returns
 */
function bindGridEditor(numEditor){
	//此处应该设置keyup事件，但是无效，待解决
	$(numEditor.target).numberbox({onChange:function(){
		//计算金额
		cal();
		//计算合计金额
		sum();
	}});
}
/**
 * 计算合计金额
 * @returns
 */
function sum(){
	var rows = $("#grid").datagrid("getRows");
	var total = 0;
	$.each(rows,function(i,row){
		total += parseFloat(row.money);
	})
	
	total = total.toFixed(2);
	$("#grid").datagrid("reloadFooter",[{num:'合计',money:total}]);
}
/**
 * 删除行
 * @param rowIndex
 * @returns
 */
function deleteRow(rowIndex){
	//关闭编辑
	$("#grid").datagrid("endEdit",existEditIndex);
	//删除行
	$("#grid").datagrid("deleteRow",rowIndex);
	var data = $("#grid").datagrid("getData");
	//重新加载数据
	$("#grid").datagrid("loadData",data);
	//计算合计
	sum();
}

