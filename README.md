# VPN Guard
VPN Guard is an open-source Spigot plugin that has VPN file caching and has 2 web APIs to switch between.

## Config
`kick-message` - The message displayed when a user is kicked, `%name%` is replaced with the name and `%ip%` is replaced with IP. Colors will be converted if formated as `&#` where `#` is an integer.

`email-address` (Optional) - If left out, the API url will change. If you're on a server that has a lot of activity, this is suggested as the API hosts may try to contact you to work out something with you because of so much API usage. If left blank and you're rapidly sending request, the host may block the IP of the server. Without a contact email, Get IP Intel will not work and will fallback on IP Hub (and only IP Hub).

`allow-vpn-connection` - This will allow users with the `vpnguard.allowvpn` permission to connect.

## Commands
`vpng refresh` - Force refreshes the cache.

`vpng reload` - Reloads the config.

`vpngpurge` (Console Command) - **This purges the VPN cache completely and will call the APIs immediately, use with caution!**

## Permissions
`vpnguard.bypass` - Allows a user to log in with a VPN. (Defaults false). `vpnguard.allowvpn` will be removed in 2.0. It's currently depreciated.

`vpnguard.command.refresh` - Allows use of the refresh command. (Defaults OP)

`vpnguard.command.reload` - Allows use of the reload command. (Defaults OP)

## APIs
Two APIs are used in the plugin.
[Get IP Intel](https://getipintel.net/) is the primary API, with a fallback on [IP Hub](https://iphub.info/) in case of server downtime. If both servers are down, it will fail out.

## Caching
A file is created called `blacklist_cache` this is a file containing known VPN IPs and a file called `whitelist_cache` with known user IPs. Each IP is separated by a line break. Usually, this should be left alone, but if you want to add your own list of known IPs then you can. Here's an example of how it should look:
```
84.19.165.213
84.19.169.235
84.19.165.218
84.19.165.219
84.19.169.174
217.114.211.244
84.19.169.228
217.114.211.242
```

If you want to take a look at some premade stuff, or contribute to it, take a look [here](https://github.com/MatthewSH/VPNGuard-IP-List)
