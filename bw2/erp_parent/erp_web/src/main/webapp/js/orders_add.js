/**
 * 
 */
//当前编辑行
var existEditIndex = -1;
$(function(){
	$("#grid").datagrid({
		columns:[[
			{field:'uuid',title:'商品编号',width:100,editor:{
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
						var edi = getEditor("uuid");
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
						bindGridEditor();
					}
				}
			}},
			{field:'price',title:'价格',width:100,editor:{type:'numberbox',options:{precision:2}}},
			{field:'num',title:'数量',width:100,editor:'numberbox'},
			{field:'money',title:'金额',width:100,editor:'numberbox'},
		]],
		singletSelect:true,
		rownumbers:true,
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
					
				}
			}
		],
		onClickRow:function(rowIndex,rowData){
			$("#grid").datagrid("endEdit",existEditIndex);	
			existEditIndex = rowIndex;
			$("#grid").datagrid("beginEdit",existEditIndex);	
		}
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
}
/**
 * 绑定事件
 * @returns
 */
function bindGridEditor(){
	var numEditor = getEditor('num');
	numEditor.target.numberbox({onchange:function(){
		alert(1);
	}});
}