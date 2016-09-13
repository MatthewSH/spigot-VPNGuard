package com.matthewhatcher.vpnguard.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.matthewhatcher.vpnguard.PluginMessages;
import com.matthewhatcher.vpnguard.VPNGuard;

public class AdminCommands implements CommandExecutor
{
	VPNGuard plugin;
	
	public AdminCommands(VPNGuard plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if((sender instanceof Player) || (sender instanceof ConsoleCommandSender)) {
			if(label.equalsIgnoreCase("vpng") || label.equalsIgnoreCase("vpnguard")) {
				if(args.length == 0 || args.length > 1) {
					sender.sendMessage(PluginMessages.HELP_MESSAGE_COMMANDS);
					return true;
				}
				
				if(args.length == 1) {
					String cmd = args[0];
					
					if(cmd.equalsIgnoreCase("refresh")) {
						if(sender.hasPermission("vpnguard.command.refresh")) {
							sender.sendMessage(PluginMessages.COMMAND_REFRESH);
							plugin.file.loadCache();
							sender.sendMessage(PluginMessages.COMMAND_REFRESH_COMPLETED);
							return true;
						}
						
						return true;
					} else if(cmd.equalsIgnoreCase("reload")) {
						if(sender.hasPermission("vpnguard.command.reload")) {
							sender.sendMessage(PluginMessages.COMMAND_RELOAD);
							plugin.config.loadConfig();
							sender.sendMessage(PluginMessages.COMMAND_RELOAD_COMPLETED);
							return true;
						}
						
						return true;
					} else {
						sender.sendMessage(PluginMessages.HELP_MESSAGE_COMMANDS);
						return true;
					}
				}
			}
				
			return true;
		}
		
		return false;
	}
	
}
