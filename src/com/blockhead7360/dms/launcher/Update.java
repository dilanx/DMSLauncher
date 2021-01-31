package com.blockhead7360.dms.launcher;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.blockhead7360.dms.launcher.internet.InternetReader;
import com.blockhead7360.dms.launcher.utilities.CrashReport;
import com.blockhead7360.dms.launcher.utilities.ErrorCode;
import com.blockhead7360.dms.launcher.utilities.Fonts;
import com.blockhead7360.dms.launcher.utilities.U;
import com.blockhead7360.dms.launcher.view.MainWindow;

public class Update extends Container {
	
	private static final long serialVersionUID = 1L;
	
	private static final String updateUrl = "https://pastebin.com/raw/aTmjN5Cz";
	private boolean available;
	
	public boolean isAvailable() {
		
		return available;
		
	}

	public Update() {
		
		available = false;
		
		String content = InternetReader.readContent(updateUrl);

		String ver = content.split("iiVersionii-")[1].split("iiStopii-")[0];
		
		String changelog = content.split("iiChangelogii-")[1].split("iiStopii-")[0];
		changelog = changelog.replaceAll("iiNLii", "\n");

		String downloadMac = content.split("iiDownloadMacii-")[1].split("iiStopii-")[0];
		String downloadWin = content.split("iiDownloadWindowsii-")[1].split("iiStopii-")[0];
		
		if (!DMSLauncher.version.equals(ver)) {
			
			available = true;
			
			setLayout(null);
			
			JLabel currentVersion = U.l("Current version: " + DMSLauncher.version, Fonts.plain14a(), 10, 10, 300, 30);
			add(currentVersion);
			
			JLabel title = U.cL("A new version of the DMSLauncher is available!", Fonts.bold32a(), 0, 50, MainWindow.WIDTH, 30);
			add(title);
			
			JLabel newVersion = U.cL("version " + ver, Fonts.plain24a(), 0, 100, MainWindow.WIDTH, 30);
			add(newVersion);
			
			JTextArea log = new JTextArea();
			Rectangle bounds = new Rectangle(20, 150, MainWindow.WIDTH - 40, MainWindow.HEIGHT - 250);
			log.setBounds(bounds);
			log.setMargin(new Insets(20, 20, 20, 20));
			log.setText(changelog);
			log.setEditable(false);
			log.setFont(Fonts.plain16());
			JScrollPane log_pane = new JScrollPane(log);
			log_pane.setBounds(bounds);
			add(log_pane);
			
			JLabel desc = U.l("Since version 1.10.1, you can't use the DMSLauncher unless you're on the latest version.", Fonts.plain14a(), 30, MainWindow.HEIGHT - 85, 640, 30);
					add(desc);
			
			JButton update = new JButton("Download new version");
			update.setBounds(new Rectangle(MainWindow.WIDTH - 300, MainWindow.HEIGHT - 90, 200, 40));
			update.setFont(Fonts.plain16a());
			update.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					if (U.isMacOS()) {
						
						try {
							Desktop.getDesktop().browse(new URI(downloadMac));
						} catch (IOException | URISyntaxException e1) {
							CrashReport.error(e1, ErrorCode.OPEN_LINK, true);
						}
						
					} else {
						
						try {
							Desktop.getDesktop().browse(new URI(downloadWin));
						} catch (IOException | URISyntaxException e1) {
							CrashReport.error(e1, ErrorCode.OPEN_LINK, true);
						}
						
					}
					
					System.exit(0);
					
				}
				
			});
			add(update);
			
		}
		
	}

}
