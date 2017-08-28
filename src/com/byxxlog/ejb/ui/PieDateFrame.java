package com.byxxlog.ejb.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PieDateFrame {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PieDateFrame window = new PieDateFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PieDateFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("饼图");
		JLabel lblNewLabel = new JLabel("日期");
		lblNewLabel.setBounds(109, 87, 54, 15);
		frame.getContentPane().add(lblNewLabel);
		
		final JTextField tfDay = new JTextField();
		tfDay.setBounds(190, 84, 132, 21);
		frame.getContentPane().add(tfDay);
		tfDay.setColumns(10);
		
		JButton btnOk = new JButton("确定");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String day = tfDay.getText();
				PieChart chart = new PieChart(Integer.parseInt(day));
				boolean flag = chart.initData();
				chart.draw(flag);
			}
		});
		btnOk.setBounds(109, 147, 93,  23);
		frame.getContentPane().add(btnOk);
		
		
		
		JButton btnCancel = new JButton("取消");
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
			}
			
		});
		btnCancel.setBounds(226, 147, 93, 23);
		frame.getContentPane().add(btnCancel);
		
		
		
		frame.setVisible(true);
	}
}

