/**
 * 
 */
$(function(){
	$('#grid').datagrid({
			url:"empAction_getListByPage",
			columns:[[
				{field:'uuid',title:'编号',width:100},
				{field:'username',title:'登陆名',width:100},
				/* {field:'pwd',title:'登陆密码',width:100}, */
				{field:'name',title:'真实姓名',width:100},
				{field:'gender',title:'性别',width:100,formatter:function(value,row,index){
					if(1 == value * 1){
						return '男';
					}
					if(0 == value * 1){
						return '女';
					}
				}},
				{field:'email',title:'邮件地址',width:100},
				{field:'tele',title:'联系电话',width:100},
				{field:'address',title:'联系地址',width:100},
				{field:'birthday',title:'出生年月日',width:100,formatter:function(value){
					return new Date(value).Format('yyyy-MM-dd');
				}},
				{field:'dep',title:'部门编号',width:100,formatter:function(value){
					return value.name;
				}},
				{field:'-',title:'操作',formatter:function(value,row,index){
					var opr = "<a href='javascript:void(0)' onclick='edit(" + row.uuid + ")'>修改</a>";
					opr += " <a href='javascript:void(0)' onclick='del(" + row.uuid + ")'>删除</a>";
					return opr;
				}}
			]],
			singletSelect:true,
			pagination: true,
			toolbar:[{
				text:'新增',
				iconCls:'icon-add',
				align:"center",
				handler:function(){
					$("#addDlg").dialog("open");
				}
			}]
		});	
})