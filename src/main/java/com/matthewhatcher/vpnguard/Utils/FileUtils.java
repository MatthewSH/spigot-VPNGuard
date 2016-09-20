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
	public List<String> cachedIPs = new ArrayList<String>();
	
	public FileUtils(VPNGuard plugin) {
		this.plugin = plugin;
		
		loadCache();
	}
	
	public void loadCache() {
		cachedIPs.clear();
		
		try {
			String line;
			File cFile = new File(plugin.getDataFolder(), "cache");
			if(!cFile.exists()) {
				cFile.createNewFile();
			}
			
			BufferedReader br = new BufferedReader(new FileReader(cFile));
			
            while ((line = br.readLine()) != null) {
            	cachedIPs.add(line.trim());
            }
            
            br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addIP(String ip) {
		try {
			Writer w = new BufferedWriter(new FileWriter(new File(plugin.getDataFolder(), "cache"), true));
			w.append(ip);
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void purgeCache() {
		try {
			cachedIPs.clear();
			Writer w = new BufferedWriter(new FileWriter(new File(plugin.getDataFolder(), "cache"), true));
			w.write("");
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isInCache(String ip) {
		return cachedIPs.contains(ip);
	}

}
