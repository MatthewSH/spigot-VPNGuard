package com.matthewhatcher.vpnguard;

import org.bukkit.ChatColor;

public class PluginMessages 
{
	public static String DEFAULT_KICK_MESSAGE = "Sorry, but your IP (%ip%) seems to be a VPN and you were kicked. Please turn it off and try again.";
	public static String PREFIX = ChatColor.WHITE + "[" + 
								  ChatColor.GOLD + "VPN Guard" +
								  ChatColor.WHITE + "] ";
	public static String PREFIX_NOCOLOR = "[VPN Guard] ";
	public static String NO_PERMISSION = PREFIX + "Sorry, but you do not have permission to run that command.";
	public static String INVALID_COMMAND = PREFIX + "I don't recognize that command.";
	public static String[] HELP_MESSAGE_COMMANDS = {
		"/vpng refresh | Force refreshes the cache.",
		"/vpng reload  | Reloads the plugin configuration"
	};
	public static String COMMAND_REFRESH = PREFIX + "Refreshing cache...";
	public static String COMMAND_REFRESH_COMPLETED = PREFIX + "Cache has been refreshed.";
	public static String COMMAND_RELOAD = PREFIX + "Reloading config...";
	public static String COMMAND_RELOAD_COMPLETED = PREFIX + "Configuration has been reloaded.";
	public static String CONSOLE_BLOCKEDLOGIN = "%name% (%ip%) tried to log in, kicked for VPN.";
	public static String CONSOLE_COMMAND_PURGE = "Okay then, clearing the cache file.";
	public static String CONSOLE_COMMAND_PURGE_COMPLETED = "Cache file has been emptied, may God help you.";
}
