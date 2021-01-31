package com.blockhead7360.dms.launcher.views;

public class SettingsOLD /*extends Window*/ {

//	public static boolean disableSection_general = false;
//	public static boolean developerMode = false;
//
//	public static JLabel currentGameDirectory;
//
//
//	private JButton devMode;
//	private Color devMode_button_color;
//	private String devMode_changelog, devMode_console, devMode_topleft;
//	
//	private Play playFrame;
//	
//	public SettingsOLD(DMSLauncher main, Play playFrame) {
//		super(main, "Settings", "Change program and game settings");
//		
//		this.playFrame = playFrame;
//
//		JLabel tip = U.cL("Hover over buttons to see descriptions", Fonts.italic10a(), 0, 10, 1000, 10);
//		pane.add(tip);
//
//		JLabel currentVersions = null;
//		File versionsFolder = new File(FM.base, main.server + File.separator + "Game" + File.separator + "versions");
//		if (versionsFolder.exists()) {
//			for (File f : versionsFolder.listFiles()) {
//				if (f.isDirectory() && f.getName().contains("forge")) {
//
//					String mcVer = f.getName().split("forge")[1].split("-")[0];
//					String forgeVer = f.getName().split("forge")[1].split("-")[1];
//
//					currentVersions = U.cL("Minecraft Version: " + mcVer + "            Forge Version: " + forgeVer + "            DMSLauncher Version: " + DMSLauncher.version, Fonts.plain12(), 0, 430, 1000, 15);
//				}
//			}
//		}else {
//			currentVersions = U.cL("(Install the game to view Minecraft and Minecraft Forge versions)            DMSLauncher Version: " + DMSLauncher.version, Fonts.plain12(), 0, 430, 1000, 15);
//		}
//		pane.add(currentVersions);
//
//		currentGameDirectory = U.cL("Game Directory: " + main.gameDir, Fonts.plain12(), 0, 450, 1000, 15);
//		pane.add(currentGameDirectory);
//
//		JLabel setup_selectionTitle = U.cL("Setup", Fonts.bold18a(), 1000-10-325, 50, 300, 20);
//		pane.add(setup_selectionTitle);
//
//		JButton setup_deleteGameFiles = new JButton("Delete Game Files");
//		setup_deleteGameFiles.setToolTipText("Delete the game files to reinstall fresh.");
//		setup_deleteGameFiles.setBounds(new Rectangle(1000-10-325, 135, 300, 50));
//		setup_deleteGameFiles.addActionListener(new DeleteGameFiles());
//		pane.add(setup_deleteGameFiles);
//
//		JButton setup_changeRunpath = new JButton("Change Game Directory");
//		setup_changeRunpath.setToolTipText("If you needed to run the game out of a different directory, change it here so the program"
//				+ " still updates the mod selection");
//		setup_changeRunpath.setBounds(new Rectangle(1000-10-325, 75, 300, 50));
//		setup_changeRunpath.addActionListener(new ChangeGameDirectory());
//		pane.add(setup_changeRunpath);
//
//		JButton setup_openGameFolder = new JButton("Open Game Folder");
//		setup_openGameFolder.setToolTipText("Open the directory containing the client files");
//		setup_openGameFolder.setBounds(new Rectangle(1000-10-325, 195, 300, 50));
//		setup_openGameFolder.addActionListener(new OpenGameFolder());
//		pane.add(setup_openGameFolder);
//
//		JLabel general_selectionTitle = U.cL("General", Fonts.bold18a(), 350, 50, 300, 20);
//		pane.add(general_selectionTitle);
//
//		JButton general_switchServer = new JButton("Switch Server");
//		general_switchServer.setToolTipText("Switch to the launcher for a different server");
//		general_switchServer.setBounds(new Rectangle(350, 75, 300, 50));
//		general_switchServer.addActionListener(new SwitchServer());
//		pane.add(general_switchServer);
//
//		JButton general_updateOnly = new JButton("Update Without Launching");
//		general_updateOnly.setToolTipText("Check for updates and update without launching the Minecraft launcher");
//		general_updateOnly.setBounds(new Rectangle(350, 135, 300, 50));
//		general_updateOnly.addActionListener(new UpdateOnly());
//		if (!main.isGameInstalled()) general_updateOnly.setEnabled(false);
//		pane.add(general_updateOnly);
//
//		JLabel support_sectionTitle = U.cL("Support", Fonts.bold18a(), 10, 50, 300, 20);
//		pane.add(support_sectionTitle);
//
//		JButton support_dmswebsite = new JButton("DMSLauncher Website");
//		support_dmswebsite.setToolTipText("Visit the DMSLauncher website (http://dilanmail.com/dmslauncher)");
//		support_dmswebsite.setBounds(new Rectangle(10, 75, 300, 50));
//		support_dmswebsite.addActionListener(new DMSWebsite());
//		pane.add(support_dmswebsite);
//
//		JButton support_email = new JButton("Email Dilan");
//		support_email.setToolTipText("Send an email to support@dilanmail.com using your mail client");
//		support_email.setBounds(new Rectangle(10, 135, 300, 50));
//		support_email.addActionListener(new EmailSupport());
//		pane.add(support_email);
//
//		JButton support_discord = new JButton("Discord");
//		support_discord.setToolTipText("Join the Dilcord City Discord server");
//		support_discord.setBounds(new Rectangle(10, 195, 300, 50));
//		support_discord.addActionListener(new Discord());
//		pane.add(support_discord);
//
//		JButton credit = new JButton("DMSLauncher Information");
//		credit.setToolTipText("View creator information and more");
//		credit.setBounds(new Rectangle(MainWindow.TAB_WIDTH/2 - 300 - 25, MainWindow.TAB_HEIGHT - 100, 300, 50));
//		credit.addActionListener(new Credit());
//		pane.add(credit);
//		
//		devMode = new JButton("Developer Mode");
//		devMode.setToolTipText("Enter and exit Developer Mode");
//		devMode.setBounds(new Rectangle(MainWindow.TAB_WIDTH/2 + 25, MainWindow.TAB_HEIGHT - 100, 300, 50));
//		devMode.addActionListener(new DeveloperMode());
//		pane.add(devMode);
//
//		if (disableSection_general) {
//			general_switchServer.setEnabled(false);
//			general_updateOnly.setEnabled(false);
//		}
//
//
//		register(this);
//
//	}
//	
//	public class OpenGameFolder implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//
//			try {
//				Desktop.getDesktop().open(new File(DMSLauncher.gameDir));
//			} catch (IOException e1) {
//				new CrashReport(e1, ErrorCode.FILE_LAUNCH);
//			}
//
//		}
//	}
//
//	public class DeveloperMode implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			new SW() {
//				public void method() {
//					if (!developerMode) {
//						devMode_button_color = devMode.getBackground();
//
//						devMode.setBackground(Color.red);
//						devMode.setOpaque(true);
//
//						devMode_topleft = MainWindow.topLeft.getText();
//						MainWindow.topLeft.setText("DEVELOPER MODE");
//						MainWindow.topLeft.setFont(Fonts.bold12a());
//						MainWindow.topLeft.setForeground(Color.red);
//						developerMode = true;
//
//						devMode_changelog = Play.changelog.getText();
//						devMode_console = Play.console.getText();
//
//						Play.changelog.setEditable(true);
//						Play.console.setEditable(true);
//						Server.ruleAbout.setEditable(true);
//
//					}else {
//
//						int i = U.iD("DMSLauncher Developer Mode", "Obtain DilanScripts or exit developer mode?", "Exit", "Exit", "Obtain DilanScripts");
//						if (i == 0) {
//							developerMode = false;
//							Play.changelog.setEditable(false);
//							Play.changelog.setText(devMode_changelog);
//							Play.console.setEditable(false);
//							Play.console.setText(devMode_console);
//							Server.ruleAbout.setEditable(false);
//							Server.ruleSelect.setSelectedIndex(0);
//							Server.ruleAbout.setText(Server.ruleAbout_placeholder);
//							devMode.setBackground(devMode_button_color);
//							devMode.setOpaque(false);
//							MainWindow.topLeft.setText(devMode_topleft);
//							MainWindow.topLeft.setFont(Fonts.italic12a());
//							MainWindow.topLeft.setForeground(Color.black);
//							return;
//						}
//						else {
//							String dschangelog = "iiChangelogii-" + Play.changelog.getText().replaceAll("\n", "iiNLii") + "iiStopii-";
//							Play.changelog.setText(dschangelog);
//
//							String dsruleabout = Server.ruleAbout.getText().replaceAll("\n", "/n");
//							Server.ruleAbout.setText(dsruleabout);
//							return;
//						}
//
//					}
//
//				}
//			};
//		}
//	}
//
//	public class DeleteGameFiles implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			new SW() {
//				public void method() {
//
//					List<String> serverFolders = new ArrayList<String>();
//					for (File f : FM.base.listFiles()) {
//						if (f.isDirectory()) {
//							if (!f.getName().contains("stacktrace") && !f.getName().contains("programjar")) {
//								if (new File(f, "Game").exists())
//									serverFolders.add(f.getName());
//							}
//						}
//					}
//					serverFolders.add("Alternate Game Directory");
//
//					String serverFolder = (String) JOptionPane.showInputDialog(null, "Select the game folder to delete", "Settings", JOptionPane.QUESTION_MESSAGE, null, serverFolders.toArray(), serverFolders.toArray()[0]);
//					if (serverFolder == null) return;
//					if (serverFolder.equals("Alternate Game Directory")) {
//						if (!main.isGameInstalled()) {
//							U.iD("Settings", "The game is currently not installed at this directory.", null);
//							return;
//						}
//						int i = U.iD("Settings", "Are you sure you want to delete all game files from\n" + main.gameDir + "?\n\n(You will not be able to recover the deleted files)", "No", "No", "Yes");
//						if (i == 1) {
//							FM.delete(new File(main.gameDir));
//							U.pause(1000L);
//							U.iD("Settings", "Successfully deleted all game files.\n\nPlease restart the launcher.", null);
//							System.exit(0);
//						}
//						return;
//					}
//
//
//
//					int i = U.iD("Settings", "Are you sure you want to delete all game files from " + serverFolder + "?\n\n(You will not be able to recover the deleted files)", "No", "No", "Yes");
//					if (i == 1) {
//						FM.delete(new File(FM.base, serverFolder + File.separator +  "Game"));
//						U.pause(1000L);
//						U.iD("Settings", "Successfully deleted all game files.\n\nPlease restart the launcher.", null);
//						System.exit(0);
//					}
//					/*if (!main.isGameInstalled()) {
//						U.iD("Settings", "The game is currently not installed.", null);
//						return;
//					}
//					int i = U.iD("Settings", "Are you sure you want to delete all game files?\n\n(You will not be able to recover the deleted files)", "No", "No", "Yes");
//					if (i == 1) {
//						FM.delete(new File(main.gameDir));
//						U.pause(1000L);
//						U.iD("Settings", "Successfully deleted all game files.\n\nPlease restart the launcher.", null);
//						System.exit(0);
//					}
//					return;*/
//				}
//			};
//		}
//	}
//
//	public class UpdateOnly implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			new SW(){
//				public void method() {
//
//					if (ScriptReader.disablePlay != null) {
//						U.iD("DMSLauncher", ScriptReader.disablePlay, null);
//						return;
//					}
//
//					MainWindow.tabs.setSelectedIndex(0);
//					GamePlay play = new GamePlay(playFrame);
//					play.checkForUpdates(UpdateMode.SETTINGS);
//				}
//			};
//		}
//	}
//
//	public class Discord implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//
//			try {
//				Desktop.getDesktop().browse(new URI("https://discord.gg/2YzNDEm"));
//			} catch (IOException e1) {
//				new CrashReport(e1, ErrorCode.LINK);
//			} catch (URISyntaxException e1) {
//				new CrashReport(e1, ErrorCode.LINK);
//			}
//
//		}
//	}
//
//	public class EmailSupport implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			try {
//				Desktop.getDesktop().mail(new URI("mailto:support@dilanmail.com?subject=DMSLauncher%20Support"));
//			} catch (IOException e1) {
//				new CrashReport(e1, ErrorCode.MAIL_LINK);
//			} catch (URISyntaxException e1) {
//				new CrashReport(e1, ErrorCode.MAIL_LINK);
//			}
//		}
//	}
//
//	public class DMSWebsite implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			try {
//				Desktop.getDesktop().browse(new URI("http://blockhead7360.com/dmslauncher"));
//			} catch (IOException e1) {
//				new CrashReport(e1, ErrorCode.LINK);
//			} catch (URISyntaxException e1) {
//				new CrashReport(e1, ErrorCode.LINK);
//			}
//		}
//	}
//
//	public class ChangeGameDirectory implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
//			String gamePath = Initializations.setGameDirectory();
//			if (gamePath == null) return;
//			FM.config.setProperty("game_dir", gamePath);
//			main.gameDir = gamePath;
//			currentGameDirectory.setText("Game Directory: " + gamePath);
//			U.iD("Settings", "Successfully changed the game directory to\n" + gamePath, null);
//		}
//	}
//
//	public class SwitchServer implements ActionListener{
//		public void actionPerformed(ActionEvent e) {
////			String server = Initializations.setServer(main);
////			if (server == null) return;
////			FM.config.setProperty("server_name", server);
////			FM.config.setProperty("server_pass", DMSLauncherPasswordEncoder.decode(ScriptReader.getServers(main).get(server)));
////			U.iD("Settings", "The server has been changed. Restart the application for the changes to take effect.", null);
////			main.getWindow().saveAndExit(true);
//		}
//	}
//
//	public class Credit implements ActionListener{
//
//		public void actionPerformed(ActionEvent e) {
//			int val = U.iD("DMSLauncher", "DMSLauncher v" + DMSLauncher.version + " by Dilan N", "Close", "blockhead7360.com", "Close");
//			/*if (val == 0) {
//				try {
//					Desktop.getDesktop().browse(new URI("https://pastebin.com/PRfcq6Xg"));
//				} catch (IOException e1) {
//					crashReport(e1);
//				} catch (URISyntaxException e1) {
//					crashReport(e1);
//				}
//			}*/
//			if (val == 0) {
//				try {
//					Desktop.getDesktop().browse(new URI("https://blockhead7360.com"));
//				} catch (IOException e1) {
//					new CrashReport(e1, ErrorCode.LINK);
//				} catch (URISyntaxException e1) {
//					new CrashReport(e1, ErrorCode.LINK);
//				}
//			}
//		}
//
//	}


}
