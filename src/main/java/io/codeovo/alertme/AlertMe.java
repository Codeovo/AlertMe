package io.codeovo.alertme;

import com.twilio.Twilio;

import io.codeovo.alertme.config.Config;

import org.bukkit.plugin.java.JavaPlugin;

public class AlertMe extends JavaPlugin {
    private static AlertMe pluginInstance;

    private Config pluginConfig;

    @Override
    public void onEnable() {
        getLogger().info("AlertMe - Enabling...");
        pluginInstance = this;
        getLogger().info("AlertMe - Loading configuration...");
        loadConfiguration();
        getLogger().info("AlertMe - Loading commands...");
        registerCommands();
        getLogger().info("AlertMe - Loading listeners...");
        registerListeners();
        getLogger().info("AlertMe - Initializing Twilio...");
        Twilio.init(pluginConfig.getAccountSID(), pluginConfig.getAccountAuth());
        getLogger().info("AlertMe - Enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("AlertMe - Disabling...");
        pluginInstance = null;
        getLogger().info("AlertMe - Disabled.");
    }

    private void registerCommands() {

    }

    private void registerListeners() {

    }

    private void loadConfiguration() {
        pluginConfig = new Config(this);
    }

    public static AlertMe getInstance() { return pluginInstance; }
}
