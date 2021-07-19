package im.dnn.StonecutterDamage;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class Settings {
    static Settings singleton = null;
    private static FileConfiguration config;

    public static void setupConfig (JavaPlugin plugin) {
        singleton = new Settings();

        Settings.config = plugin.getConfig();

        Settings.config.addDefault("debug", true);
        Settings.config.addDefault("damageAmount", 2.0);
        Settings.config.addDefault("deathMessage", "<player> died stepping on stonecutter");
        plugin.saveConfig();
    }

    public static boolean isDebug () {
        return Settings.config.getBoolean("debug");
    }

    public static double getDouble (String path) {
        return Settings.config.getDouble(path);
    }

    public static String getString (String path) {
        return Settings.config.getString(path);
    }
}
