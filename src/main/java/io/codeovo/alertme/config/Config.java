package io.codeovo.alertme.config;

import io.codeovo.alertme.AlertMe;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {
    private FileConfiguration config;

    private boolean isDebug;

    private String accountSID;
    private String accountAuth;
    private String twilioNumber;

    private String prefix;
    private List<String> toAlertNumbers;

    private boolean isOPEnabled;
    private boolean isOPIgnoreCancelled;
    private int opAlertingType;

    public Config(AlertMe alertMe) throws Error {
        this.config = alertMe.getConfig();

        alertMe.saveDefaultConfig();

        loadValues();
    }

    private void loadValues() throws Error {
        isDebug = config.getBoolean("plugin.debug");

        accountSID = config.getString("twilio.account-sid");
        accountAuth = config.getString("twilio.account-auth");
        twilioNumber = config.getString("twilio.twilio-number");

        prefix = config.getString("alerts.prefix");
        toAlertNumbers = config.getStringList("alerts.alert-numbers");

        isOPEnabled = config.getBoolean("op-event.enabled");
        isOPIgnoreCancelled = config.getBoolean("op-event.ignore-cancelled");
        opAlertingType = config.getInt("op-event.alerting-type");
    }

    public boolean isDebug() {
        return isDebug;
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

    public String getPrefix() { return prefix; }

    public List<String> getToAlertNumbers() { return toAlertNumbers; }

    public boolean isOPEnabled() {
        return isOPEnabled;
    }

    public boolean isOPIgnoreCancelled() {
        return isOPIgnoreCancelled;
    }

    public int getOpAlertingType() { return opAlertingType; }
}
