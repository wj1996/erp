/**
 * 
 */
$(function(){
	$("#grid").datagrid({
		queryParam:{},
		columns:[[
			{field:'name',title:'商品类型',width:100},
			{field:'y',title:'销售额',width:100}
		]],
		singleSelect:true,
		onLoadSuccess:function(data){
			showChart();
		}
	})
	
	$("#btnSearch").bind("click",function(){
		var formData = $("#searchForm").serializeJSON();
		$("#grid").datagrid("load",formData);
		$("#grid").datagrid({
			url:"reportAction_trendReport",
			queryParams:formData
		})
	})
})

function showChart(){
	var months = new Array();
	for(var i = 1;i <= 12;i++){
		months.push(i + '月');
	}
	Highcharts.chart('trendChart', {

	    title: {
	        text: $("#year").combobox("getValue") + "年趋势分析"
	    },

	    subtitle: {
	        text: 'Source: com.wj'
	    },

	    yAxis: {
	        title: {
	            text: '销售额'
	        }
	    },
	    
	    xAxis:{
	    	categories:months
	    },
	    
	    tooltip:{
	    	valueSuffix:'元'
	    },
	    legend: {
	        layout: 'vertical',
	        align: 'center',
	        verticalAlign: 'bottom'
	    },

	    /*plotOptions: {
	        series: {
	            label: {
	                connectorAllowed: false
	            },
	            pointStart: 2010
	        }
	    },*/

	    series: [{
	        name: '销售趋势',
//	        data: [43934, 52503, 57177, 69658, 97031, 119931, 137133, 154175]
	        data:$("#grid").datagrid("getRows")
	    }/*, {
	        name: 'Manufacturing',
	        data: [24916, 24064, 29742, 29851, 32490, 30282, 38121, 40434]
	    }, {
	        name: 'Sales & Distribution',
	        data: [11744, 17722, 16005, 19771, 20185, 24377, 32147, 39387]
	    }, {
	        name: 'Project Development',
	        data: [null, null, 7988, 12169, 15112, 22452, 34400, 34227]
	    }, {
	        name: 'Other',
	        data: [12908, 5948, 8105, 11248, 8989, 11816, 18274, 18111]
	    }*/],

	    responsive: {
	        rules: [{
	            condition: {
	                maxWidth: 500
	            },
	            chartOptions: {
	                legend: {
	                    layout: 'horizontal',
	                    align: 'center',
	                    verticalAlign: 'bottom'
	                }
	            }
	        }]
	    }

	});
}