package tresor;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PayCommandExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("pay")) {
        	
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "Mets bien le pseudo et le montant");
                return false;
            }
            
            Player senderPlayer = (Player) sender;
            String targetName = args[0];
            Player targetPlayer = senderPlayer.getServer().getPlayer(targetName);

            if (targetPlayer == null || !targetPlayer.isOnline()) {
                sender.sendMessage(ChatColor.RED + "Le joueur spécifié n'est pas en ligne !");
                return false;
            }

            double amount;
            try {
                amount = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Le montant spécifié n'est pas valide !");
                return false;
            }
        	
            ItemStack customSunflower = getCustomNamedSunflower(amount);
            targetPlayer.getInventory().addItem(customSunflower);
            targetPlayer.sendMessage(ChatColor.GREEN + "Vous avez reçu " + ChatColor.YELLOW + amount + ChatColor.GREEN + " tournesols de la part de " + ChatColor.YELLOW + senderPlayer.getName() + ChatColor.GREEN + " !");
            return true;
        }
        return false;
	}
	
	private ItemStack getCustomNamedSunflower(double amount) {
	    ItemStack sunflower = new ItemStack(Material.SUNFLOWER, (int) amount);
	    ItemMeta meta = sunflower.getItemMeta();
	    meta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "coucou les copines");
	    sunflower.setItemMeta(meta);
	    return sunflower;
	}

}
