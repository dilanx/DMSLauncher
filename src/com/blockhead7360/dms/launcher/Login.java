package com.blockhead7360.dms.launcher;

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.blockhead7360.dms.launcher.files.FM;
import com.blockhead7360.dms.launcher.internet.DMSAccount;
import com.blockhead7360.dms.launcher.internet.ScriptReader;
import com.blockhead7360.dms.launcher.utilities.Fonts;
import com.blockhead7360.dms.launcher.utilities.U;
import com.blockhead7360.dms.launcher.view.MainWindow;
import com.blockhead7360.dms.launcher.view.Window;
import com.blockhead7360.dms.launcher.views.Play;
import com.blockhead7360.dms.launcher.views.Server;
import com.blockhead7360.dms.launcher.views.Settings;
import com.blockhead7360.dms.program.manageserver.passwordencoder.DMSLauncherPasswordEncoder;

public class Login extends Container {

	private static final long serialVersionUID = 1L;

	private DMSLauncher main;
	
	private Map<String, String> servers;

	private JLabel error;
	private JButton go;

	public boolean connectToServer(boolean fromView, String server, String pass, String id) {

		if (server == null) {
			return false;
		}

		if (!servers.containsKey(server)) {
			return false;
		}

		if (pass == null) {
			return false;
		}

		if (id == null) {
			return false;
		}

		String realPass = DMSLauncherPasswordEncoder.decode(servers.get(server));
		if (!realPass.equals(pass)) {

			if (fromView) {
				error.setText("Nope. That's not the right password.");
			}

			return false;
		}
		
		DMSLauncher.server = server;

		ScriptReader.read();


		String account = null;

		for (DMSAccount dmsa : ScriptReader.accounts) {
			String dmsaId = dmsa.getId();
			if (dmsaId.equalsIgnoreCase(id)) {
				account = dmsaId;
			}
		}
		
		if (account == null) {
			
			if (fromView) {
				error.setText("That isn't a valid DMSLauncher ID.");
			}
			
			return false;
		}
		
		DMSLauncher.account = account;

		FM.config.setProperty("server_name", server);
		FM.config.setProperty("server_pass", realPass);
		FM.config.setProperty("server_account", account);
		
		FM.loadServerFiles();
		Window.tabs.clear();
		
		new Play(main, ScriptReader.changelog, "");
		Server.initRules();
		new Server(main);
		new Settings(main);
		
		DMSLauncher.getWindow().load();

		return true;

	}

	public Login(DMSLauncher main, Map<String, String> servers) {
		this.main = main;
		this.servers = servers;

		setLayout(null);

		JLabel title = U.cL("DMSLauncher " + DMSLauncher.version, Fonts.bold72(), 0, 20, MainWindow.WIDTH, 100);
		add(title);

		JLabel instruction = U.cL("Select a server and log in to continue.", Fonts.plain24a(), 0, 200, MainWindow.WIDTH, 30);
		add(instruction);

		// 150 + 30 + 300 = 480

		JLabel serverTitle = U.rL("Server Name", Fonts.bold16a(), MainWindow.WIDTH / 2 - 240, 300, 150, 30);
		add(serverTitle);

		JComboBox<String> server = new JComboBox<String>();
		server.addItem("- Select a server...");
		for (String s : servers.keySet()) server.addItem(s);
		server.setBounds(new Rectangle(MainWindow.WIDTH/2 - 60, 300, 300, 30));
		add(server);

		JLabel passTitle = U.rL("Server Password", Fonts.bold16a(), MainWindow.WIDTH / 2 - 240, 350, 150, 30);
		add(passTitle);

		JTextField pass = new JTextField();
		pass.setBounds(new Rectangle(MainWindow.WIDTH/2 - 60, 350, 300, 30));
		add(pass);

		JLabel idTitle = U.rL("DMSLauncher ID", Fonts.bold16a(), MainWindow.WIDTH / 2 - 240, 400, 150, 30);
		add(idTitle);

		JTextField id = new JTextField();
		id.setBounds(new Rectangle(MainWindow.WIDTH/2 - 60, 400, 300, 30));
		add(id);

		error = U.cL("", Fonts.plain14a(), 0, 580, MainWindow.WIDTH, 20);
		error.setForeground(Color.red);
		add(error);

		go = new JButton("LOG IN");
		go.setFont(Fonts.bold16a());
		go.setBounds(new Rectangle(MainWindow.WIDTH/2 - 240, 500, 480, 50));
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (server.getSelectedIndex() == 0) {
					error.setText("You didn't select a server.");
					return;
				}

				if (pass.getText().isEmpty()) {
					error.setText("You didn't enter the password.");
					return;
				}

				if (id.getText().isEmpty()) {
					error.setText("You didn't even enter your DMSLauncher ID.");
					return;
				}

				connectToServer(true, (String) server.getSelectedItem(), pass.getText(), id.getText());

				return;

			}

		});
		add(go);

	}

}
