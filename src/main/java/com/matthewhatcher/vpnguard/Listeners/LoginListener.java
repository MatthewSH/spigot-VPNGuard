package com.matthewhatcher.vpnguard.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import com.matthewhatcher.vpnguard.VPNGuard;

public class LoginListener implements Listener 
{
	private VPNGuard plugin;
	
	public LoginListener(VPNGuard plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPreLogin(AsyncPlayerPreLoginEvent event) {
		
	}
}
