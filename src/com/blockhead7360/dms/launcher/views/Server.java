package com.blockhead7360.dms.launcher.views;

import java.awt.Color;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.blockhead7360.dms.launcher.DMSLauncher;
import com.blockhead7360.dms.launcher.internet.ScriptReader;
import com.blockhead7360.dms.launcher.utilities.Fonts;
import com.blockhead7360.dms.launcher.utilities.SW;
import com.blockhead7360.dms.launcher.utilities.U;
import com.blockhead7360.dms.launcher.view.Window;

public class Server extends Window{
	
	public static JTextArea ruleList;
	public static JTextArea ruleAbout;
	
	public static String ruleListText = "";
	
	public static JComboBox<String> ruleSelect;
	
	public static DMSLauncher dmsmain;
	
	public Server(DMSLauncher main) {
		super(main, "Server", "View server rules");
		
		dmsmain = main;
		
		JLabel serverrules = U.cL("Server Rules", Fonts.bold32a(), 0, 5, 1000, 50);
		pane.add(serverrules);
		
		JLabel updatedTime = U.cL(ScriptReader.rulesSub, Fonts.plain14a(), 0, 55, 1000, 30);
		pane.add(updatedTime);
		
		JLabel belowText = U.cL(ScriptReader.rulesWarn, Fonts.bold14a(), 0, 475, 1000, 30);
		belowText.setForeground(new Color(176, 0, 0));
		pane.add(belowText);

		ruleList = new JTextArea();
		ruleList.setMargin(new Insets(20, 20, 20, 20));
		ruleList.setBounds(new Rectangle(25, 105, 600, 350));
		ruleList.setFont(Fonts.bold18());
		ruleList.setEditable(false);
		ruleList.setText(ruleListText);
		ruleList.setVisible(true);
		JScrollPane ruleList_pane = new JScrollPane(ruleList);
		ruleList_pane.setBounds(new Rectangle(25, 105, 600, 350));
		pane.add(ruleList_pane);
		
		
		ruleAbout = new JTextArea();
		ruleAbout.setMargin(new Insets(8, 8, 8, 8));
		ruleAbout.setBounds(new Rectangle(650, 205, 325, 250));
		ruleAbout.setFont(Fonts.plain14());
		ruleAbout.setEditable(false);
		ruleAbout.setText(ruleAbout_placeholder);
		ruleAbout.setForeground(Color.gray);
		ruleAbout.setVisible(true);
		JScrollPane ruleAbout_pane = new JScrollPane(ruleAbout);
		ruleAbout_pane.setBounds(new Rectangle(650, 205, 325, 250));
		pane.add(ruleAbout_pane);
		
		
		
		JLabel viewmore = U.cL("Select a rule to view more information", Fonts.italic12a(), 650, 120, 325, 30);
		pane.add(viewmore);
		
		ruleSelect = new JComboBox<String>();
		ruleSelect.addItem(" ");
		for (String s : ScriptReader.rulesKeys) {
			ruleSelect.addItem(s);
		}
		ruleSelect.addActionListener(new ViewMore());
		ruleSelect.setBounds(new Rectangle(650, 150, 325, 30));
		pane.add(ruleSelect);
		
		register(this);
		
	}
	
	
	public class ViewMore implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			new SW() {
				public void method() {
					
					if (ruleSelect.getSelectedIndex() <= 0) {
						ruleAbout.setText(ruleAbout_placeholder);
						ruleAbout.setForeground(Color.gray);
						return;
					}
					String text = ScriptReader.rules.get(ruleSelect.getSelectedItem());
					text = text.replaceAll("/n", "\n");
					ruleAbout.setText(text);
					if (DMSLauncher.darkMode) ruleAbout.setForeground(Color.white);
					else ruleAbout.setForeground(Color.black);
					
				}
			};
		}
	}
	
	
	public static void initRules() {
		
		ruleListText = "";
		
		for (int i = 0; i < ScriptReader.rules.size(); i++) {
			
			ruleListText += (i + 1) + ". " + ScriptReader.rulesKeys.get(i).toString() + "\n\n";
		}
		
	}

	public static String ruleAbout_placeholder = "Select a rule from the box above to\nview more information.";
	
	
}
