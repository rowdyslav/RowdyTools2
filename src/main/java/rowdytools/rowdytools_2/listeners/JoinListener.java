package rowdytools.rowdytools_2.listeners;

import rowdytools.rowdytools_2.RowdyTools2;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class JoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            Bukkit.getScheduler().runTaskLater(JavaPlugin.getPlugin(RowdyTools2.class), () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at @a[tag=!hidewelcome] as @a[tag=!hidewelcome] run playsound minecraft:rowdysounds.welcome master @s");
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&a" + player.getName() + " играет впервые, добро пожаловать!"));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tellraw @a[tag=!hidewelcome] {\"text\":\"(чтобы убрать звук пропиши команнду /hidewelcome)\",\"color\":\"gray\"}");
            }, 200L); // 200 тики = 10 секунд
        }
    }
}
