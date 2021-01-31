package com.blockhead7360.dms.launcher.views;

import javax.swing.JLabel;

import com.blockhead7360.dms.launcher.DMSLauncher;
import com.blockhead7360.dms.launcher.utilities.Fonts;
import com.blockhead7360.dms.launcher.utilities.U;
import com.blockhead7360.dms.launcher.view.Window;

public class Manage extends Window{

	
	public Manage(DMSLauncher main) {
		super(main, "Manage Server", "Manage server files");
		
		JLabel na = U.cL("This feature hasn\'t been fully implemented in v1.7.X.X yet", Fonts.plain16(), 0, 580/2-20, 1000, 20);
		JLabel aka = U.cL("(a.k.a I don\'t feel like finishing this rn)", Fonts.plain16(), 0, 580/2, 1000, 20);
		pane.add(na);
		pane.add(aka);
				
		//register(this);
	}
	
}
