package com.byxxlog.ejb.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public class LineChart_S {
	Map<Integer, LogType> map = null;

	public LineChart_S(Map<Integer, LogType> map, String applicationTitle, String chartTitle) {

		this.map = map;

		// getChart(applicationTitle);

	}

	public JPanel getChart(String chartTitle) {

		// 创建主题样式
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("宋书", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("宋书", Font.PLAIN, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "时刻", "次数", createDataset1(),
				PlotOrientation.VERTICAL, true, true, false);

		ChartPanel panel = new ChartPanel(lineChart);
		// JPanel jp = new JPanel();

		// panel.setPreferredSize(new Dimension(500, 300));
		// jp.add(panel, BorderLayout.CENTER);

		// JPanel panel = new JPanel();
		//panel.setPreferredSize(new Dimension(400, 300));
		return panel;
	}

	private DefaultCategoryDataset createDataset1() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// for (int i = 0; i < 24; i++) {

		dataset.addValue(0, "登录", 0 + "");
		dataset.addValue(0, "操作", 0 + "");
		dataset.addValue(0, "异常", 0 + "");
		for (int i = 0; i < 24; i++) {
			for (Map.Entry<Integer, LogType> entry : this.map.entrySet()) {
				Integer key = entry.getKey() % 100;
				//System.out.println("origin" + entry.getKey() + "key = " + key);
				LogType logType = entry.getValue();

				if (key.equals(i)) {
					// System.out.println("登录个数为 " + logType.getLogin_num() +
					// "时间为: " + i + "");
					dataset.addValue(logType.getLogin_num(), "登录", i + "");
					dataset.addValue(logType.getOperate_num(), "操作", i + "");
					dataset.addValue(logType.getError_num(), "异常", i + "");
					break;
				}
			}
		}

		// }
		// dataset.addValue( 15 , "numbers" , "1970" );
		// dataset.addValue( 30 , "numbers" , "1980" );
		// dataset.addValue( 60 , "numbers" , "1990" );
		// dataset.addValue( 120 , "numbers" , "2000" );
		// dataset.addValue( 240 , "numbers" , "2010" );
		// dataset.addValue( 300 , "numbers" , "2014" );
		return dataset;
	}

}