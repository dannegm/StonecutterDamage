package im.dnn.StonecutterDamage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class StonecutterDamage extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        Settings.setupConfig(this);
        Logger.setPlugin(this);

        Logger.info("Enabled plugin");
        getServer().getPluginManager().registerEvents(new StonecutterListener(), this);
    }

    @Override
    public void onDisable() {
        Logger.info("Disabled plugin");
    }
}
