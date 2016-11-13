package io.codeovo.alertme;

import com.twilio.Twilio;

import io.codeovo.alertme.config.Config;
import io.codeovo.alertme.listeners.samples.PlayerOPEvent;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AlertMe extends JavaPlugin {
    private Config pluginConfig;

    @Override
    public void onEnable() {
        getLogger().info("AlertMe - Enabling...");
        getLogger().info("AlertMe - Loading configuration...");
        loadConfiguration();
        getLogger().info("AlertMe - Loading listeners...");
        registerListeners();
        getLogger().info("AlertMe - Initializing Twilio...");
        Twilio.init(pluginConfig.getAccountSID(), pluginConfig.getAccountAuth());
        getLogger().info("AlertMe - Enabled.");
    }

    private void registerListeners() {

        if (pluginConfig.isOPEnabled()) {
            getServer().getPluginManager().registerEvents(new PlayerOPEvent(this), this);
        }
    }

    private void loadConfiguration() {
        try {
            pluginConfig = new Config(this);
        } catch (Error e) {
            getLogger().info("AlertMe - An error was encountered: " + e.getMessage());

            if (pluginConfig.isDebug()) {
                e.printStackTrace();
            }

            getLogger().info("AlertMe - Plugin will disable...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    public Config getPluginConfig() { return pluginConfig; }
}
