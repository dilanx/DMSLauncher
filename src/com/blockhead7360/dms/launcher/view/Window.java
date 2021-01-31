package com.blockhead7360.dms.launcher.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.blockhead7360.dms.launcher.DMSLauncher;

public abstract class Window {

	public static List<TabbedWindowStorage> tabs = new ArrayList<TabbedWindowStorage>();


	public DMSLauncher main;
	public JPanel pane;
	public String title;
	public String desc;

	public Window(DMSLauncher main, String title, String desc) {
		this.main = main;
		this.pane = new JPanel();
		this.pane.setLayout(null);
		this.title = title;
		this.desc = desc;
		
	}

	public void register(Window w) {
		
		tabs.add(new TabbedWindowStorage(w.title, w.desc, w.pane));

		return;

	}

}
