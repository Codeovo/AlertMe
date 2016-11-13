package io.codeovo.alertme.config;

import io.codeovo.alertme.AlertMe;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private AlertMe alertMe;
    private FileConfiguration config;

    private boolean isDebug;

    private String accountSID;
    private String accountAuth;
    private String twilioNumber;

    private boolean isOPEnabled;
    private boolean isOPIgnoreCancelled;

    public Config(AlertMe alertMe) throws Error {
        this.alertMe = alertMe;
        this.config = alertMe.getConfig();

        this.alertMe.saveDefaultConfig();

        loadValues();
    }

    private void loadValues() throws Error {
        isDebug = config.getBoolean("plugin.debug");

        accountSID = config.getString("twilio.account-sid");
        accountAuth = config.getString("twilio.account-auth");
        twilioNumber = config.getString("twilio.twilio-number");

        isOPEnabled = config.getBoolean("op-event.enabled");
        isOPIgnoreCancelled = config.getBoolean("op-event.ignore-cancelled");
    }

    public String getAccountSID() {
        return accountSID;
    }

    public String getAccountAuth() {
        return accountAuth;
    }

    public String getTwilioNumber() {
        return twilioNumber;
    }

    public boolean isOPEnabled() {
        return isOPEnabled;
    }

    public boolean isOPIgnoreCancelled() {
        return isOPIgnoreCancelled;
    }
}
