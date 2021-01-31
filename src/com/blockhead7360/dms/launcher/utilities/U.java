package com.blockhead7360.dms.launcher.utilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import com.blockhead7360.dms.launcher.DMSLauncher;

public class U {

	public static void pause(long milliseconds) {
		boolean pause = true;
		long old = System.currentTimeMillis();
		while (pause) {
			if (System.currentTimeMillis() - old > milliseconds) {
				pause = false;
			}
		}
	}

	public static String chatFromList(List<String> list) {
		String s = "";
		for (String x : list){
			s += x + "\n\n";
		}
		return s;
	}


	public static JLabel l(String text, Font font, int x, int y, int w, int h) {
		JLabel label = new JLabel(text);
		label.setFont(font);
		label.setBounds(new Rectangle(x, y, w, h));
		return label;
	}

	public static JLabel cL(String text, Font font, int x, int y, int w, int h) {
		JLabel label = new JLabel(text, SwingConstants.CENTER);
		label.setFont(font);
		label.setBounds(new Rectangle(x, y, w, h));
		return label;
	}
	
	public static JLabel rL(String text, Font font, int x, int y, int w, int h) {
		JLabel label = new JLabel(text, SwingConstants.RIGHT);
		label.setFont(font);
		label.setBounds(new Rectangle(x, y, w, h));
		return label;
	}

	public static int eD(String title, String message, String defaultOption, String... options) {
		return d(JOptionPane.ERROR_MESSAGE, title, message, defaultOption, options);
	}

	public static int wD(String title, String message, String defaultOption, String... options) {
		return d(JOptionPane.WARNING_MESSAGE, title, message, defaultOption, options);
	}

	public static int iD(String title, String message, String defaultOption, String... options) {
		return d(JOptionPane.INFORMATION_MESSAGE, title, message, defaultOption, options);
	}

	public static int qD(String title, String message, String defaultOption, String... options) {
		return d(JOptionPane.QUESTION_MESSAGE, title, message, defaultOption, options);
	}

	public static boolean isMacOS() {
		return !(System.getProperty("os.name").toLowerCase().contains("win"));
	}

	public static int d(int messageType, String title, String message, String defaultOption, String... options) {
		if (options.length <= 1) {
			JOptionPane.showMessageDialog(DMSLauncher.getWindow().frame, message, title, messageType);
			return 0;
		}
		if (options.length == 2)
			return JOptionPane.showOptionDialog(DMSLauncher.getWindow().frame, message, title, JOptionPane.YES_NO_OPTION, messageType, null, options, defaultOption);

		return JOptionPane.showOptionDialog(DMSLauncher.getWindow().frame, message, title, JOptionPane.YES_NO_CANCEL_OPTION, messageType, null, options, defaultOption);
	}
	
	public static Color colorTranslate(String colorString) {
		if (colorString.equalsIgnoreCase("black")) return Color.black;
		if (colorString.equalsIgnoreCase("blue")) return Color.blue;
		if (colorString.equalsIgnoreCase("cyan")) return Color.cyan;
		if (colorString.equalsIgnoreCase("darkGray")) return Color.darkGray;
		if (colorString.equalsIgnoreCase("gray")) return Color.gray;
		if (colorString.equalsIgnoreCase("green")) return Color.green;
		if (colorString.equalsIgnoreCase("lightGray")) return Color.lightGray;
		if (colorString.equalsIgnoreCase("magenta")) return Color.magenta;
		if (colorString.equalsIgnoreCase("orange")) return Color.orange;
		if (colorString.equalsIgnoreCase("pink")) return Color.pink;
		if (colorString.equalsIgnoreCase("red")) return Color.red;
		if (colorString.equalsIgnoreCase("white")) return Color.white;
		if (colorString.equalsIgnoreCase("yellow")) return Color.yellow;
		return null;

	}

}
