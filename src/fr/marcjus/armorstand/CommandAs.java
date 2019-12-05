package fr.marcjus.armorstand;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import net.minecraft.server.v1_12_R1.PlayerInteractManager;
import net.minecraft.server.v1_12_R1.WorldServer;

public class CommandAs implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		Location loc = player.getLocation();

		if (sender instanceof Player) {
			if (args.length == 0) {
				ArmorStand stand = (ArmorStand) loc.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);
				stand.setArms(true);
				stand.setCustomName("§eR.I.P");
				stand.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
				stand.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
				stand.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
				stand.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
				stand.setCustomNameVisible(true);
				stand.setVisible(true);
				stand.setRightArmPose(new EulerAngle(5, -5, -6));
				stand.setLeftArmPose(new EulerAngle(5, 5, 6));
			} else if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("nms")) {
					createNpc(player, "MarcJus");
					player.sendMessage("§2Nms!");
				}
			}

		} else {
			sender.sendMessage("Cette commande est pour les joueurs uniquement!");
		}
		return false;
	}

	private void createNpc(Player player, String npcName) {
		Location loc = player.getLocation();
		MinecraftServer nmsServ = ((CraftServer) Bukkit.getServer()).getServer();
		WorldServer nmsWorld = ((CraftWorld) player.getWorld()).getHandle();
		GameProfile gameProfile = new GameProfile(UUID.fromString("16d2ef59-64e7-4fa1-90a2-20d0b3223251"),"§a§1" + npcName);
		
		EntityPlayer npc = new EntityPlayer(nmsServ, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld));
		Player npcPlayer = npc.getBukkitEntity().getPlayer();
		npcPlayer.setPlayerListName("");
		
		npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		
		PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
		connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
		
	}

}
