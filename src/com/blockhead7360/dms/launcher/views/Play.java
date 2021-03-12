package com.blockhead7360.dms.launcher.views;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import com.blockhead7360.dms.launcher.DMSLauncher;
import com.blockhead7360.dms.launcher.GameInstallation;
import com.blockhead7360.dms.launcher.GamePlay;
import com.blockhead7360.dms.launcher.GamePlay.UpdateMode;
import com.blockhead7360.dms.launcher.files.FM;
import com.blockhead7360.dms.launcher.internet.FilesUtility;
import com.blockhead7360.dms.launcher.internet.InternetReader;
import com.blockhead7360.dms.launcher.internet.ScriptReader;
import com.blockhead7360.dms.launcher.utilities.CircleIndicator;
import com.blockhead7360.dms.launcher.utilities.CrashReport;
import com.blockhead7360.dms.launcher.utilities.ErrorCode;
import com.blockhead7360.dms.launcher.utilities.Fonts;
import com.blockhead7360.dms.launcher.utilities.SW;
import com.blockhead7360.dms.launcher.utilities.U;
import com.blockhead7360.dms.launcher.view.Window;

public class Play extends Window{

	public static JTextArea changelog;
	public static JTextArea console;
	public static JProgressBar progressBar1, progressBar2;

	private Play playClass;

	public JButton play;
	public JButton ramAllocate;
	public JButton loadOptions;

	public JLabel updatetext;

	public JLabel statusOnline;
	public JLabel playerCount;
	public JLabel playerList;
	public CircleIndicator statusIndicator;
	public JButton statusRefresh;

	public Play(DMSLauncher main, String changelogText, String consoleText) {
		super(main, "Play", "View the changelog and console - Play the game");
		changelog = new JTextArea();
		changelog.setMargin(new Insets(20, 20, 20, 20));
		changelog.setBounds(new Rectangle(25, 5, 600, 400));
		changelog.setFont(Fonts.plain14());
		changelog.setEditable(false);
		changelog.setText(changelogText);
		changelog.setVisible(true);
		JScrollPane changelog_pane = new JScrollPane(changelog);
		changelog_pane.setBounds(new Rectangle(25, 5, 600, 400));
		pane.add(changelog_pane);

		console = new JTextArea();
		console.setMargin(new Insets(8, 8, 8, 8));
		console.setBounds(new Rectangle(650, 5, 325, 400));
		console.setFont(Fonts.plain12());
		console.setEditable(false);
		console.setText(consoleText);
		console.setVisible(true);
		DefaultCaret caret = (DefaultCaret) console.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane console_pane = new JScrollPane(console);
		console_pane.setBounds(new Rectangle(650, 5, 325, 400));
		pane.add(console_pane);

		progressBar1 = new JProgressBar();
		progressBar1.setBounds(650, 410, 325, 20);
		progressBar1.setMaximum(1);
		progressBar1.setValue(0);
		progressBar1.setVisible(true);
		pane.add(progressBar1);

		progressBar2 = new JProgressBar();
		progressBar2.setBounds(650, 435, 325, 20);
		progressBar2.setMaximum(1);
		progressBar2.setValue(0);
		progressBar2.setVisible(true);
		pane.add(progressBar2);


		if (!main.isGameInstalled()) {
			play = new JButton("Install and Play");
		}else {
			play = new JButton("Play Minecraft");
		}

		play.setBounds(new Rectangle(748, 468, 220, 50));
		play.setFont(Fonts.plain16());
		play.addActionListener(new PlayButton());
		play.setVisible(true);

		pane.add(play);

		String text = null;

		if (DMSLauncher.account == null) {

			text = "Hello!";

		} else {

			text = "Hello " + DMSLauncher.account.split("\\.")[0] + "!";

		}
		updatetext = U.rL(text, Fonts.bold14a(), 450, 480, 245, 40);
		pane.add(updatetext);

		statusOnline = U.l("Unable to retreive server status", Fonts.bold22a(), 40, 405, 570, 50);
		statusOnline.setForeground(Color.gray);
		pane.add(statusOnline); 

		playerCount = U.l(" ", Fonts.bold16a(), 205, 405, 405, 50);
		if (DMSLauncher.darkMode) playerCount.setForeground(Color.lightGray); 
		else playerCount.setForeground(Color.darkGray);

		pane.add(playerCount);

		playerList = U.l(" ", Fonts.plain12a(), 45, 425, 560, 50);
		pane.add(playerList);

		statusIndicator = new CircleIndicator();
		statusIndicator.setBounds(new Rectangle(25, 424, 10, 10));
		pane.add(statusIndicator);


		statusRefresh = new JButton("Refresh Status");
		statusRefresh.setFont(Fonts.plain12a());
		statusRefresh.setBounds(new Rectangle(455, 420, 150, 20));
		statusRefresh.addActionListener(new RefreshStatus());
		pane.add(statusRefresh);

		JButton newInVersion = new JButton("New in v" + DMSLauncher.version);
		newInVersion.setFont(Fonts.plain12a());
		newInVersion.setBounds(new Rectangle(455, 450, 150, 20));
		newInVersion.addActionListener(new NewInVersion());
		pane.add(newInVersion);

		refreshStatus();

		/* COMMENTED OUT - A */

		ramAllocate = new JButton("RAM Allocation");
		ramAllocate.setFont(Fonts.plain14());
		ramAllocate.setBounds(new Rectangle(20, 478, 220, 30));
		ramAllocate.addActionListener(new RAMAllocateButton());
		ramAllocate.setVisible(true);
		if (!main.isGameInstalled()) {
			ramAllocate.setEnabled(false);
			ramAllocate.setToolTipText("Install your game first.");
		}
		pane.add(ramAllocate);

		loadOptions = new JButton("Download Options");
		loadOptions.setFont(Fonts.plain14());
		loadOptions.setBounds(new Rectangle(260, 478, 220, 30));
		loadOptions.addActionListener(new LoadOptions());
		loadOptions.setVisible(true);
		if (!main.isGameInstalled()) {
			loadOptions.setEnabled(false);
			loadOptions.setToolTipText("Install your game first.");
		}
		pane.add(loadOptions);

		register(this);

		this.playClass = this;

	}

