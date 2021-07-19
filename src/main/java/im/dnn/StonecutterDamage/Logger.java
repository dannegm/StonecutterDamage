package im.dnn.StonecutterDamage;

import org.bukkit.plugin.java.JavaPlugin;

public class Logger {
    static Logger singleton = null;
    static JavaPlugin plugin;

    public static void setPlugin (JavaPlugin plugin) {
        Logger.plugin = plugin;
    }

    private static void createInstance() {
        if (singleton == null) {
            singleton = new Logger();
        }
    }

    public static void info (String msg) {
        Logger.createInstance();
        if (Settings.isDebug()) {
            plugin.getLogger().info(msg);
        }
    }

    public static void importantInfo (String msg) {
        Logger.createInstance();
        plugin.getLogger().info(msg);
    }
}
