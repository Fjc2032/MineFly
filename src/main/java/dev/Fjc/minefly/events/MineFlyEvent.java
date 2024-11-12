package dev.Fjc.mineFly.events;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.UUID;


public class MineFlyEvent extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    private final double mineFlySpeed = getConfig().getDouble("set-mining-speed-flying", 20);
    private final double notFlyingSpeed = getConfig().getDouble("set-mining-speed-not-flying", 4);

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFlight(PlayerToggleFlightEvent event) {
        //First we declare the player
        Player player = event.getPlayer();

        //Check if the player is actually flying
        if (event.isFlying()) {
            updateMiningSpeed(player, mineFlySpeed);
        } else {
            updateMiningSpeed(player, notFlyingSpeed);
        }
    }

    //logic for updateMiningSpeed()
    private void updateMiningSpeed(Player player, double newSpeed) {
        AttributeModifier speedModifier = new AttributeModifier(UUID.randomUUID(), "speedModifier", 20, AttributeModifier.Operation.ADD_NUMBER);
        Objects.requireNonNull(player.getAttribute(Attribute.PLAYER_BLOCK_BREAK_SPEED)).addModifier(speedModifier);
    }

}