	public void addToConsole(String text) {
		console.setText(console.getText() + "\n" + text);
		console.setCaretPosition(Math.max(console.getText().lastIndexOf("\n"), 0));
	}

	public void addToChangelog(String text) {
		changelog.setText(changelog.getText() + text);
	}

	public void disableButtons() {
		play.setEnabled(false);
		ramAllocate.setEnabled(false);
		loadOptions.setEnabled(false);
	}

	public void enableButtons() {
		play.setEnabled(true);
		ramAllocate.setEnabled(true);
		loadOptions.setEnabled(true);
	}

	public void refreshStatus() {
		try {


			if (ScriptReader.disableServerIndicator) {

				statusOnline.setText("Server status unavailable");
				playerCount.setText(" ");
				playerList.setText("The administrator has temporarily disabled this feature.");
				statusOnline.setForeground(Color.gray);
				statusIndicator.setForeground(Color.gray);
				statusRefresh.setEnabled(false);

				return;

			}			

			statusOnline.setText("Loading...");
			statusOnline.setForeground(Color.gray);

			JsonObject query = InternetReader.getServerQuery("https://api.mcsrvstat.us/2/" + ScriptReader.serverIP);

			if (query == null) {

				statusOnline.setText("Server status unavailable");
				playerCount.setText(" ");
				playerList.setText("Unable to retrieve server status.");
				statusOnline.setForeground(Color.gray);
				statusIndicator.setForeground(Color.gray);

				return;

			}


			if (query.getBoolean("online")) {

				statusOnline.setText("Server Online");
				if (DMSLauncher.darkMode) statusOnline.setForeground(Color.white); 
				else statusOnline.setForeground(Color.black);
				statusIndicator.setForeground(new Color(46, 204, 64));
				int playersOnline = query.getJsonObject("players").getInt("online");
				if (playersOnline == 0) {
					playerCount.setText("No players online");
					playerList.setText(" ");
				} else {
					playerCount.setText(playersOnline + " " + (playersOnline == 1 ? "player" : "players") + " online");

					JsonArray array = query.getJsonObject("players").getJsonArray("list");

					if (array == null) {

						playerList.setText("Unable to retrieve player names.");

					} else {

						int length = array.size();

						if (length > 0) {

							String list = array.getString(0);

							for (int i = 1; i < length; i++) {

								list += ", " + array.getString(i);

							}

							playerList.setText(list);

						}

					}

				}

			} else {

				statusOnline.setText("Server Offline");
				if (DMSLauncher.darkMode) statusOnline.setForeground(Color.white); 
				else statusOnline.setForeground(Color.black);
				statusIndicator.setForeground(Color.red);
				playerCount.setText(" ");
				playerList.setText(" ");

			}

		} catch (Exception e) {
			e.printStackTrace();
			statusOnline.setText("Unable to retrieve server status");
			playerCount.setText(" ");
			statusOnline.setForeground(Color.gray);
			statusIndicator.setForeground(Color.gray);
		}
	}

	public class LoadOptions implements ActionListener{

		public void fromInstallation() {

			options(true);

		}

		public void actionPerformed(ActionEvent e) {

			options(false);

		}

