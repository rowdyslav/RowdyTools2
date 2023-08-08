package rowdytools.rowdytools_2.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NameCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("name")) { //Команда name
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Эту команду может использовать только игрок!");
                return true;
            }
            Player sender_ = (Player) sender;
            if (sender_.hasPermission("rowdytools.name")) {
                ItemStack item = sender_.getInventory().getItemInMainHand();
                if (item.getType() == Material.AIR) {
                    sender_.sendMessage(ChatColor.RED + "Ваша рука пуста");
                    return true;
                }
                String name = String.join(" ", args);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(name);
                item.setItemMeta(meta);
                sender_.sendMessage(ChatColor.GREEN + "Предмет в вашей руке был переименован в " + ChatColor.GRAY + name + ChatColor.GREEN + ".");
                return true;
            } else {
                sender_.sendMessage(ChatColor.RED + "У вас недостаточно прав для использования этой команды.");
            }
            return true;
        }
        return false;
    }
}
