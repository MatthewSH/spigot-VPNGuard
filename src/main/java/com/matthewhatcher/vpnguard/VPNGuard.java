package com.matthewhatcher.vpnguard;

import org.bukkit.plugin.java.JavaPlugin;

import com.matthewhatcher.vpnguard.Listeners.LoginListener;
import com.matthewhatcher.vpnguard.Utils.ConfigUtils;
import com.matthewhatcher.vpnguard.Utils.FileUtils;

public class VPNGuard extends JavaPlugin
{
	private VPNGuard instance;
	public LoginListener loginListener;
	public ConfigUtils config;
	public FileUtils file;
	
	public VPNGuard getInstance() {
		return this.instance;
	}
	
	@Override
	public void onEnable() {
		this.instance = this;
		loginListener = new LoginListener(getInstance());
		config = new ConfigUtils(getInstance());
		file = new FileUtils(getInstance());
		
		this.getServer().getPluginManager().registerEvents(loginListener, this.instance);
		
		super.onEnable();
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
}
