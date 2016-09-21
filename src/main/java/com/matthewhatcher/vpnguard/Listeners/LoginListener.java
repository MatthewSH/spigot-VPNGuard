package com.matthewhatcher.vpnguard.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		String ipAddress = event.getAddress().toString().replace("/", "");
		
		if(plugin.file.isInCache(ipAddress) || plugin.web.isVPN(ipAddress)) {
			if(!plugin.file.isInCache(ipAddress)) {
				plugin.file.addIP(ipAddress);
			}
			
			if(!player.hasPermission("vpnguard.allowvpn")) {
				plugin.getLogger().info(PluginMessages.CONSOLE_BLOCKEDLOGIN.replace("%name%", player.getName()).replace("%ip%", ipAddress));
				event.setKickMessage(plugin.config.kickMessage.replace("%name%", player.getName()).replace("%ip%", ipAddress).replace("&", "§"));
				event.setResult(Result.KICK_OTHER);
			}
		}
	}
}
