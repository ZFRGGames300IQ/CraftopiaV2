package tresor;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Tresor extends JavaPlugin {
    private final Map<Material, Integer> maxItemsMap = new HashMap<>();
	
    private final Random random = new Random();
    
    private int chancePoints = 0;
    private final int maxChancePoints = 100;
    
    public Tresor() {
        maxItemsMap.put(Material.OAK_WOOD, 10);
        maxItemsMap.put(Material.COOKED_BEEF, 10);
        maxItemsMap.put(Material.IRON_HOE, 1);
        maxItemsMap.put(Material.IRON_INGOT, 10);
        maxItemsMap.put(Material.COAL, 10);
        maxItemsMap.put(Material.SPONGE, 1);
        maxItemsMap.put(Material.DIAMOND, 10);
        maxItemsMap.put(Material.BEACON, 1);
        maxItemsMap.put(Material.DIAMOND_LEGGINGS, 1);
        maxItemsMap.put(Material.SPAWNER, 1);
    }
	
    @Override
    public void onEnable() {
    	getLogger().info("Trésor est invoqué");
        this.getCommand("tresor").setExecutor(new TresorCommandExecutor());
        this.getCommand("pay").setExecutor(new PayCommandExecutor());
        
        Bukkit.getScheduler().runTaskTimer(this, this::spawnCoffre, 0, 720);
        //Bukkit.getScheduler().runTaskTimer(this, this::spawnCoffre, 0, 72000);
    }
    
    @Override
    public void onDisable() {
    	getLogger().info("Trésor est désinvoqué");
    }
    
    private void spawnCoffre() {
    	
        int x = random.nextInt(10000) - 5000;
        int z = random.nextInt(10000) - 5000;
        int y = Bukkit.getWorlds().get(0).getHighestBlockYAt(x, z);

        if (y <= 1) {
            y = 2;
        }

        Location chestLocation = new Location(Bukkit.getWorlds().get(0), x, y, z);

        Block block = chestLocation.getBlock();
        block.setType(Material.CHEST);
        Inventory chestInventory = ((org.bukkit.block.Chest) block.getState()).getBlockInventory();
        fillChest(chestInventory);
        Bukkit.broadcastMessage("Un coffre mystérieux est apparu à la position X: " + x + ", Y: " + y + ", Z: " + z + " !");

        if (block.getState() instanceof Chest) {
            Chest chest = (Chest) block.getState();
        }
    }
    
    private void fillChest(Inventory chestInventory) {
        Random random = new Random();
        while (chancePoints < maxChancePoints) {
        for (Map.Entry<Material, Integer> entry : maxItemsMap.entrySet()) {
            Material material = entry.getKey();
            int maxItems = entry.getValue();
            double chancePercentage = 0;

            if (random.nextInt(100) < 10) {
                chestInventory.addItem(new ItemStack(Material.OAK_LOG, 10));
                addChancePoints(10);
            }
            else if (random.nextInt(100) < 15) {
                chestInventory.addItem(new ItemStack(Material.COOKED_BEEF, 10));
                addChancePoints(10);
            }
            else if (random.nextInt(100) < 10) {
                chestInventory.addItem(new ItemStack(Material.IRON_HOE, 1));
                addChancePoints(20);
            }
            else if (random.nextInt(100) < 10) {
                chestInventory.addItem(new ItemStack(Material.IRON_INGOT, 10));
                addChancePoints(30);
            }
            else if (random.nextInt(100) < 15) {
                chestInventory.addItem(new ItemStack(Material.COAL, 10));
                addChancePoints(20);
            }
            else if (random.nextInt(100) < 10) {
                chestInventory.addItem(new ItemStack(Material.SPONGE, 1));
                addChancePoints(60);
            }
            else if (random.nextInt(100) < 10) {
                chestInventory.addItem(new ItemStack(Material.DIAMOND, 10));
                addChancePoints(70);
            }
            else if (random.nextInt(100) < 5) {
                chestInventory.addItem(new ItemStack(Material.BEACON, 1));
                addChancePoints(90);
            }
            else if (random.nextInt(100) < 5) {
                chestInventory.addItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
                addChancePoints(100);
            }
            else if (random.nextInt(100) < 5) {
                chestInventory.addItem(new ItemStack(Material.SPAWNER, 1));
                addChancePoints(100);
            }
          }
        }
    }
    private void addChancePoints(int points) {
        chancePoints += points;
        if (chancePoints >= maxChancePoints) {
            chancePoints = maxChancePoints;
        }
    }
}
