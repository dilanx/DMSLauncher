package com.blockhead7360.dms.launcher;

import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.net.ftp.FTPFile;

import com.blockhead7360.dms.launcher.files.FM;
import com.blockhead7360.dms.launcher.internet.FTP;
import com.blockhead7360.dms.launcher.utilities.CrashReport;
import com.blockhead7360.dms.launcher.utilities.ErrorCode;
import com.blockhead7360.dms.launcher.utilities.Fonts;
import com.blockhead7360.dms.launcher.utilities.SW;
import com.blockhead7360.dms.launcher.utilities.U;
import com.blockhead7360.dms.launcher.views.Play;

public class GamePlay {

	private Play playFrame;

	public GamePlay(Play playFrame) {
		this.playFrame = playFrame;
	}

	public void checkForUpdates(UpdateMode mode) {
		playFrame.disableButtons();
		DMSLauncher.getWindow().disableTabs();


		if (mode == UpdateMode.INSTALL) {
			playFrame.updatetext.setText("Downloading mods...");
		}
		else playFrame.updatetext.setText("Checking for updates...");

		if (mode == UpdateMode.PLAY) {
			Play.progressBar1.setValue(0);
			Play.progressBar1.setMaximum(6);
			Play.progressBar1.setValue(1);
		}


		FTP.connect(playFrame, 4);

		try {

			FTPFile[] away = FTP.client.listFiles();


			File home_home = new File(DMSLauncher.gameDir + File.separator + "mods");
			home_home.mkdir();

			File[] home = home_home.listFiles();

			List<String> client = new ArrayList<String>();
			List<String> server = new ArrayList<String>();

			if (mode == UpdateMode.INSTALL) playFrame.addToConsole("Downloading mods...\n");
			else playFrame.addToConsole("Checking for updates...");

			Play.progressBar2.setValue(0);

			for (File f : home) {
				if (!f.getName().contains("DS_Store") && f.getName().endsWith(".jar")) {
					client.add(f.getName());
				}
			}

			for (FTPFile f : away) {
				if (!f.getName().contains("DS_Store") && f.getName().endsWith(".jar")) {
					server.add(f.getName());
				}
			}

			playFrame.updatetext.setText("");

			if (mode == UpdateMode.INSTALL) {
				updateMenu(client, server, UpdateMode.INSTALL);
				return;
			}

			boolean updateRequired = false;
			for (String fn : client)
				if (!server.contains(fn)) updateRequired = true;
			for (String fn : server)
				if (!client.contains(fn)) updateRequired = true;

			if (updateRequired) {
				playFrame.addToConsole("An update is available.");
				Play.progressBar1.setValue(2);
				updateMenu(client, server, mode);
			}
			else {
				playFrame.addToConsole("No update is available.");
				Play.progressBar1.setValue(2);
				new SW() {
					public void method() {
						Play.progressBar1.setValue(5);
						play(mode);
					}
				};

			}



		} catch (IOException ex) {
			CrashReport.error(ex, ErrorCode.UPDATE_CHECK, true);
		}


	}


