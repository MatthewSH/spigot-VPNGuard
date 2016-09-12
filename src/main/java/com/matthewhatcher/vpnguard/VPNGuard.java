package com.matthewhatcher.vpnguard;

import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.matthewhatcher.vpnguard.Listeners.LoginListener;

public class VPNGuard extends JavaPlugin
{
	private VPNGuard instance;
	public LoginListener loginListener;
	
	@Override
	public void onEnable() {
		this.instance = this;
		loginListener = new LoginListener(this.instance);
		
		this.getServer().getPluginManager().registerEvents(loginListener, this.instance);
		
		super.onEnable();
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
}
