package io.codeovo.alertme.listeners.samples;

import io.codeovo.alertme.AlertMe;
import io.codeovo.alertme.events.AlertType;
import io.codeovo.alertme.events.TwilioAlertEvent;
import io.codeovo.alertme.exception.AlertMeException;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerOPEvent implements Listener {
    private AlertMe alertMe;

    public PlayerOPEvent(AlertMe alertMe) { this.alertMe = alertMe; }

    @EventHandler
    public void onOP(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().startsWith("/op")) {
            if (e.isCancelled() && alertMe.getPluginConfig().isOPIgnoreCancelled()) {
                return;
            }

            switch (alertMe.getPluginConfig().getOpAlertingType()) {
                case 0:
                    Bukkit.getPluginManager().callEvent(
                            new TwilioAlertEvent(e.getPlayer().getDisplayName() + " attempted to use the OP command.",
                                    AlertType.SMS));
                    break;
                case 1:
                    Bukkit.getPluginManager().callEvent(
                            new TwilioAlertEvent(e.getPlayer().getDisplayName() + " attempted to use the OP command.",
                                    AlertType.VOICE));
                    break;
                case 2:
                    Bukkit.getPluginManager().callEvent(
                            new TwilioAlertEvent(e.getPlayer().getDisplayName() + " attempted to use the OP command.",
                                    AlertType.BOTH));
                    break;
                default:
                    try {
                        throw new AlertMeException("Unable to throw OP Event, likely an invalid alerting type.");
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
}
