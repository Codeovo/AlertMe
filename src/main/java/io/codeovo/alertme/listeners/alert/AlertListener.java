package io.codeovo.alertme.listeners.alert;

import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.codeovo.alertme.AlertMe;
import io.codeovo.alertme.events.TwilioAlertEvent;
import io.codeovo.alertme.exception.AlertMeException;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.sql.Timestamp;
import java.util.Date;

public class AlertListener implements Listener {
    private AlertMe alertMe;

    public AlertListener(AlertMe alertMe) { this.alertMe = alertMe; }

    @EventHandler(ignoreCancelled = true)
    public void onAlert(TwilioAlertEvent e) {
        String broadcastMessage = (alertMe.getPluginConfig().getPrefix() + e.getMessage())
                .replace("%time%", new Timestamp(new Date().getTime()).toString());

        switch (e.getAlertType()) {
            case SMS:
                for (String number : alertMe.getPluginConfig().getToAlertNumbers()) {
                    handleMessageSending(number, broadcastMessage);
                }

                break;
            case VOICE:
                break;
            case BOTH:
                for (String number : alertMe.getPluginConfig().getToAlertNumbers()) {
                    handleMessageSending(number, broadcastMessage);
                }

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

    private void handleMessageSending(final String toPhoneNumber, final String broadcastMessage) {
        Bukkit.getScheduler().runTaskAsynchronously(alertMe, new Runnable() {
            public void run() {
                try {
                    Message message = Message.creator(new PhoneNumber(toPhoneNumber),
                            new PhoneNumber(alertMe.getPluginConfig().getTwilioNumber()),
                            broadcastMessage).create();

                    if (alertMe.getPluginConfig().isDebug()) {
                        alertMe.getLogger().info("AlertMe - Message sent: " + message.getSid() + ", Price: " +
                                message.getPrice() + " " + message.getPriceUnit().getCurrencyCode());
                    }
                } catch (TwilioException e) {
                    alertMe.getLogger().info("AlertMe - Message sending failed: " + e.getMessage());

                    if (alertMe.getPluginConfig().isDebug()) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
