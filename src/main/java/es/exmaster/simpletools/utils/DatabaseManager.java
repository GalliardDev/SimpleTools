package es.exmaster.simpletools.utils;

import java.io.File;
import java.io.IOException;

import es.exmaster.simpletools.Main;

public class DatabaseManager {
	public static void crearBDD() {
		String DBFolderPath = Main.plugin.getDataFolder().getPath();
		
		File db = new File(DBFolderPath, "database.db");
		
    	if(!(db.exists())) {
    		try {
    	        db.createNewFile();
    	        Main.plugin.getLogger().info("DB has been created");
    	    } catch (IOException e) {
    	    	Main.plugin.getLogger().severe("Error creating DB.");
    	    }
    	} else {
    		Main.plugin.getLogger().info("DB has been found");
    	}
	}
}
