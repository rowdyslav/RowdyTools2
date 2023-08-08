package rowdytools.rowdytools_2.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NgiveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ngive")) {
            if (args.length < 1 || args.length > 3) {
                sender.sendMessage(ChatColor.RED + "Использование: /ngive <предмет> [имя] [игрок]");
                return true;
            }
            Material material = Material.getMaterial(args[0].toUpperCase());
            if (material == null) {
                sender.sendMessage(ChatColor.RED + "Указанный предмет не существует!");
                return true;
            }
            String itemName = args.length > 1 ? ChatColor.translateAlternateColorCodes('&', args[1]) : material.name();
            Player target;
            if (args.length == 3) {
                target = Bukkit.getPlayer(args[2]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Игрок " + ChatColor.WHITE + args[2] + ChatColor.RED + " не найден!");
                    return true;
                }
            } else {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "Укажите игрока, чтобы использовать эту команду из консоли.");
                    return true;
                }
                target = (Player) sender;
            }
            if (!sender.hasPermission("rowdytools.ngive")) {
                sender.sendMessage(ChatColor.RED + "У вас недостаточно прав для использования этой команды.");
                return true;
            }
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(itemName);
            item.setItemMeta(meta);
            target.getInventory().addItem(item);
            sender.sendMessage(ChatColor.GREEN + "Вы выдали предмет " + ChatColor.DARK_GRAY + material.name() + ChatColor.GREEN + " с именем " + ChatColor.GRAY + itemName + ChatColor.GREEN + " игроку " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + ".");
            target.sendMessage(ChatColor.GREEN + "Вы получили предмет " + ChatColor.DARK_GRAY + material.name() + ChatColor.GREEN + " с именем " + ChatColor.GRAY + itemName + ChatColor.GREEN + " от игрока " + ChatColor.WHITE + sender.getName() + ChatColor.GREEN + ".");
            return true;
        }
        return false;
    }

}
