package com.byxxlog.ejb.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.jdesktop.swingx.JXDatePicker;

import com.byxxlog.ejb.utils.TimeUtil;

public class MainSplitPanel {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainSplitPanel window = new MainSplitPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainSplitPanel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 600);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelTop = new JPanel();

		panelTop.setBounds(0, 0, 1000, 50);
		panelTop.setLayout(null);

		frame.getContentPane().add(panelTop, BorderLayout.NORTH);

		JLabel lbNowDate = new JLabel("当前日期: ");
		lbNowDate.setBounds(10, 10, 80, 30);
		panelTop.add(lbNowDate);

		JButton btnSearch = new JButton("查询");
		btnSearch.setBounds(300, 10, 100, 30);
		panelTop.add(btnSearch);

		final JXDatePicker datepick = new JXDatePicker();
		Date date = new Date();
		// 设置 date日期
		datepick.setDate(date);

		datepick.setBounds(100, 10, 150, 30);

		panelTop.add(datepick);

		// getDate(panelTop,btnSearch);

		JPanel panelCenter = new JPanel();
		frame.getContentPane().add(panelCenter, BorderLayout.CENTER);

		panelCenter.setBounds(0, 50, 1000, 600);
		panelCenter.setLayout(null);
		final JSplitPane splitPane = new JSplitPane();
		// splitPane.setBounds(0, 50, 1000,600);
		splitPane.setSize(1000, 500);
		panelCenter.add(splitPane, BorderLayout.CENTER);

		Date d = new Date();

		Integer day = TimeUtil.dateToInt(d);

		splitPane.setLeftComponent(getPieChart(day));

		splitPane.setRightComponent(getLineChart(day));
		// splitPane.setLeftComponent(getPieChart());

		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 获取 日期
				Date d = datepick.getDate();

				Integer day = TimeUtil.dateToInt(d);
				// JOptionPane.showMessageDialog(frame, "获取控件中的日期 :" +
				// d_string);
				splitPane.setLeftComponent(getPieChart(day));
				splitPane.setRightComponent(getLineChart(day));
			}
		});
	}

	public JPanel getPieChart(Integer day) {
		JPanel jpanel = new JPanel();
		PieChart chart = new PieChart(day);
		boolean flag = chart.initData();
		// chart.draw(flag);
		if (flag) {
			JPanel jp = chart.getPie(chart.getMap(), day + "");
			jp.setPreferredSize(new Dimension(500, 450));
			jpanel.add(jp, BorderLayout.CENTER);
			
			
		} else {
			JLabel label = new JLabel("该日期没有日志记录...");
			jpanel.add(label, BorderLayout.CENTER);
			jpanel.setPreferredSize(new Dimension(500, 300));
		}
		return jpanel;
	}

	public JPanel getLineChart(Integer day) {
		JPanel jpanel = new JPanel();
		LineChart chart = new LineChart(day);
		boolean flag = chart.initData();
		// chart.draw(flag);
		if (flag) {
			JPanel jp = chart.getChart(chart.getTypeMapResult(), day);
			
			jp.setPreferredSize(new Dimension(400, 450));
			jpanel.add(jp, BorderLayout.CENTER);
		} else {

			JLabel label = new JLabel("该日期没有日志记录...");
			jpanel.add(label, BorderLayout.CENTER);

		}
		return jpanel;
	}

}
