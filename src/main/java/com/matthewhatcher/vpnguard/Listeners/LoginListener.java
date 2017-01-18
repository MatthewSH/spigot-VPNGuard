package com.matthewhatcher.vpnguard.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import com.matthewhatcher.vpnguard.PluginMessages;
import com.matthewhatcher.vpnguard.VPNGuard;

public class LoginListener implements Listener 
{
	private VPNGuard plugin;
	
	public LoginListener(VPNGuard plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		String ipAddress = event.getAddress().toString().replace("/", "");
		
		if(plugin.file.isInWhitelistCache(ipAddress))
			return;
		
		if(plugin.file.isInBlacklistCache(ipAddress)) {
			if(this.canBypass(player))
				return;
			
			plugin.getLogger().info(PluginMessages.CONSOLE_BLOCKEDLOGIN.replace("%name%", player.getName()).replace("%ip%", ipAddress));
			event.setKickMessage(plugin.config.kickMessage.replace("%name%", player.getName()).replace("%ip%", ipAddress).replace("&", "§"));
			event.setResult(Result.KICK_OTHER);
			return;
		}
		
		if(!plugin.file.isInBlacklistCache(ipAddress) && plugin.web.isVPN(ipAddress)) {
			plugin.file.addIPToBlacklist(ipAddress);
			
			if(this.canBypass(player))
				return;
			
			plugin.getLogger().info(PluginMessages.CONSOLE_BLOCKEDLOGIN.replace("%name%", player.getName()).replace("%ip%", ipAddress));
			event.setKickMessage(plugin.config.kickMessage.replace("%name%", player.getName()).replace("%ip%", ipAddress).replace("&", "§"));
			event.setResult(Result.KICK_OTHER);
			return;
		}
		
		plugin.file.addIPToWhitelist(ipAddress);
	}
	
	private boolean canBypass(Player player) {
		if(player.hasPermission("vpnguard.bypass") || player.hasPermission("vpnguard.allowvpn"))
			return true;
		else
			return false;
	}
}
