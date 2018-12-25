/**
 * 
 */
$(function(){
	$("#grid").datagrid({
		url:"reportAction_orderReport",
		columns:[[
			{field:'name',title:'商品类型',width:100},
			{field:'y',title:'销售额',width:100}
		]],
		singleSelect:true,
		onLoadSuccess:function(data){
			//显示图
			showChart(data.rows);
		}
	})
	
	$("#btnSearch").bind("click",function(){
		var formData = $("#searchForm").serializeJSON();
		if(formData.endDate != null && formData.endDate.length > 0){
			formData.endDate += " 23:59:59";
		}
		$("#grid").datagrid("load",formData);
	})
	
})

function showChart(_data){
	Highcharts.chart('container', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    title: {
	        text: '销售统计'
	    },
	    tooltip: {
	        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	    },
	    //信用
	    credits:{enabled:false},
	    //导出
	    exporting:{enabled:true},
	    plotOptions: {
	        pie: {
	            allowPointSelect: true,
	            cursor: 'pointer',
	            dataLabels: {
	                enabled: true,
	                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                style: {
	                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                }
	            }
	        }
	    },
	    series: [{
	        name: '比较',
	        colorByPoint: true,
	       /* data: [{
	            name: 'Chrome',
	            y: 61.41,
	            sliced: true,
	            selected: true
	        }, {
	            name: 'Internet Explorer',
	            y: 11.84
	        }, {
	            name: 'Firefox',
	            y: 10.85
	        }, {
	            name: 'Edge',
	            y: 4.67
	        }, {
	            name: 'Safari',
	            y: 4.18
	        }, {
	            name: 'Sogou Explorer',
	            y: 1.64
	        }, {
	            name: 'Opera',
	            y: 1.6
	        }, {
	            name: 'QQ',
	            y: 1.2
	        }, {
	            name: 'Other',
	            y: 2.61
	        }]*/
	        data:_data
	    }]
	});
}