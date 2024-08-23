import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MineFly extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        // Register events with the server, probably
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (event.isFlying()) {
            // When a player starts flying, these events are called
            double flyingSpeed = getConfig().getDouble("set-mining-speed.flying", 20.0);
            updateMiningSpeed(player, flyingSpeed);
        } else {
            // When a player stops flying, these events are called
            double notFlyingSpeed = getConfig().getDouble("set-mining-speed.not-flying", 4.0);
            updateMiningSpeed(player, notFlyingSpeed);
        }

        // Save config.yml
        FileConfiguration config = getConfig();
        if (config.get("set-mining-speed.flying") == null) {
            config.set("set-mining-speed.flying", 20.0);
            config.set("set-mining-speed.not-flying", 4.0);
            saveConfig();
        }
    }

    private void updateMiningSpeed(Player player, double newSpeed) {
        // Update the player's attack speed attribute
        AttributeModifier speedModifier = new AttributeModifier("generic.mining_speed", newSpeed, AttributeModifier.Operation.MULTIPLY_ADD); //I will need to test different operators eventually
        player.getAttribute(Attribute.GENERIC_MINING_SPEED).addModifier(speedModifier);
    }
}
