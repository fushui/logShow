package com.byxxlog.ejb.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.data.general.DefaultPieDataset;

import com.byxxlog.ejb.dao.LogDao;
import com.byxxlog.ejb.entity.ByxxLog;

public class PieChart {

	public List<ByxxLog> li;

	private Integer day;

	private Map<String, Integer> map = null;

	public PieChart() {

	}

	public PieChart(Integer day) {
		this.day = day;
	}

	public void mapPut(Map<String, Integer> map, String key) {
		if (null == map.get(key)) {
			map.put(key, 0);
		}
		map.put(key, map.get(key) + 1);

	}

	public void mapPutList(Map<Integer, List<ByxxLog>> map, Integer key, ByxxLog log) {
		// if(null == map.get(key)){
		// List list = new ArrayList<ByxxLog>();
		// map.put(key, list);
		// }

		List<ByxxLog> value = map.get(key);

		if (null == value) {
			value = new ArrayList<ByxxLog>();
			map.put(key, value);
		}
		value.add(log);
		map.put(key, value);

	}

	public void drawPie(JPanel p) {
		JFrame jf = new JFrame();
		jf.add(p);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setBounds(100, 100, 700, 500);
		jf.setVisible(true);
	}

	public JPanel getPie(Map<String, Integer> map, String day) {
		// 构造DataSet
		DefaultPieDataset DataSet = new DefaultPieDataset();

		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			DataSet.setValue(entry.getKey(), entry.getValue());
		}
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
		// 创建饼图
		JFreeChart chart = ChartFactory.createPieChart3D("", DataSet, true, true, false);
		// 用来放置图表
		ChartPanel panel = new ChartPanel(chart);
		// JPanel jp = new JPanel();

		//panel.setPreferredSize(new Dimension(500, 300));
		// jp.add(panel, BorderLayout.CENTER);
		return panel;
	}

	// 按日期分类
	public Map<Integer, List<ByxxLog>> classifyByDate(List<ByxxLog> li) {
		List<Integer> time_lists = new ArrayList<Integer>();
		List<ByxxLog> list = new ArrayList<ByxxLog>();
		Map<Integer, List<ByxxLog>> map = new HashMap<Integer, List<ByxxLog>>();

		for (ByxxLog log : li) {
			String dayString = log.getCreateTime().split(" ")[0];
			Integer day = Integer.parseInt(dayString);
			time_lists.add(day);
			mapPutList(map, day, log);
		}

		return map;
	}

	// 按类别分类
	public Map<String, Integer> classifyByType(List<ByxxLog> li) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (ByxxLog log : li) {
			String type = log.getUserPost();
			mapPut(map, type);
		}
		return map;
	}

	public void test2() {
		LogDao dao = new LogDao();
		List<ByxxLog> li = dao.getData();
		Map<String, Integer> map = classifyByType(li);
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println("key " + entry.getKey() + "value " + entry.getValue());
		}
	}

	public void draw(boolean flag) {
		if (flag) {
			drawPie(getPie(this.map, this.day + ""));
		} else {
			drawPanel();
		}
	}

	private void drawPanel() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "该日期没有数据...", "提示", JOptionPane.ERROR_MESSAGE);
	}

	public boolean initData() {
		LogDao dao = new LogDao();
		List<ByxxLog> li = dao.getData();
		System.out.println("size: " + li.size());
		Map<Integer, List<ByxxLog>> mapDate = classifyByDate(li);

		for (Map.Entry<Integer, List<ByxxLog>> entry : mapDate.entrySet()) {
			Integer day = entry.getKey();
			List<ByxxLog> logList = entry.getValue();
			System.out.println("key : " + entry.getKey() + "valueSize" + logList.size());
			Map<String, Integer> map = classifyByType(entry.getValue());

			if (day.equals(this.day)) {
				this.map = map;
				return true;
			}
			// drawPie(getPie(map, entry.getKey()+""));

		}

		return false;
	}

	public void test() {
		LogDao dao = new LogDao();
		List<ByxxLog> li = dao.getData();
		System.out.println("size: " + li.size());
		Map<Integer, List<ByxxLog>> mapDate = classifyByDate(li);

		for (Map.Entry<Integer, List<ByxxLog>> entry : mapDate.entrySet()) {
			List<ByxxLog> logList = entry.getValue();
			System.out.println("key : " + entry.getKey() + "valueSize" + logList.size());
			Map<String, Integer> map = classifyByType(entry.getValue());

			JFrame jf = new JFrame();
			jf.add(getPie(map, entry.getKey() + ""));
			jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jf.setBounds(100, 100, 700, 500);
			jf.setVisible(true);
		}
	}

	public static void main(String[] args) {

		PieChart chart = new PieChart(20170817);
		boolean flag = chart.initData();
		chart.draw(flag);
	}

	public List<ByxxLog> getLi() {
		return li;
	}

	public void setLi(List<ByxxLog> li) {
		this.li = li;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

}
