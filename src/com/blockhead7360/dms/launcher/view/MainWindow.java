package com.blockhead7360.dms.launcher.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.blockhead7360.dms.launcher.DMSLauncher;
import com.blockhead7360.dms.launcher.Login;
import com.blockhead7360.dms.launcher.Update;
import com.blockhead7360.dms.launcher.files.FM;
import com.blockhead7360.dms.launcher.internet.ScriptReader;
import com.blockhead7360.dms.launcher.utilities.Fonts;
import com.blockhead7360.dms.launcher.utilities.SW;
import com.blockhead7360.dms.launcher.utilities.U;

public class MainWindow {

	boolean loggingIn = true;

	public static final int WIDTH = 1000, HEIGHT = 700;
	public static final int TAB_WIDTH = 1000, TAB_HEIGHT = 580;

	public JFrame frame;
	public static JTabbedPane tabs;
	public static JLabel topLeft;

	public void loadWindow() {
		frame = new JFrame("DMSLauncher");
		frame.setSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.addWindowListener(new OnExit());
		frame.setLayout(null);

	}

	public void update(Update update) {

		frame.setContentPane(update);
		frame.revalidate();
		frame.setVisible(true);

	}

	public void login(Login login) {

		loggingIn = true;

		frame.setContentPane(login);

		frame.revalidate();

		frame.setVisible(true);

	}

	public void load() {

		Container container = new Container();
		container.setLayout(null);

		loggingIn = false;

		tabs = new JTabbedPane();
		tabs.setBounds(new Rectangle(0, 100, TAB_WIDTH, TAB_HEIGHT));
		if (DMSLauncher.darkMode) tabs.setForeground(Color.white);
		else tabs.setForeground(Color.black);
		
		tabs.setFont(new Font("arial", Font.BOLD, 12));
//		tabs.addChangeListener(new ChangeListener() {
//			public void stateChanged(ChangeEvent e) {
//
//				updateTabColor();
//
//			}
//		});

		JPanel pane = new JPanel();
		pane.setLayout(null);

		frame.setIconImage(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource("res/dmslaunchericon.png")));

		JLabel title_image;
		try {
			title_image = new JLabel(new ImageIcon(ImageIO.read(new URL(ScriptReader.image))));
			title_image.setBounds(0, -300, 1000, 700);
		} catch (IOException e) {
			title_image = new JLabel("Unable to load image.", 0);
			title_image.setBounds(0, -300, 1000, 700);

			title_image.setFont(Fonts.bold12a());
		}
		container.add(title_image);

		topLeft = U.l("Created by Dilan N - blockhead7360.com", Fonts.italic12a(), 10, 75, 1000, 30);
		container.add(topLeft);

		container.add(U.cL("v" + DMSLauncher.version, Fonts.bold16(), 650, 70, 325, 30));
		container.add(U.cL("Connected to " + DMSLauncher.server, Fonts.bold12(), 650, 100, 325, 10));

		for (TabbedWindowStorage tws : Window.tabs) {
			tabs.addTab(tws.getTitle(), null, tws.getPane(), tws.getDesc());
		}
		
		//updateTabColor();

		container.add(tabs);

		frame.setContentPane(container);
		frame.revalidate();
		frame.setVisible(true);



	}

	public JTabbedPane getTabs() {
		return tabs;
	}

	public void disableTabs() {
		tabs.setEnabled(false);
	}
	public void enableTabs() {
		tabs.setEnabled(true);
	}

//	public void updateTabColor() {
//
//		if (U.isMacOS()) {
//			for (int i = 0; i < tabs.getTabCount(); i++) {
//
//				if (tabs.getSelectedIndex() == i) tabs.setForegroundAt(i, Color.black);
//				else tabs.setForegroundAt(i, Color.darkGray);
//
//			}
//		}
//
//	}

	public void saveAndExit(boolean shutdown) {

		new SW() {

			public void method() {

				frame.setVisible(false);
				frame.dispose();

				LoadingWindow w = new LoadingWindow("closing");

				FM.saveConfig();

				U.pause(100L);

				if (shutdown) System.exit(0);
				else {
					w.delete();
				}
			}
		};
	}

	public class OnExit implements WindowListener {

		public void windowClosing(WindowEvent e) {

			if (!loggingIn) {
				if (!tabs.isEnabled()) {
					int result = U.qD("Exit DMSLauncher?",
							"The DMSLauncher is busy doing something so it's a TERRIBLE idea\nto try and exit it right now (file corruption possible).",
							"You're right, cancel.", "You're right, cancel.", "Shut up and shut down.");
					if (result != 1) {
						return;
					}
				}
			}


			saveAndExit(true);
		}

		public void windowActivated(WindowEvent arg0) {}

		public void windowClosed(WindowEvent arg0) {}

		public void windowDeactivated(WindowEvent arg0) {}

		public void windowDeiconified(WindowEvent arg0) {}

		public void windowIconified(WindowEvent arg0) {}

		public void windowOpened(WindowEvent arg0) {}

	}


}