	private void updateMenu(List<String> client, List<String> server, UpdateMode mode) {
		if (mode == UpdateMode.INSTALL) {
			List<List<String>> mods = updateNotes(client, server);
			update(mods.get(0), mods.get(1), UpdateMode.INSTALL);
			return;
		}

		JFrame framex = new JFrame("DMSLauncher Installation Summary");
		framex.setMinimumSize(new Dimension(600, 600));
		framex.setPreferredSize(new Dimension(600, 600));
		framex.setMaximumSize(new Dimension(600, 600));
		framex.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		framex.setLocationRelativeTo(DMSLauncher.getWindow().frame);
		framex.setResizable(false);
		Container panex = new Container();
		framex.setLayout(null);
		panex.setLayout(null);

		JLabel labelx = U.cL("An update is available!", Fonts.bold16(), 0, 0, 600, 40);
		panex.add(labelx);

		JTextArea area = new JTextArea();

		String sm = "";

		sm += "The following mods will be installed or deleted to match\n";
		sm += "your mods folder with the server\'s client selection...\n\n";

		int index = 1;
		List<List<String>> mods = updateNotes(client, server);

		sm += "Files to be deleted: " + mods.get(0).size() + "\n";

		for (String s : mods.get(0)){
			sm += index + ". " + s + "\n";
			index++;
		}


		sm += "\nFiles to be downloaded: " + mods.get(1).size() + "\n";

		index = 1;

		for (String s : mods.get(1)){
			sm += index + ". " + s + "\n";
			index++;
		}

		area.setFont(Fonts.plain14());
		area.setEditable(false);
		area.setText(sm);
		JScrollPane scroll = new JScrollPane(area);
		scroll.setBounds(new Rectangle(50, 30, 500, 500));
		panex.add(scroll);

		JButton cancel = new JButton("Cancel");
		cancel.setFont(Fonts.plain14a());
		cancel.setBounds(new Rectangle(20, 530, 180, 30));
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				framex.setVisible(false);
				framex.dispose();

				Play.progressBar1.setValue(0);
				Play.progressBar2.setValue(0);

				playFrame.addToConsole("\nCanceled.");

				DMSLauncher.getWindow().enableTabs();
				playFrame.enableButtons();

			}
		});
		panex.add(cancel);

		JButton updateButton = new JButton("Update and Play");
		updateButton.setFont(Fonts.plain14a());
		updateButton.setBounds(new Rectangle(210, 530, 180, 30));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				framex.setVisible(false);
				framex.dispose();

				new SW() {
					public void method() {
						List<List<String>> mods = updateNotes(client, server);
						List<String> delete = mods.get(0);
						List<String> install = mods.get(1);

						update(delete, install, mode);
						return;
					}
				};

			}
		});
		panex.add(updateButton);

		JButton skipButton = new JButton("Skip Update and Play");
		skipButton.setFont(Fonts.plain14a());
		skipButton.setBounds(new Rectangle(400, 530, 180, 30));
		skipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				framex.setVisible(false);
				framex.dispose();


				new SW() {
					public void method() {
						play(UpdateMode.PLAY);
						return;
					}
				};

			}
		});
		panex.add(skipButton);

		framex.setContentPane(panex);
		framex.setVisible(true);
	}

	private List<List<String>> updateNotes(List<String> client, List<String> server){
		List<String> deleteMods = new ArrayList<String>();

		for (String s : client) {
			if (!server.contains(s)) {
				deleteMods.add(s);
			}
		}

		List<String> installMods = new ArrayList<String>();

		for (String s : server) {
			if (!client.contains(s)) {
				installMods.add(s);
			}
		}

		List<List<String>> list = new ArrayList<List<String>>();

		list.add(deleteMods);
		list.add(installMods);

		return list;

	}

	public void play(UpdateMode mode) {

		if (mode == UpdateMode.INSTALL) {
			Play.RAMAllocateButton ram = playFrame.new RAMAllocateButton();
			ram.fromInstallation();
			return;
		}
		
		DMSLauncher.getWindow().disableTabs();
		playFrame.disableButtons();

		playFrame.updatetext.setText("Starting game...");

		Play.progressBar2.setValue(0);
		Play.progressBar2.setMaximum(1);


		playFrame.addToConsole("\n\nStarting game...");

		playFrame.addToConsole("Refreshing runMC" + (!U.isMacOS() ? ".bat" : ".command") + "...");

		if (new File(new File(FM.base, DMSLauncher.server + File.separator + "Game"), "runMC" + (U.isMacOS() ? ".bat" : ".command")).exists()) {
			new File(new File(FM.base, DMSLauncher.server + File.separator + "Game"), "runMC" + (U.isMacOS() ? ".bat" : ".command")).delete();
		}

		if (!U.isMacOS()) {
			List<String> runcmd = new ArrayList<String>();
			String path = new File(FM.base, DMSLauncher.server + File.separator + "Game").getPath();
			runcmd.add("cd \"" + path + "\"");
			runcmd.add("start \"\" Minecraft.exe --workDir \"" + DMSLauncher.gameDir + "\"");
			runcmd.add("exit");
			try {
				Files.write(new File(new File(FM.base, DMSLauncher.server + File.separator + "Game"), "runMC.bat").toPath(), runcmd, StandardOpenOption.CREATE);
			} catch (IOException e) {
				CrashReport.error(e, ErrorCode.FILE_WRITE, true);
			}
		} else {
			List<String> runcmd = new ArrayList<String>();
			String path = new File(FM.base, DMSLauncher.server + File.separator + "Game").getPath();
			runcmd.add("cd \"" + path + "\"");
			runcmd.add("chmod +x \"" + path + "/Minecraft.app/Contents/MacOS/launcher\"");
			runcmd.add("open Minecraft.app --args --workDir \"" + DMSLauncher.gameDir + "\"");
			runcmd.add("osascript -e \"do shell script \\\"osascript -e \\\\\\\"tell application \\\\\\\\\\\\\\\"Terminal\\\\\\\\\\\\\\\" to quit\\\\\\\" &> /dev/null &\\\"\"; exit");
			try {
				Files.write(new File(new File(FM.base, DMSLauncher.server + File.separator + "Game"), "runMC.command").toPath(), runcmd, StandardOpenOption.CREATE);
			} catch (IOException e) {
				CrashReport.error(e, ErrorCode.FILE_WRITE, true);
			}
		}

		U.pause(500L);

		Play.progressBar2.setValue(1);

		if (mode == UpdateMode.INSTALL) Play.progressBar1.setValue(6);
		if (mode == UpdateMode.PLAY) Play.progressBar1.setValue(7);

		playFrame.addToConsole("\nLaunching Minecraft!\n");

		if (U.isMacOS()) {
			try {
				//String[] args = new String[] {"/bin/bash", "-c", "chmod", "a+x", "\"" + U.base + File.separator + DMSLauncher.server + "/Game/runMC.command\""};
				//new ProcessBuilder(args).start();

				String path = FM.base.getPath() + File.separator + DMSLauncher.server + "/Game/runMC.command";
				Runtime.getRuntime().exec("chmod a+x " + new File(path).getPath());				

				U.pause(500L);
				Desktop.getDesktop().open(new File(FM.base, DMSLauncher.server + File.separator + "Game/runMC.command"));
			} catch (IOException e) {
				CrashReport.error(e, ErrorCode.OPEN_FILE, true);
			}
		}else {
			try{
				Desktop.getDesktop().open(new File(FM.base, DMSLauncher.server + File.separator + "Game/runMC.bat"));
			}catch(IOException e){
				CrashReport.error(e, ErrorCode.OPEN_FILE, true);
			}
		}
		
		U.pause(1000L);

		DMSLauncher.getWindow().saveAndExit(true);
	}

	private void update(List<String> delete, List<String> install, UpdateMode mode) {

		if (mode != UpdateMode.INSTALL) {
			playFrame.updatetext.setText("Updating modded client...");
			playFrame.addToConsole("\nUpdating client mods to match\nwith server's client selection...\n\n");

		}
			

			if (mode == UpdateMode.PLAY) {
				Play.progressBar1.setValue(3);
			}

			int pb2sizeExtra = 0;
			if (delete.size() == 0) pb2sizeExtra++;
			Play.progressBar2.setValue(delete.size() + pb2sizeExtra);
			Play.progressBar2.setValue(0);

			int num = 0;
			for (String s : delete){
				File file = new File(new File(DMSLauncher.gameDir + File.separator + "mods"), s);
				if (file.exists()){
					FM.delete(file);
					num++;
					playFrame.updatetext.setText("Deleted mod " + num + "/" + delete.size());

					Play.progressBar2.setValue(num);

					playFrame.addToConsole("Deleted file:\n" + s + "\n");

				}
			}

			if (mode == UpdateMode.PLAY) {
				Play.progressBar1.setValue(4);
			}

			Play.progressBar2.setValue(0);

			pb2sizeExtra = 0;
			if (install.size() == 0) pb2sizeExtra++; 
			Play.progressBar2.setMaximum(install.size() + pb2sizeExtra);

			num = 0;
			for (String s : install){

				playFrame.addToConsole("Downloading file:\n" + s + "\n");
				File file = new File(new File(DMSLauncher.gameDir + File.separator + "mods"), s);

				FTP.download(s, file, 999, playFrame);

				num++;
				playFrame.updatetext.setText("Downloaded mod " + num + "/" + install.size());
				Play.progressBar2.setValue(num);

			}

			if (mode == UpdateMode.PLAY) {
				Play.progressBar1.setValue(5);
			}

			Play.progressBar2.setValue(0);

			Play.progressBar2.setIndeterminate(true);

			playFrame.updatetext.setText("Validating files...");
			playFrame.addToConsole("\n\nValidating files...\n");

			boolean success = true;
			String failMessage = "";

			try {

				FTPFile[] remotes = FTP.client.listFiles();
				File[] locals = new File(DMSLauncher.gameDir, "mods").listFiles();

				int remoteSize = 0;
				int localSize = 0;

				for (FTPFile remote : remotes) {

					if (remote.getName().endsWith(".jar")) remoteSize++;

				}

				for (File local : locals) {

					if (local.getName().endsWith(".jar")) localSize++;

				}

				if (remoteSize != localSize) {

					success = false;
					failMessage += "\nFile count mismatch:\nremote: " + remoteSize + "\nlocal: " + localSize + "\n";

				}

				for (FTPFile remote : FTP.client.listFiles()) {

					if (!remote.getName().endsWith(".jar")) continue;

					File local = new File(DMSLauncher.gameDir + File.separator + "mods", remote.getName());

					if (!local.exists()) {

						success = false;
						failMessage += "\nMissing file: \n" + local.getName() + "\n";
						continue;

					}

					if (local.length() != remote.getSize()) {

						success = false;
						failMessage += "\nSize mismatch:\nfile: " + local.getName() + "\nlocal size: " + local.length() + "\nremote size: " + remote.getSize() + "\n";

					}

				}

			} catch (IOException e) {
				CrashReport.error(e, ErrorCode.UPDATE_CHECK, true);
			}
			
			FTP.disconnect();

			if (success) {

				playFrame.addToConsole("\nValidation successful!\n");

			} else {

				playFrame.addToConsole("\nVALIDATION FAILED OH NO!\n");
				playFrame.addToConsole(failMessage);
				playFrame.addToConsole("\nVALIDATION FAILED OH NO!\n");
				
				CrashReport.validationFail(failMessage);
				
				int i = U.eD("Validation Failure", "Okay so apparently your mod folder doesn't match the server's.\n"
						+ "This means some of your mods may not have downloaded all the way or download glitches may have occurred.\n\n"
						+ "This is a big deal because some of your mods may be corrupted and playing with them may cause problems.\n\nI recommend you DO NOT start up your game. Instead, click 'More help' to learn how to fix this issue.", "More help", "More help", "Exit DMSLauncher");
				
				if (i == 0) {
					
					try {
						Desktop.getDesktop().browse(new URI("https://blockhead7360.com/dmslauncher/validation-failure"));
					} catch (IOException | URISyntaxException e1) {
						CrashReport.error(e1, ErrorCode.OPEN_LINK, false);
					}
					
				}
				
				System.exit(0);
				return;

			}


		Play.progressBar2.setIndeterminate(false);

		playFrame.enableButtons();
		DMSLauncher.getWindow().enableTabs();
		playFrame.updatetext.setText(" ");
		if (mode == UpdateMode.INSTALL) {
			Play.progressBar1.setValue(5);
			playFrame.addToConsole("\n\nInstallation complete!\n");
		}
		else playFrame.addToConsole("\n\nUpdate is complete!\n");

		if (mode == UpdateMode.PLAY) {
			Play.progressBar1.setValue(6);
		}

		play(mode);

	}

	public enum UpdateMode {
		PLAY,
		INSTALL,
	}

}
