package rowdytools.rowdytools_2.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class JumpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("jump")) {
            if (args.length > 1) {
                sender.sendMessage(ChatColor.RED + "Использование: /jump <селектор>");
                return true;
            }
            if (!sender.hasPermission("rowdytools.jump")) {
                sender.sendMessage(ChatColor.RED + "У вас недостаточно прав для использования этой команды.");
                return true;
            }
            if (args.length == 0 && !(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Укажите игрока, чтобы использовать эту команду из консоли.");
                return true;
            }
            if (args.length == 0) {
                Entity sndr = (Entity) sender;
                sndr.sendMessage(ChatColor.GREEN + "Вы были подброшены вверх!");
                sndr.setVelocity(sndr.getVelocity().setY(3));
                return true;
            }
            Player target;
            if (!args[0].startsWith("@")) {
                target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "Игрок " + ChatColor.WHITE + args[0] + ChatColor.RED + " не найден!");
                    return true;
                }
                sender.sendMessage(ChatColor.GREEN + "Вы подбросили вверх игрока " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + "!");
                target.setVelocity(target.getVelocity().setY(3));
                target.sendMessage(ChatColor.GREEN + "Вы были подброшены вверх игроком " + ChatColor.WHITE + sender.getName() +  ChatColor.GREEN + "!");
                return true;
            }
            String selector = args[0];
            if (Bukkit.selectEntities(sender, selector).isEmpty()) { // check if the selector returned any entities
                sender.sendMessage(ChatColor.RED + "Неверно указан селектор"); // inform the sender of an invalid selector
                return true;
            }
            for (Entity entity : Bukkit.selectEntities(sender, selector)) {
                entity.setVelocity(entity.getVelocity().setY(3));
                if (entity instanceof Player) {
                    entity.sendMessage(ChatColor.GREEN + "Вы были подброшены вверх игроком " + ChatColor.WHITE + sender.getName() +  ChatColor.GREEN + "!");
                }
            }
            sender.sendMessage(ChatColor.GREEN + "Вы подбросили вверх всех игроков/существ, соответствующих селектору " + ChatColor.WHITE + selector + ChatColor.GREEN + "!");
            return true;
        }
        return false;
    }

}
