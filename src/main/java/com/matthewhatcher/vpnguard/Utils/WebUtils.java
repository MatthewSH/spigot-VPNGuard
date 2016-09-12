package com.matthewhatcher.vpnguard.Utils;

import com.matthewhatcher.vpnguard.VPNGuard;

public class WebUtils 
{
	private VPNGuard plugin;
	
	String apiUrl;
	String fallbackApiUrl;
	
	public WebUtils(VPNGuard plugin) {
		this.plugin = plugin;
		
		if(plugin.config.hasEmail()) {
			apiUrl = "http://check.getipintel.net/check.php?ip=%ip%&contact=" + plugin.config.adminEmail;
			fallbackApiUrl = "http://legacy.iphub.info/api.php?ip=%ip%&showtype=4&email=" + plugin.config.adminEmail;
		} else {
			apiUrl = "http://check.getipintel.net/check.php?ip=%ip%";
			fallbackApiUrl = "http://legacy.iphub.info/api.php?ip=%ip%&showtype=4";
		}
	}
	
	public boolean apiIsOnline() {
		return true;
	}
	
	public boolean fallbackIsOnline() {
		return true;
	}
}
