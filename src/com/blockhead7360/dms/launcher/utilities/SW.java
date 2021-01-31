package com.blockhead7360.dms.launcher.utilities;

import javax.swing.SwingWorker;

public abstract class SW extends SwingWorker<String, String>{

	public SW() {
		this.execute();
	}
	
	protected String doInBackground() {
		
		method();
		
		return null;
	}
	
	public abstract void method();
	
	
}
