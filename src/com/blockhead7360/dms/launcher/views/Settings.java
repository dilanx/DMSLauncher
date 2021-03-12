package com.blockhead7360.dms.launcher.views;

import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.blockhead7360.dms.launcher.DMSLauncher;
import com.blockhead7360.dms.launcher.Login;
import com.blockhead7360.dms.launcher.files.FM;
import com.blockhead7360.dms.launcher.internet.DMSAccount;
import com.blockhead7360.dms.launcher.internet.ScriptReader;
import com.blockhead7360.dms.launcher.utilities.CrashReport;
import com.blockhead7360.dms.launcher.utilities.ErrorCode;
import com.blockhead7360.dms.launcher.utilities.Fonts;
import com.blockhead7360.dms.launcher.utilities.U;
import com.blockhead7360.dms.launcher.view.MainWindow;
import com.blockhead7360.dms.launcher.view.Window;

public class Settings extends Window {

	public static DMSAccount myAccount;

	public Settings(DMSLauncher main) {
		super(main, "Account and Settings", "View your account details and the settings");

		boolean user = false;
		myAccount = null;
		
		for (DMSAccount dmsa : ScriptReader.accounts) {
			if (dmsa.getId().equalsIgnoreCase(DMSLauncher.account)) {
				myAccount = dmsa;
				user = true;
			}
		}

		JLabel userHeadImage;
		try {
			userHeadImage = new JLabel(new ImageIcon(ImageIO.read(new URL("https://minotar.net/avatar/" + (user ? myAccount.getUsername() : "0") + "/128.png"))), 0);
			userHeadImage.setBounds(50, 50, 128, 128);
		} catch (IOException e) {
			userHeadImage = new JLabel("Unable to load image!", 0);
			userHeadImage.setBounds(50, 50, 128, 128);
			userHeadImage.setFont(Fonts.bold12a());
		}

		userHeadImage.setVisible(true);
		pane.add(userHeadImage);
		
		
		String text = (user ? 
				"Hello, " + myAccount.getId().split("\\.")[0] + "!" : "Hello!");
		
		JLabel hello = new JLabel(text);
		hello.setFont(Fonts.bold22());
		hello.setBounds(new Rectangle(200, 84, 300, 30));
		hello.setVisible(true);
		pane.add(hello);

		JLabel username = new JLabel((user ? myAccount.getUsername() : ""));
		username.setFont(Fonts.plain16a());
		username.setBounds(new Rectangle(200, 124, 200, 20));
		username.setVisible(true);
		pane.add(username);


		JLabel paymentStatus = new JLabel("Payment Status", 0);
		paymentStatus.setFont(Fonts.bold16a());
		paymentStatus.setBounds(new Rectangle(0, 225, 500, 30));
		paymentStatus.setVisible(true);
		pane.add(paymentStatus);
		
		JLabel status = new JLabel((user ? myAccount.getStatus() : "No User"), 0);
		status.setFont(Fonts.bold48());
		status.setForeground(U.colorTranslate((user ? myAccount.getStatusColor() : "gray")));
		status.setBounds(new Rectangle(0, 275, 500, 50));
		status.setVisible(true);
		pane.add(status);

		JLabel lastPaymentMade = new JLabel("Last Payment Made", 0);
		lastPaymentMade.setFont(Fonts.bold16a());
		lastPaymentMade.setBounds(new Rectangle(0, 355, 250, 30));
		lastPaymentMade.setVisible(true);
		pane.add(lastPaymentMade);

		JLabel lastPay = new JLabel((user ? myAccount.getLastPay() : "N/A"), 0);
		lastPay.setFont(Fonts.plain24a());
		lastPay.setBounds(new Rectangle(0, 385, 250, 50));
		lastPay.setVisible(true);
		pane.add(lastPay);

		JLabel nextPaymentDue = new JLabel("Next Payment Due", 0);
		nextPaymentDue.setFont(Fonts.bold16a());
		nextPaymentDue.setBounds(new Rectangle(250, 355, 250, 30));
		nextPaymentDue.setVisible(true);
		pane.add(nextPaymentDue);

		JLabel nextPay = new JLabel((user ? myAccount.getNextPay() : "N/A"), 0);
		nextPay.setFont(Fonts.plain24a());
		nextPay.setBounds(new Rectangle(250, 385, 250, 50));
		nextPay.setVisible(true);
		pane.add(nextPay);

		
		JPanel settings = new JPanel();
		GridLayout gl = new GridLayout(0, 1);
		gl.setHgap(20);
		gl.setVgap(50);
		settings.setLayout(gl);
		settings.setBounds(new Rectangle(MainWindow.TAB_WIDTH - 375, MainWindow.TAB_HEIGHT / 2 - 250, 325, 450));
		
		Border border = BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED),
				"Settings and Stuff", TitledBorder.CENTER, TitledBorder.TOP);
		settings.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		JButton openGameFolder = new JButton("Open game folder");
		if (!main.isGameInstalled()) {
			openGameFolder.setEnabled(false);
			openGameFolder.setToolTipText("Install your game first.");
		}
		openGameFolder.addActionListener(new OpenGameFolder());
		settings.add(openGameFolder);

		JButton switchServer = new JButton("Switch server");
		switchServer.addActionListener(new SwitchServer());
		settings.add(switchServer);

		JButton switchAppearance = new JButton("Change appearance to " + (DMSLauncher.darkMode ? "light" : "dark") + " mode");
		switchAppearance.addActionListener(new SwitchAppearance());
		settings.add(switchAppearance);

		JButton about = new JButton("About the DMSLauncher");
		about.addActionListener(new About());
		settings.add(about);
		
		pane.add(settings);
		
		JButton accountInfo = new JButton("View your DMSLauncher ID");
		accountInfo.setBounds(new Rectangle(50, MainWindow.TAB_HEIGHT - 100, 200, 20));
		if (!user) accountInfo.setEnabled(false);
		accountInfo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				U.iD("Your DMSLauncher ID", myAccount.getId(), null);
				
			}
			
		});
		pane.add(accountInfo);

		register(this);
	}

	public class OpenGameFolder implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			try {
				Desktop.getDesktop().open(new File(DMSLauncher.gameDir));
			} catch (IOException e1) {
				CrashReport.error(e1, ErrorCode.OPEN_FILE, false);
			}

		}

	}

	public class SwitchServer implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int confirm = U.qD("Log out", "Are you sure you want to log out?", "No, cancel.", "No, cancel.", "Yes, log out.");

			if (confirm == 1) {

				FM.config.remove("server_name");
				FM.config.remove("server_pass");
				FM.config.remove("server_account");

				DMSLauncher.getWindow().login(new Login(main, ScriptReader.getServers()));

			}

		}

	}
	
	public class SwitchAppearance implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			int confirm = U.qD("Change appearance", "Changing the appearance requires an application restart.", "Continue", "Continue", "Cancel");
			
			if (confirm == 0) {
				
				FM.config.setProperty("dark_mode", Boolean.toString(!DMSLauncher.darkMode));
				DMSLauncher.getWindow().saveAndExit(true);
				
			}
			
		}
		
	}

	public class About implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			int confirm = U.iD("DMSLauncher", "DMSLauncher version " + DMSLauncher.version + " by Dilan N", "Close", "Close", "blockhead7360.com/dmslauncher");

			if (confirm == 1) {

				try {
					Desktop.getDesktop().browse(new URI("https://blockhead7360.com/dmslauncher"));
				} catch (IOException | URISyntaxException e1) {
					CrashReport.error(e1, ErrorCode.OPEN_LINK, false);
				}

			}

		}

	}

}
