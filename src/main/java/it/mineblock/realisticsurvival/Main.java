package it.mineblock.realisticsurvival;

import it.mineblock.mbcore.spigot.MBConfig;
import it.mineblock.mbcore.spigot.config.Configuration;
import it.mineblock.realisticsurvival.listeners.DrinkWater;
import it.mineblock.realisticsurvival.listeners.Fatigue;
import it.mineblock.realisticsurvival.listeners.JoinAndQuit;
import it.mineblock.realisticsurvival.listeners.StaminaSprint;
import it.mineblock.realisticsurvival.tasks.BossBarTask;
import it.mineblock.realisticsurvival.tasks.StaminaTask;
import it.mineblock.realisticsurvival.tasks.TemperatureTask;
import it.mineblock.realisticsurvival.tasks.WaterTask;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public class Main extends JavaPlugin {

    public static final String CONFIG = "config.yml";

    private StaminaTask staminaTask;
    private WaterTask waterTask;
    private TemperatureTask temperatureTask;
    //private ProtocolManager protocolManager;

    public static Plugin plugin;
    public static MBConfig configuration = new MBConfig();
    public static Configuration config;
    public static ArrayList<Material> fatigueBlock;
    public static ArrayList<Biome> coldBiomes;
    public static ArrayList<Biome> hotBiomes;
    public static Storage<UUID, Storage<Float, Float>> playerStats;
    public static Storage<UUID, Float> playerStamina;
    public static Storage<UUID, Float> playerTemperature;
    public static Storage<UUID, Location> playerStill;
    public static Storage<UUID, Integer> playerFoodLevel;
    public static BossBarTask bossBarTask;

    @Override
    public void onEnable() {
        plugin = this;
        //protocolManager = ProtocolLibrary.getProtocolManager();

        config = configuration.autoloadConfig(this, this.getName(), getResource(CONFIG), new File(getDataFolder(), CONFIG), CONFIG);

        registerCommands();
        registerListeners();

        playerStats = new Storage<>();
        playerStamina = new Storage<>();
        playerStill = new Storage<>();
        playerTemperature = new Storage<>();
        playerFoodLevel = new Storage<>();
        fatigueBlock = new ArrayList<>();
        coldBiomes = new ArrayList<>();
        hotBiomes = new ArrayList<>();

        registerFatigueBlocks();
        registerColdBiomes();
        registerHotBiomes();

        staminaTask = new StaminaTask();
        staminaTask.runTaskTimer(this, 20, 30);
        waterTask = new WaterTask();
        waterTask.runTaskTimer(this, 20, 100);
        temperatureTask = new TemperatureTask();
        temperatureTask.runTaskTimer(this, 20, 100);
        bossBarTask = new BossBarTask(100);
        bossBarTask.runTaskTimer(this, 20, 210);
    }

    @Override
    public void onDisable() {
        staminaTask.cancel();
        waterTask.cancel();
        temperatureTask.cancel();
        bossBarTask.cancel();
    }

    private void registerCommands(){

    }

    private void registerListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new JoinAndQuit(), this);
        pluginManager.registerEvents(new Fatigue(), this);
        pluginManager.registerEvents(new StaminaSprint(), this);
        pluginManager.registerEvents(new DrinkWater(), this);
    }

    private void registerFatigueBlocks(){
        fatigueBlock.add(Material.LEVER);
        fatigueBlock.add(Material.ACACIA_DOOR);
        fatigueBlock.add(Material.BIRCH_DOOR);
        fatigueBlock.add(Material.DARK_OAK_DOOR);
        fatigueBlock.add(Material.JUNGLE_DOOR);
        fatigueBlock.add(Material.SPRUCE_DOOR);
        fatigueBlock.add(Material.OAK_DOOR);
        fatigueBlock.add(Material.IRON_DOOR);
        fatigueBlock.add(Material.ACACIA_TRAPDOOR);
        fatigueBlock.add(Material.BIRCH_TRAPDOOR);
        fatigueBlock.add(Material.DARK_OAK_TRAPDOOR);
        fatigueBlock.add(Material.JUNGLE_TRAPDOOR);
        fatigueBlock.add(Material.OAK_TRAPDOOR);
        fatigueBlock.add(Material.SPRUCE_TRAPDOOR);
        fatigueBlock.add(Material.IRON_TRAPDOOR);
    }

    private void registerColdBiomes(){
        /*biome.equals(Biome.EXTREME_HILLS) || biome.equals(Biome.MUTATED_EXTREME_HILLS) ||
							biome.equals(Biome.EXTREME_HILLS_WITH_TREES) || biome.equals(Biome.MUTATED_EXTREME_HILLS_WITH_TREES) ||
							biome.equals(Biome.TAIGA) || biome.equals(Biome.MUTATED_TAIGA) ||
							biome.equals(Biome.TAIGA_HILLS) || biome.equals(Biome.REDWOOD_TAIGA_HILLS) ||
							biome.equals(Biome.STONE_BEACH)*/
        /*coldBiomes.add(Biome.EXTREME_HILLS);
        coldBiomes.add(Biome.MUTATED_EXTREME_HILLS);
        coldBiomes.add(Biome.EXTREME_HILLS_WITH_TREES);
        coldBiomes.add(Biome.MUTATED_EXTREME_HILLS_WITH_TREES);
        coldBiomes.add(Biome.TAIGA);
        coldBiomes.add(Biome.MUTATED_TAIGA);
        coldBiomes.add(Biome.TAIGA_HILLS);
        coldBiomes.add(Biome.TAIGA_COLD);
        coldBiomes.add(Biome.TAIGA_COLD_HILLS);
        coldBiomes.add(Biome.REDWOOD_TAIGA_HILLS);
        coldBiomes.add(Biome.STONE_BEACH);*/
    }

    private void registerHotBiomes(){

    }

    public static List<Material> getNearbyMaterials(Location location, int radius) {
        List<Material> materials = new ArrayList<>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    materials.add(location.getWorld().getBlockAt(x, y, z).getType());
                }
            }
        }
        return materials;
    }
}
