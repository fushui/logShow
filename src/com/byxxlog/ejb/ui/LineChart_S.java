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

		// ����������ʽ
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		// ����ͼ��������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15));
		// �������������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15));
		// Ӧ��������ʽ
		ChartFactory.setChartTheme(standardChartTheme);
		JFreeChart lineChart = ChartFactory.createLineChart(chartTitle, "ʱ��", "����", createDataset1(),
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

		dataset.addValue(0, "��¼", 0 + "");
		dataset.addValue(0, "����", 0 + "");
		dataset.addValue(0, "�쳣", 0 + "");
		for (int i = 0; i < 24; i++) {
			for (Map.Entry<Integer, LogType> entry : this.map.entrySet()) {
				Integer key = entry.getKey() % 100;
				//System.out.println("origin" + entry.getKey() + "key = " + key);
				LogType logType = entry.getValue();

				if (key.equals(i)) {
					// System.out.println("��¼����Ϊ " + logType.getLogin_num() +
					// "ʱ��Ϊ: " + i + "");
					dataset.addValue(logType.getLogin_num(), "��¼", i + "");
					dataset.addValue(logType.getOperate_num(), "����", i + "");
					dataset.addValue(logType.getError_num(), "�쳣", i + "");
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