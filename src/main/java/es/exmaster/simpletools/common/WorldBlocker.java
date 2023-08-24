package es.exmaster.simpletools.common;

import java.io.File;
import java.io.IOException;

import es.exmaster.simpletools.Main;

public class WorldBlocker {
	public static void loadFile() {
        File blockedWorlds = new File(Main.plugin.getDataFolder(), "blockedWorlds.yml");
        try {
			blockedWorlds.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
