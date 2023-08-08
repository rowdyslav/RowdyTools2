package rowdytools.rowdytools_2.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;


public class TotemsCommand implements CommandExecutor {

    private final List<String> SOUND_LIST;

    public TotemsCommand(List<String> soundList) {
        this.SOUND_LIST = soundList;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("totems")) {
            if (!sender.hasPermission("rowdytools.totems")) {
                sender.sendMessage(ChatColor.RED + "У вас недостаточно прав для использования этой команды.");
                return true;
            }
            sender.sendMessage(ChatColor.GREEN + "Список скинов на тотемы: " + ChatColor.WHITE + String.join(ChatColor.GREEN + ", " + ChatColor.WHITE, SOUND_LIST));
            return true;
        }
        return false;
    }
}
