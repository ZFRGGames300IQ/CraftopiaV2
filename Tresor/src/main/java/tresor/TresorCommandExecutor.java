package tresor;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TresorCommandExecutor implements CommandExecutor {

    private final Map<Material, Integer> maxItemsMap = new HashMap<>();

    public TresorCommandExecutor() {
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
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Cette commande ne peut être exécutée que par un joueur !");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("tresor")) {
            Location playerLocation = player.getLocation();
            Block chestBlock = playerLocation.getBlock();
            chestBlock.setType(Material.CHEST);
            Inventory chestInventory = ((org.bukkit.block.Chest) chestBlock.getState()).getBlockInventory();

            fillChest(chestInventory);

            return true;
        }
        return false;
    }

    private void fillChest(Inventory chestInventory) {
        Random random = new Random();
        for (Map.Entry<Material, Integer> entry : maxItemsMap.entrySet()) {
            Material material = entry.getKey();
            int maxItems = entry.getValue();
            double chancePercentage = 0;
            
            if (random.nextInt(100) < 10) {
                chestInventory.addItem(new ItemStack(Material.OAK_WOOD, 10));
            }
            else if (random.nextInt(100) < 15) {
                chestInventory.addItem(new ItemStack(Material.COOKED_BEEF, 10));
            }
            else if (random.nextInt(100) < 10) {
                chestInventory.addItem(new ItemStack(Material.IRON_HOE, 1));
            }
            else if (random.nextInt(100) < 10) {
                chestInventory.addItem(new ItemStack(Material.IRON_INGOT, 10));
            }
            else if (random.nextInt(100) < 15) {
                chestInventory.addItem(new ItemStack(Material.COAL, 10));
            }
            else if (random.nextInt(100) < 10) {
                chestInventory.addItem(new ItemStack(Material.SPONGE, 1));
            }
            else if (random.nextInt(100) < 10) {
                chestInventory.addItem(new ItemStack(Material.DIAMOND, 10));
            }
            else if (random.nextInt(100) < 5) {
                chestInventory.addItem(new ItemStack(Material.BEACON, 1));
            }
            else if (random.nextInt(100) < 5) {
                chestInventory.addItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
            }
            else if (random.nextInt(100) < 5) {
                chestInventory.addItem(new ItemStack(Material.SPAWNER, 1));
            }
            
        }
    }
}