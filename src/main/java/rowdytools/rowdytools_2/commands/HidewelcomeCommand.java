package rowdytools.rowdytools_2.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HidewelcomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("hidewelcome")) { //Команда hidewelcome
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Эту команду может использовать только игрок!");
                return true;
            }
            Player sender_ = (Player) sender;
            if (!sender_.hasPermission("rowdytools.hidewelcome")) {
                sender_.sendMessage(ChatColor.RED + "У вас недостаточно прав для использования этой команды.");
                return true;
            }
            if (sender_.getScoreboardTags().contains("hidewelcome")) {
                sender_.removeScoreboardTag("hidewelcome");
                sender_.sendMessage(ChatColor.GREEN + "Вы включаете звук приветствия новых игроков.");
            } else {
                sender_.addScoreboardTag("hidewelcome");
                sender_.sendMessage(ChatColor.GREEN + "Вы отключаете звук приветствия новых игроков.");
            }
            return true;
        }
        return false;
    }
}
