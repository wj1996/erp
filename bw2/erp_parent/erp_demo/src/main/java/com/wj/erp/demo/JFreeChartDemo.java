package com.wj.erp.demo;

import java.awt.Font;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;

public class JFreeChartDemo {

	public static void main(String[] args) throws Exception{
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("家电", 10086);
		dataset.setValue("百货", 1000);
		dataset.setValue("食品", 110);
		
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");  
		//设置标题字体  
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));  
		//设置轴向字体  
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
		//设置图例字体  
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));  
		ChartFactory.setChartTheme(mChartTheme);  
		/**
		 * 参数1：图标的标题
		 * 参数2：dataset，数据集
		 * 参数3：是否显示legend
		 * 参数4：tooltip提示
		 * 参数5：url 是否为超链接
		 */
		JFreeChart chart = ChartFactory.createPieChart("标题", dataset, true, false, false);
		//保存到本地目录下
		/**
		 * 参数1：文件名，本地目录
		 * 参数2：图表对象
		 * 参数3：图表的宽度
		 * 参数4：图表的高度
		 */
		ChartUtilities.saveChartAsPNG(new File("G:/test2/test.png"), chart, 900, 900);
	}
}
