package com.matthewhatcher.vpnguard.Utils;

import java.util.ArrayList;
import java.util.List;

import com.matthewhatcher.vpnguard.VPNGuard;

public class FileUtils 
{
	private VPNGuard plugin;
	public List<String> cachedIPs = new ArrayList<String>();
	
	public FileUtils(VPNGuard plugin) {
		this.plugin = plugin;
	}

}
