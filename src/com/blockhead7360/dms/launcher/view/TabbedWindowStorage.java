package com.blockhead7360.dms.launcher.view;

import javax.swing.JPanel;

public class TabbedWindowStorage {

	private String title;
	private String desc;
	private JPanel pane;
	
	public TabbedWindowStorage(String title, String desc, JPanel pane) {
		this.title = title;
		this.desc = desc;
		this.pane = pane;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public JPanel getPane() {
		return pane;
	}

	public void setPane(JPanel pane) {
		this.pane = pane;
	}
	
	
}
