package com.matthewhatcher.vpnguard.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.matthewhatcher.vpnguard.PluginMessages;
import com.matthewhatcher.vpnguard.VPNGuard;

public class ConsoleCommands implements CommandExecutor
{
	VPNGuard plugin;
	
	public ConsoleCommands(VPNGuard plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof ConsoleCommandSender) {
			if(label.equalsIgnoreCase("vpngpurge")) {
				sender.sendMessage(PluginMessages.CONSOLE_COMMAND_PURGE);
				plugin.file.purgeCache();
				sender.sendMessage(PluginMessages.CONSOLE_COMMAND_PURGE_COMPLETED);
			}
		}
		
		return false;
	}

}
