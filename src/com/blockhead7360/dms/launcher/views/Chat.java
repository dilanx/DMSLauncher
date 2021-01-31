package com.blockhead7360.dms.launcher.views;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import com.blockhead7360.dms.launcher.DMSLauncher;
import com.blockhead7360.dms.launcher.utilities.Fonts;
import com.blockhead7360.dms.launcher.utilities.U;
import com.blockhead7360.dms.launcher.view.Window;

public class Chat extends Window {

	public static List<String> currentChat;
	public static List<String> totalChat;
	
	public static String user;
	public static JTextArea chatBox;
	public static JLabel chatSending;
	
	
	public Chat(DMSLauncher main) {
		super(main, "Chat", "Chat with other clients");
		
		/* DELETE THIS WHEN CHAT IS IMPLEMENTED - Start*/
		
		JLabel na = U.cL("This feature hasn\'t been fully implemented in v1.7.X.X yet", Fonts.plain16(), 0, 580/2-20, 1000, 20);
		JLabel aka = U.cL("(a.k.a I don\'t feel like finishing this rn)", Fonts.plain16(), 0, 580/2, 1000, 20);
		pane.add(na);
		pane.add(aka);
				
		/* End */
		
		//register(this);
		
	}

	
	
	

	
}
