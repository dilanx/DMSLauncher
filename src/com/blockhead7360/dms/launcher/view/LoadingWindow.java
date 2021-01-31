package com.blockhead7360.dms.launcher.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.blockhead7360.dms.launcher.DMSLauncher;
import com.blockhead7360.dms.launcher.utilities.Fonts;

public class LoadingWindow {

	private JFrame frame;
	
	public LoadingWindow(String operation) {
		frame = new JFrame("DMSLauncher is " + operation + "...");
		frame.setSize(new Dimension(500, 100));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		
		Container pane = new Container();
		pane.setLayout(null);
		
		JLabel loading = new JLabel("DMSLauncher v" + DMSLauncher.version, 0);
		loading.setFont(new Font("Default", Font.BOLD, 16));
		loading.setBounds(new Rectangle(0, 0, 500, 50));
		pane.add(loading);
		
		JLabel credit = new JLabel("Created by Dilan N - blockhead7360.com", 0);
		credit.setFont(Fonts.italic12a());
		credit.setBounds(new Rectangle(0, 30, 500, 20));
		pane.add(credit);
		
		frame.setContentPane(pane);
		frame.setVisible(true);
		
	}
	
	public void delete() {
		frame.setVisible(false);
		frame.dispose();
	}
	
	
}
