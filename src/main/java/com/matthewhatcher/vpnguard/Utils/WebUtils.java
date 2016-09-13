package com.matthewhatcher.vpnguard.Utils;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.matthewhatcher.vpnguard.VPNGuard;

public class WebUtils 
{
	@SuppressWarnings("unused")
	private VPNGuard plugin;
	String apiUrl;
	String fallbackApiUrl;
	String mainUrl = "getipintel.net";
	String fallbackUrl = "iphub.info";
	
	public WebUtils(VPNGuard plugin) {
		this.plugin = plugin;
		
		if(plugin.config.hasEmail()) {
			apiUrl = "http://check.getipintel.net/check.php?ip=%ip%&flags=m&format=json&contact=" + plugin.config.adminEmail;
			fallbackApiUrl = "http://legacy.iphub.info/api.php?ip=%ip%&showtype=4&email=" + plugin.config.adminEmail;
		} else {
			apiUrl = null;
			fallbackApiUrl = "http://legacy.iphub.info/api.php?ip=%ip%&showtype=4";
		}
	}
	
	public boolean apiIsOnline() {
		if(apiUrl == null)
			return false;
		
		if(online(mainUrl))
			return true;
		else
			return false;
	}
	
	public boolean fallbackIsOnline() {
		if(fallbackApiUrl == null)
			return false;
		
		if(online(fallbackUrl))
			return true;
		else
			return false;
	}
	
	public boolean isVPN(String ip) {
		JsonObject obj;
		String response;
		
		if((apiUrl != null && apiUrl.length() > 2) && apiIsOnline()) {
			response = HttpRequest.get(apiUrl.replace("%ip%", ip)).body();
			obj = new JsonParser().parse(response).getAsJsonObject();
			
			if(obj.get("status").getAsString().equalsIgnoreCase("success")) {
				if(obj.get("result").getAsInt() == 1)
					return true;
			}
				
			return false;
		} else if((fallbackApiUrl != null && fallbackApiUrl.length() > 2) && fallbackIsOnline()) {
			response = HttpRequest.get(fallbackApiUrl.replace("%ip%", ip)).body();
			obj = new JsonParser().parse(response).getAsJsonObject();
			if(obj.get("proxy").getAsInt() == 1)
				return true;
				
			return false;
		}
		
		return false;
	}
	
	private boolean online(String host) {
		String response = HttpRequest.get(host).body();
		if(response.contains("It's just you")) 
			return true;
		else
			return false;
	}
}
