package rowdytools.rowdytools_2;

import rowdytools.rowdytools_2.commands.*;
import rowdytools.rowdytools_2.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class RowdyTools2 extends JavaPlugin {

    private static RowdyTools2 instance;

    public static RowdyTools2 getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Создание папки для хранения файлов плагина, если ее нет
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }

        // Создание файла конфигурации, если его нет
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }

        List<String> soundList = getConfig().getStringList("soundList");

        getServer().getPluginManager().registerEvents(new TotemListener(soundList), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getCommand("hidewelcome").setExecutor(new HidewelcomeCommand());
        getCommand("jump").setExecutor(new JumpCommand());
        getCommand("name").setExecutor(new NameCommand());
        getCommand("ngive").setExecutor(new NgiveCommand());
        getCommand("totems").setExecutor(new TotemsCommand(soundList));
        ReloadCommand reloadCommand = new ReloadCommand(this);
        getCommand("rowdytools").setExecutor(reloadCommand);
        getCommand("rt").setExecutor(reloadCommand);
    }
}
