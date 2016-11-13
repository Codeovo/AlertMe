package io.codeovo.alertme.config;

import io.codeovo.alertme.AlertMe;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private AlertMe alertMe;
    private FileConfiguration config;

    private String accountSID;
    private String accountAuth;

    public Config(AlertMe alertMe) {
        this.alertMe = alertMe;
        this.config = alertMe.getConfig();

        this.alertMe.saveDefaultConfig();

        loadValues();
    }

    private void loadValues() {
        accountSID = config.getString("twilio.account-sid");
        accountAuth = config.getString("twilio.account-auth");
    }

    public String getAccountSID() {
        return accountSID;
    }

    public String getAccountAuth() {
        return accountAuth;
    }
}
