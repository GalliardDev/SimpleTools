# Commands:
> **/spawn [player] -** Teleports you or other player to the spawn<br>
> **/freefall <player> -** Makes a player fall from their Y + 200<br>
> **/discord -** Gives you your discord invitation link if exists<br>
> **/globalchest [player] -** Opens an inventory (for you or another player) which allows you and other players to deposit and withdraw items. It is useful to community projects (I'm working on a way to prevent stealing)<br>
> **/thunder <player> <times> -** Strikes a player with a thunder <times> times<br>
> **/simpletools [reload] -** Shows basic info of the plugin. If reload argument is provided, it'll reload the config.<br>

# Permissions:
> **SimpleTools.&#42; -** All commands<br>
> **SimpleTools.spawn -** /spawn<br>
> **SimpleTools.spawn.others -** /spawn <player><br>
> **SimpleTools.thunder -** /thunder <player> <times><br>
> **SimpleTools.discord -** /discord<br>
> **SimpleTools.globalchest -** /globalchest<br>
  > **SimpleTools.globalchest.others -** /globalchest <player><br>
> **SimpleTools.freefall -** /freefall <player><br>
> **SimpleTools.simpletools -** /simpletools<br>
  > **SimpleTools.simpletools.reload -** /simpletools reload<br>

# Config file:
```YAML
language:

  # PLUGIN THINGS
  prefix: "&6[&9SimpleTools&6]"
  configReloaded: "&aSe ha recargado la configuración!"

  # ERRORS
  onlyPlayerCommand: "&cEste comando sólo lo puede ejecutar un jugador"
  noPermission: "&cNo tienes permisos para eso!"
  tooManyArguments: "&cDemasiados argumentos!"
  playerRequired: "&cDebes especificar un jugador!"
  invalidArgument: "&cArgumento no válido!"

  # GLOBAL CHEST COMMAND
  globalChestTitle: "&8Cofre global"

  # SPAWN COMMAND
  spawnSelf: "&7Has sido teletransportado al spawn"
  spawnYouOthers: "&7Has teletransportado a &a%victim% &7al spawn"
  spawnOthersYou: "&7Has sido teletransportado al spawn por &a%sender%"

  # DISCORD COMMAND
  discordMsg: "&7Aquí tienes nuestro discord, &a%sender% &7:\n&9&nhttps://discord.gg/HHtQ8wU2TK"
  
  # FREE FALL COMMAND
  freefallMsg: "&d&lHas sido teletransportado aquí arriba por nuv" 

version: 1.2.3
```

# Placeholders:
>Coming soon...
