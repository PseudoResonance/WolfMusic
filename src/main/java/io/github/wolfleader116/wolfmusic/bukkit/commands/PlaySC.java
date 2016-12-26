package io.github.wolfleader116.wolfmusic.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.wolfleader116.wolfapi.bukkit.Errors;
import io.github.wolfleader116.wolfapi.bukkit.Message;
import io.github.wolfleader116.wolfapi.bukkit.SubCommandExecutor;
import io.github.wolfleader116.wolfapi.bukkit.WolfAPI;
import io.github.wolfleader116.wolfmusic.bukkit.JukeboxController;
import io.github.wolfleader116.wolfmusic.bukkit.SongFile;
import io.github.wolfleader116.wolfmusic.bukkit.WolfMusic;

public class PlaySC implements SubCommandExecutor {
	
	Message message = new Message(WolfMusic.plugin);

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (sender.hasPermission("wolfmusic.play")) {
				SongFile[] songs = WolfMusic.getSongs();
				if (songs.length == 0) {
					message.sendPluginError(sender, Errors.CUSTOM, "There are no songs on the server!");
				} else {
					Player p = (Player) sender;
					if (JukeboxController.isPlaying(p)) {
						message.sendPluginMessage(sender, "Music is already playing!");
					} else {
						JukeboxController.nextSong(p);
					}
				}
			} else {
				WolfAPI.message.sendPluginError(sender, Errors.NO_PERMISSION, "play music!");
				return false;
			}
		} else {
			message.sendPluginError(sender, Errors.CUSTOM, "This command is for players only!");
			return false;
		}
		return true;
	}

}
