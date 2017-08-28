package com.byxxlog.ejb.ui;

import java.awt.EventQueue;
import java.awt.LayoutManager;
import java.awt.Window;

import javax.swing.JFrame;

import weblogic.wsee.security.policy12.assertions.Layout;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 325);
		frame.setLayout(null);
		
	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnPie = new JButton("±˝Õº");
		btnPie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PieDateFrame frame = new PieDateFrame();
			}
		});
		btnPie.setBounds(0, 0, 225, 150);
		frame.add(btnPie, BorderLayout.NORTH);
		                     
		JButton btnLine = new JButton("’€œﬂÕº");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DateFrame frame = new DateFrame();          
			}
		});
		btnLine.setBounds(225, 150, 225, 150);
		frame.add(btnLine, BorderLayout.CENTER);
		frame.setResizable(true);
		//frame.pack();
		
	}

}
