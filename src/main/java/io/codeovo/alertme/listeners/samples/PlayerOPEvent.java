package io.codeovo.alertme.listeners.samples;

import io.codeovo.alertme.AlertMe;

import io.codeovo.alertme.events.AlertType;
import io.codeovo.alertme.events.TwilioAlertEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerOPEvent implements Listener {
    private AlertMe alertMe;

    public PlayerOPEvent(AlertMe alertMe) {
        this.alertMe = alertMe;
    }

    @EventHandler
    public void onOP(PlayerCommandPreprocessEvent e) {
        if (e.getMessage().startsWith("/op")) {
            if (e.isCancelled() && alertMe.getPluginConfig().isOPIgnoreCancelled()) {
                return;
            }

            Bukkit.getPluginManager().callEvent(
                    new TwilioAlertEvent(e.getPlayer().getDisplayName() + " attempted to use the OP command.",
                            AlertType.SMS));
        }
    }
}
