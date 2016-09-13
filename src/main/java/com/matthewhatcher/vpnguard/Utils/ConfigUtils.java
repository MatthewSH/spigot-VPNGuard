package com.matthewhatcher.vpnguard.Utils;

import com.matthewhatcher.vpnguard.PluginMessages;
import com.matthewhatcher.vpnguard.VPNGuard;

public class ConfigUtils 
{
	private VPNGuard plugin;
	
	public boolean allowVPNs = true;
	public String kickMessage = PluginMessages.DEFAULT_KICK_MESSAGE;
	public String adminEmail = null;
	
	public ConfigUtils(VPNGuard plugin) {
		this.plugin = plugin;
		
		saveDefaultConfig();
		loadConfig();
	}
	
	public void loadConfig() {
		if(plugin.getConfig().getString("email-address") != null) {
			this.adminEmail = plugin.getConfig().getString("email-address");
		}
		
		this.kickMessage = plugin.getConfig().getString("kick-message");
		this.allowVPNs = plugin.getConfig().getBoolean("allow-vpn-connection");
	}
	
	public void saveDefaultConfig() {
		plugin.getConfig().addDefault("kick-message", PluginMessages.DEFAULT_KICK_MESSAGE);
		plugin.getConfig().addDefault("allow-vpn-connection", true);
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
	
	public boolean hasEmail() {
		return (adminEmail != null);
	}
}
