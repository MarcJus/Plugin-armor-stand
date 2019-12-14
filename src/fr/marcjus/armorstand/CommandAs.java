package fr.marcjus.armorstand;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class CommandAs implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		Location loc = player.getLocation();

		if (sender instanceof Player) {
			ArmorStand stand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
			stand.setArms(true);
			stand.setCustomName("§eR.I.P");
			stand.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
			stand.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
			stand.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
			stand.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
			stand.setCustomNameVisible(true);
			stand.setVisible(false);
			stand.setRightArmPose(new EulerAngle(5, -5, -6));
			stand.setLeftArmPose(new EulerAngle(5, 5, 6));
		} else {
			sender.sendMessage("Cette commande est pour les joueurs uniquement!");
		}
		return false;
	}

}
