package im.dnn.StonecutterDamage;

import org.bukkit.plugin.java.JavaPlugin;

public class StonecutterDamage extends JavaPlugin {
    private final int RESOURCE_ID = 94430;

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

    public void checkForUpdates () {
        new UpdateChecker(this, RESOURCE_ID).getVersion(version -> {
            if (!this.getDescription().getVersion().equalsIgnoreCase(version)) {
                Logger.importantInfo("There is a new update available.");
            }
        });
    }
}
