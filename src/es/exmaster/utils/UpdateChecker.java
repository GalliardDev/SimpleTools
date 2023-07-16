package es.exmaster.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import es.exmaster.simpletools.Main;

public class UpdateChecker {
    private Main plugin;
    private int resourceId;

    public UpdateChecker(Main plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLatestVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
                 Scanner scanner = new Scanner(inputStream);){
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            }
            catch (IOException exception) {
                this.plugin.getLogger().info("There was an error finding an update!" + exception.getMessage());
            }
        });
    }
}
