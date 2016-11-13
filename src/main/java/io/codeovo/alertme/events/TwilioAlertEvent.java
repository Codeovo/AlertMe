package io.codeovo.alertme.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TwilioAlertEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();

    private String message;
    private AlertType alertType;
    private boolean cancelled;

    public TwilioAlertEvent(String message, AlertType alertType) {
        this.message = message;
        this.alertType = alertType;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public String getMessage() {
        return message;
    }

    public AlertType getAlertType() {
        return alertType;
    }
}
