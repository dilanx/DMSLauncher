package com.blockhead7360.dms.launcher.internet;

public class UpdateChecker {

//	private static final String updateUrl = "https://pastebin.com/raw/aTmjN5Cz";
//	
//	public static void checkForUpdates(DMSLauncher main) {
//		String content = InternetReader.readContent(updateUrl);
//
//		String ver = content.split("iiVersionii-")[1].split("iiStopii-")[0];
//		
//		String changelog = content.split("iiChangelogii-")[1].split("iiStopii-")[0];
//		changelog = changelog.replaceAll("iiNLii", "\n");
//
//		String downloadMac = content.split("iiDownloadMacii-")[1].split("iiStopii-")[0];
//		String downloadWin = content.split("iiDownloadWindowsii-")[1].split("iiStopii-")[0];
//		
//		if (!DMSLauncher.version.equals(ver)) {
//			JFrame frame = new JFrame("DMSLauncher program update available!");
//
//			frame.setPreferredSize(new Dimension(400, 600));
//			frame.setMaximumSize(new Dimension(400, 600));
//			frame.setMinimumSize(new Dimension(400, 600));
//			frame.setDefaultCloseOperation(2);
//			frame.setResizable(false);
//			frame.setLocationRelativeTo(null);
//			Container pane = new Container();
//			frame.setLayout(null);
//
//			JLabel label = new JLabel("A new version of DMSLauncher is available!", 0);
//			label.setFont(Fonts.bold18a());
//			label.setBounds(new Rectangle(0, 10, 400, 30));
//			pane.add(label);
//
//			JTextArea area = new JTextArea();
//
//			area.setFont(Fonts.plain14());
//			area.setEditable(false);
//			area.setText(changelog);
//			JScrollPane scroll = new JScrollPane(area);
//			scroll.setBounds(new Rectangle(10, 55, 380, 470));
//			pane.add(scroll);
//
//			JButton button = new JButton("Update this program to v" + ver);
//			button.setFont(Fonts.plain12());
//			button.setBounds(new Rectangle(50, 530, 300, 35));
//			button.addActionListener(new ActionListener()
//			{
//				public void actionPerformed(ActionEvent e)
//				{
//					
//					try {
//						if (U.isMacOS()) {
//							Desktop.getDesktop().browse(new URI(downloadMac));
//						}else {
//							Desktop.getDesktop().browse(new URI(downloadWin));
//						}
//					} catch (Exception e1) {
//						new CrashReport(e1, ErrorCode.LINK);
//					}
//					
//					main.getWindow().saveAndExit(true);
//					
//					/*if (U.isMacOS())
//						externalUpdate(main);
//					else{
//						new SW() {
//							public void method() {
//								frame.setVisible(false);
//								frame.dispose();
//								update(main, ver, download);
//							}
//						};
//					}*/
//				}
//			});
//			pane.add(button);
//
//			frame.setContentPane(pane);
//			frame.setVisible(true);
//		}
//	}
//	
//	public static void deleteOldVersion()
//	{
//		if (DMSLauncher.oldVersion.equals(DMSLauncher.version)) {
//			return;
//		}
//		//File dir = new File(program_jar.getPath());
//		//if (dir.exists())
//		{
//			File[] arrayOfFile;
//			//int j = (arrayOfFile = dir.listFiles()).length;
//			for (int i = 0; i < j; i++)
//			{
//				File f = arrayOfFile[i];
//
//				if (f.getName().startsWith("DMS Launcher " + DMSLauncher.oldVersion) && f.getName().endsWith(".jar")) {
//					f.delete();
//				}
//			}
//		}else {
//		//	dir.mkdir();
//		}
//	}
//	
//	@SuppressWarnings("unused")
//	private static void externalUpdate(DMSLauncher main) {
//		if (U.isMacOS()) {
//			
//			try {
//				
//				Desktop.getDesktop().open(new File(FM.base, "DMSLauncherUpdater.jar"));
//			} catch (IOException e) {
//				new CrashReport(e, ErrorCode.FILE_LAUNCH);
//			}
//			main.getWindow().saveAndExit(true);
//
//			return;
//			
//		}
//			
//	}
//
//	@SuppressWarnings("unused")
//	private static void update(DMSLauncher main, String prgmUpdateTo, String prgmDownload) {
//		main.getWindow().frame.setVisible(false);
//		main.getWindow().frame.dispose();
//
//		JFrame updating = new JFrame("Updating DMSLauncher to v" + prgmUpdateTo);
//		updating.setPreferredSize(new Dimension(400, 100));
//		updating.setMaximumSize(new Dimension(400, 100));
//		updating.setMinimumSize(new Dimension(400, 100));
//		updating.setDefaultCloseOperation(0);
//		updating.setResizable(false);
//		updating.setLocationRelativeTo(null);
//		Container pane = new Container();
//		updating.setLayout(null);
//
//		JLabel updlabel = new JLabel("Downloading new version...", 0);
//		updlabel.setFont(Fonts.bold18a());
//		updlabel.setBounds(new Rectangle(0, 20, 400, 40));
//		pane.add(updlabel);
//
//		updating.setContentPane(pane);
//		updating.setVisible(true);
//
//		boolean pause = true;
//		long old = System.currentTimeMillis();
//		while (pause) {
//			if (System.currentTimeMillis() - old > 1000L) {
//				pause = false;
//			}
//		}
//		try
//		{
//		//	FilesUtility.download(new URL(prgmDownload), program_jar + "/DMS Launcher " + prgmUpdateTo + ".jar");
//		}
//		catch (MalformedURLException e)
//		{
//			new CrashReport(e, ErrorCode.DOWNLOAD_WRITE);			
//			return;
//		}
//		catch (IOException e)
//		{
//			new CrashReport(e, ErrorCode.DOWNLOAD_WRITE);
//			return;
//		}
//		boolean pausex = true;
//		long oldx = System.currentTimeMillis();
//		while (pausex) {
//			if (System.currentTimeMillis() - oldx > 1000L) {
//				pausex = false;
//			}
//		}
//		try
//		{
//		//	Desktop.getDesktop().open(new File(program_jar + "/DMS Launcher " + prgmUpdateTo + ".jar"));
//		}
//		catch (IOException e)
//		{
//			new CrashReport(e, ErrorCode.FILE_LAUNCH);
//			return;
//		}
//		main.getWindow().saveAndExit(true);
//	}

}

