package io.codeovo.alertme.listeners.alert;

import io.codeovo.alertme.AlertMe;

import io.codeovo.alertme.events.TwilioAlertEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AlertListener implements Listener {
    private AlertMe alertMe;

    public AlertListener(AlertMe alertMe) { this.alertMe = alertMe; }

    @EventHandler(ignoreCancelled = true)
    public void onAlert(TwilioAlertEvent e) {

    }
}
