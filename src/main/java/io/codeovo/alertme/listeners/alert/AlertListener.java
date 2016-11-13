package io.codeovo.alertme.listeners.alert;

import io.codeovo.alertme.AlertMe;
import io.codeovo.alertme.events.TwilioAlertEvent;
import io.codeovo.alertme.exception.AlertMeException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AlertListener implements Listener {
    private AlertMe alertMe;

    public AlertListener(AlertMe alertMe) { this.alertMe = alertMe; }

    @EventHandler(ignoreCancelled = true)
    public void onAlert(TwilioAlertEvent e) {
        String broadcastMessage = alertMe.getPluginConfig().getPrefix() + e.getMessage();

        switch (e.getAlertType()) {
            case SMS:
                e.getAlertType();
                break;
            case VOICE:
                break;
            default:
                try {
                    throw new AlertMeException("Unable to handle alert event, something went wrong.");
                } catch (AlertMeException e1) {
                    alertMe.getLogger().info("AlertMe - Something went wrong: " + e1.getMessage());

                    if (alertMe.getPluginConfig().isDebug()) {
                        e1.printStackTrace();
                    }
                }
                break;
        }
    }
}
