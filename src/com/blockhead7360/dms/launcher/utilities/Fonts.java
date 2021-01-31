package com.blockhead7360.dms.launcher.utilities;

import java.awt.Font;

public class Fonts {

	private static String type;
	
	public static void init() {
		if (U.isMacOS())
			type = "monaco";
		else
			type = "consolas";
	}
	
	public static Font plain12() {
		return new Font(type, Font.PLAIN, 12);
	}
	
	public static Font plain12a() {
		return new Font("arial", Font.PLAIN, 12);
	}
	
	public static Font italic10a() {
		return new Font("arial", Font.ITALIC, 10);
	}
	
	public static Font plain14() {
		return new Font(type, 0, 14);
	}
	
	public static Font plain14a() {
		return new Font("arial", 0, 14);
	}
	
	public static Font plain16() {
		return new Font(type, 0, 16);
	}
	
	public static Font plain16a() {
		return new Font("arial", 0, 16);
	}
	
	public static Font plain20a() {
		return new Font("arial", 0, 20);
	}
	
	public static Font plain24a() {
		return new Font("arial", 0, 24);
	}
	
	public static Font plain32a() {
		return new Font("arial", 0, 32);
	}
	
	public static Font bold14a() {
		return new Font("arial", 1, 14);
	}
	
	public static Font bold14() {
		return new Font(type, 1, 14);
	}
	
	public static Font bold16() {
		return new Font(type, 1, 16);
	}
	
	public static Font bold16a() {
		return new Font("arial", 1, 16);
	}
		
	public static Font bold18() {
		return new Font(type, 1, 18);
	}
	
	public static Font italic16() {
		return new Font(type, 2, 16);
	}
	
	public static Font bold12() {
		return new Font(type, 1, 12);
	}
	
	public static Font italic12a() {
		return new Font("arial", 2, 12);
	}
	
	public static Font bold12a() {
		return new Font("arial", Font.BOLD, 12);
	}
	
	public static Font bold18a() {
		return new Font("arial", 1, 18);
	}
	
	public static Font bold22() {
		return new Font(type, 1, 22);
	}
	
	public static Font bold22a() {
		return new Font("arial", 1, 22);
	}
	
	public static Font bold32() {
		return new Font(type, 1, 32);
	}
	
	public static Font bold32a() {
		return new Font("arial", 1, 32);
	}
	
	public static Font bold40() {
		return new Font(type, 1, 40);
	}
	
	public static Font bold48() {
		return new Font(type, 1, 48);
	}
	
	public static Font bold48a() {
		return new Font("arial", 1, 48);
	}
	
	public static Font bold72() {
		return new Font(type, 1, 72);
	}
	
}
