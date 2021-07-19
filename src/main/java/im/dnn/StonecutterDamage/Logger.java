package im.dnn.StonecutterDamage;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class Logger {
    static Logger singleton = null;

    static JavaPlugin plugin;

    Location storedBlock;

    public static void setPlugin (JavaPlugin plugin) {
        Logger.plugin = plugin;
    }

    public static void info (String msg) {
        if (singleton == null) {
            singleton = new Logger();
        }

        if (Settings.isDebug()) {
            plugin.getLogger().info(msg);
        }
    }
}
