import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Commands extends JavaPlugin implements Listener {

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("fjc.minefly")) {
            sender.sendMessage(ChatColor.RED + "No permission, fool.");
            return false;
        } else {
            if (command.getName().equals("mineflyreload")) {
                reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Plugin MineFly was successfully reloaded.");
            }
        }
        return true;
    }
}


