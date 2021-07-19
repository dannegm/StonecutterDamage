package im.dnn.StonecutterDamage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Settings {
    static Settings singleton = null;
    private static FileConfiguration config;

    public static void setupConfig (JavaPlugin plugin) {
        singleton = new Settings();

        Settings.config = plugin.getConfig();

        Settings.config.addDefault("debug", false);
        Settings.config.addDefault("damage.amount", 4.0);
        Settings.config.addDefault("damage.rate", 2.0);
        Settings.config.addDefault("knockback.enabled", true);
        Settings.config.addDefault("knockback.rate", 0.1);

        List<String> deathMessages = new ArrayList<>();
        deathMessages.add("<player> died stepping on stonecutter");
        deathMessages.add("<player> chop his leg");
        Settings.config.addDefault("deathMessages", deathMessages);

        plugin.saveConfig();
    }

    public static boolean isDebug () {
        return Settings.config.getBoolean("debug");
    }

    public static double getDouble (String path) {
        return Settings.config.getDouble(path);
    }

    public static List<String> getStringList (String path) {
        return Settings.config.getStringList(path);
    }

    public static boolean getBoolean (String path) {
        return Settings.config.getBoolean(path);
    }
}
