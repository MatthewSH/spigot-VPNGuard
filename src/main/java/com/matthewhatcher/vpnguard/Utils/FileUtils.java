package com.matthewhatcher.vpnguard.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.matthewhatcher.vpnguard.VPNGuard;

public class FileUtils 
{
	private VPNGuard plugin;
	public List<String> blacklistIPs = new ArrayList<String>();
	public List<String> whitelistIPs = new ArrayList<String>();
	
	public FileUtils(VPNGuard plugin) {
		this.plugin = plugin;
		
		newCache();	
		loadCache();
	}
	
	public void loadCache() {
		blacklistIPs.clear();
		whitelistIPs.clear();
		
		try {
			String line;
			
			File bcFile = new File(plugin.getDataFolder(), "blacklist_cache");
			File wcFile = new File(plugin.getDataFolder(), "whitelist_cache");
			
			if(!bcFile.exists())
				bcFile.createNewFile();
			
			if(!wcFile.exists())
				wcFile.createNewFile();
			
			BufferedReader br = new BufferedReader(new FileReader(bcFile));
			
            while ((line = br.readLine()) != null) {
            	blacklistIPs.add(line.trim());
            }
            
            br.close();
            
            br = new BufferedReader(new FileReader(wcFile));
            
            while ((line = br.readLine()) != null) {
            	whitelistIPs.add(line.trim());
            }
            
            br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addIPToBlacklist(String ip) {
		try {
			Writer w = new BufferedWriter(new FileWriter(new File(plugin.getDataFolder(), "blacklist_cache"), true));
			w.append(ip);
			w.append("\n");
			w.close();
			blacklistIPs.add(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addIPToWhitelist(String ip) {
		try {
			Writer w = new BufferedWriter(new FileWriter(new File(plugin.getDataFolder(), "whitelist_cache"), true));
			w.append(ip);
			w.append("\n");
			w.close();
			whitelistIPs.add(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void purgeCache() {
		try {
			blacklistIPs.clear();
			whitelistIPs.clear();
			
			Writer w = new BufferedWriter(new FileWriter(new File(plugin.getDataFolder(), "blacklist_cache"), true));
			w.write("");
			w.close();
			
			w = new BufferedWriter(new FileWriter(new File(plugin.getDataFolder(), "whitelist_cache"), true));
			w.write("");
			w.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void newCache() {
		File oldCache = new File(plugin.getDataFolder(), "cache");
		File newCache = new File(plugin.getDataFolder(), "blacklist_cache");
		
		if(oldCache.exists() && !newCache.exists()) {
			oldCache.renameTo(newCache);
		}
	}
	
	public boolean isInBlacklistCache(String ip) {
		return blacklistIPs.contains(ip);
	}
	
	public boolean isInWhitelistCache(String ip) {
		return whitelistIPs.contains(ip);
	}
}