		public void options(boolean fromInstallation) {

			disableButtons();

			Map<String, String> optionsMap = ScriptReader.options;

			String def = "Default options and keybinds";

			String[] options = new String[optionsMap.keySet().size() + 1];

			options[0] = def;

			int i = 1;
			for (String key : optionsMap.keySet()) {

				options[i] = key;
				i++;

			}

			String s = (String) JOptionPane.showInputDialog(DMSLauncher.getWindow().frame, "Select an options/keybinds preset to download:" + (fromInstallation ? "" : "\n\n(WARNING: Your previous options and keybinds will be permanently deleted.)"), "Download Options / Keybinds", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (s == null) {

				if (fromInstallation) options(true);

				enableButtons();

				return;

			}

			if (s.equals(def)) {

				File oldOptions = new File(FM.base, DMSLauncher.server + File.separator + "Game" + File.separator + "options.txt");

				if (oldOptions.exists()) oldOptions.delete();

				addToConsole("\nUsing default options.");

			} else {

				try {
					File newOptions = new File(FM.base, DMSLauncher.server + File.separator + "Game" + File.separator + "options_dl.txt");
					FilesUtility.download(new URL(optionsMap.get(s)), newOptions.getPath());

					File oldOptions = new File(FM.base, DMSLauncher.server + File.separator + "Game" + File.separator + "options.txt");

					if (oldOptions.exists()) oldOptions.delete();
					newOptions.renameTo(oldOptions);

				} catch (IOException e) {
					CrashReport.error(e, ErrorCode.INTERNET_DOWNLOAD, false);
					enableButtons();
					return;
				}

				addToConsole("\nDownloaded options:\n" + s);

			}

			enableButtons();

			if (fromInstallation) {
				new SW() {
					public void method() {
						GamePlay gp = new GamePlay(playClass);
						gp.play(UpdateMode.PLAY);
					}
				};
			}

		}

	}

	public class RAMAllocateButton implements ActionListener{

		public void fromInstallation() {

			ram(true);

		}

		public void actionPerformed(ActionEvent e) {

			ram(false);

		}

		public void ram(boolean fromInstallation) {

			disableButtons();

			String[] rams = new String[]{"2048 MB", "2304 MB", "2560 MB", "2816 MB", "3072 MB", "3328 MB", "3584 MB", "3840 MB", "4096 MB", "4352 MB", "4608 MB", "4864 MB", "5120 MB", "5376 MB", "5632 MB", "5888 MB", 
					"6144 MB", "6400 MB", "6656 MB", "6912 MB", "7168 MB", "7424 MB", "7680 MB", "7936 MB", "8192 MB", "8448 MB", "8704 MB", "8960 MB", "9216 MB", "9472 MB", "9728 MB", "9984 MB", "10240 MB", "10496 MB", 
					"10752 MB", "11008 MB", "11264 MB", "11520 MB"};

			try {


				List<String> lines = Files.readAllLines(new File(new File(FM.base, DMSLauncher.server + File.separator + "Game"), "launcher_profiles.json").toPath());
				String j = null;
				String full = null;
				int i = -1;
				for (String s : lines){
					i++;
					if (s.contains("-Xmx")){
						full = s;
						j = s.split("-Xmx")[1].split("m")[0];				
						break;
					}
				}

				if (full == null || j == null) {

					CrashReport.error(null, ErrorCode.RAM_FIND, false);
					enableButtons();
					return;

				}

				String s = (String) JOptionPane.showInputDialog(DMSLauncher.getWindow().frame, "Select RAM allocation towards Minecraft:", "Minecraft RAM Allocation", JOptionPane.QUESTION_MESSAGE, null, rams, j + " MB");
				if (s == null) {

					if (fromInstallation) ram(true);

					enableButtons();

					return;

				}
				lines.set(i, full.replace(j, s.replace(" MB", "")));
				FM.clear(new File(new File(FM.base, DMSLauncher.server + File.separator + "Game"), "launcher_profiles.json"));
				Files.write(new File(new File(FM.base, DMSLauncher.server + File.separator + "Game"), "launcher_profiles.json").toPath(), lines, StandardOpenOption.WRITE);
				addToConsole("\nChanged Minecraft RAM allocation:\n" + s);

				enableButtons();

				if (fromInstallation) {
					LoadOptions options = new LoadOptions();
					options.fromInstallation();
					return;
				}

			} catch (IOException ex) {
				CrashReport.error(ex, ErrorCode.RAM, false);
				enableButtons();
				return;
			}
		}

	}

	public class NewInVersion implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			DMSLauncher.newInVersion(main);

		}
	}

	public class RefreshStatus implements ActionListener{
		public void actionPerformed(ActionEvent e) {

			new SW() {
				public void method() {
					refreshStatus();
				}
			};

		}
	}

	public class PlayButton implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if (ScriptReader.disablePlay != null) {
				U.iD("DMSLauncher", ScriptReader.disablePlay, null);
				return;
			}

			if (Settings.myAccount != null) {
				if (Settings.myAccount.getBlockButton().equalsIgnoreCase("yes")) {
					U.iD("DMSLauncher", Settings.myAccount.getBlockReason(), null);
					return;
				}
			}


			new SW() {
				public void method() {

					if (!main.isGameInstalled()) {
						GameInstallation installation = new GameInstallation(playClass);
						installation.install();

					}
					else {
						GamePlay gp = new GamePlay(playClass);
						gp.checkForUpdates(UpdateMode.PLAY);
					}
				}
			};



		}

	}

}
