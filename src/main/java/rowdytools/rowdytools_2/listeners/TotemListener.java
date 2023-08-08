package rowdytools.rowdytools_2.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class TotemListener implements Listener {

    private final List<String> soundList;

    public TotemListener(List<String> soundList) {
        this.soundList = soundList;
    }

    @EventHandler
    public void onEntityResurrect(EntityResurrectEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            ItemStack mainHand = player.getInventory().getItemInMainHand();
            ItemStack offHand = player.getInventory().getItemInOffHand();
            checkTotem(player, mainHand);
            checkTotem(player, offHand);
        }
    }

    private void checkTotem(Player player, ItemStack item) {
        if (item != null && item.getType() == Material.TOTEM_OF_UNDYING && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            String displayName = meta.getDisplayName().toLowerCase();
            if (soundList.contains(displayName)) { // Проверяем, есть ли в мапе звук для данного тотема
                int x = player.getLocation().getBlockX();
                int y = player.getLocation().getBlockY();
                int z = player.getLocation().getBlockZ();
                player.getServer().dispatchCommand(player.getServer().getConsoleSender(), "playsound minecraft:rowdysounds." + displayName + " master @a " + x + " " + y + " " + z + " 1.0 1.0");
            }
        }
    }
}
