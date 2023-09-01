package es.exmaster.simpletools.common;

import es.exmaster.simpletools.Main;

public class SimpleLogger {
	private Main main;
	
	public SimpleLogger() {
		this.main = Main.plugin;
	}
	
	public void error(String msg) {
		main.getLogger().severe(msg);
	}
	
	public void info(String msg) {
		main.getLogger().info(msg);
	}
	
	public void warning(String msg) {
		main.getLogger().warning(msg);
	}
}
