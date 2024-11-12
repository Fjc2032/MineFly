package dev.Fjc.mineFly;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineFly extends JavaPlugin implements Listener {

    private final double mineFlySpeed = getConfig().getDouble("set-mining-speed-flying", 20);
    private final double notFlyingSpeed = getConfig().getDouble("set-mining-speed-not-flying", 4);

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        saveConfig();
        getLogger().info("Plugin disabled! All files saved!");
        // Plugin shutdown logic
    }

    public double getMineFlySpeed() {
        return mineFlySpeed;
    }
    public double getMineFlyNotFlyingSpeed() {
        return notFlyingSpeed;
    }

}
