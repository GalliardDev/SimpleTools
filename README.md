# Commands:
> **/spawn [player] -** Teleports you or other player to the spawn<br>
> **/freefall <player> -** Makes a player fall from their Y + 200<br>
> **/discord -** Gives you your discord invitation link if exists<br>
> **/globalchest -** Opens an inventory which allows you and other players to deposit and withdraw items. It is useful to community projects (I'm working on a way to prevent stealing)<br>
> **/thunder <player> <times> -** Strikes a player with a thunder <times> times<br>

# Permissions:
> **SimpleTools.&#42; -** All commands<br>
> **SimpleTools.spawn -** /spawn<br>
> **SimpleTools.spawn.others -** /spawn <player><br>
> **SimpleTools.thunder -** /thunder <player> <times><br>
> **SimpleTools.discord -** /discord<br>
> **SimpleTools.globalchest -** /globalchest<br>
> **SimpleTools.freefall -** /freefall <player><br>

# Config file:
```YAML
language:
  # prefix
  prefix: §6[§9SimpleTools§6]
  # Errors
  onlyPlayerCommand: §cEste comando sólo lo puede ejecutar un
    jugador
  noPermission: §cNo tienes permisos para esto, put!
  tooManyArguments: §cDemasiados argumentos!
  playerRequired: §cDebes especificar un jugador!
  # Spawn Command
  spawnSelf:  §7Has sido teletransportado al spawn
  spawnYouOthers: §7Has teletransportado a §a%victim% §7al spawn
  spawnOthersYou: §7Has sido teletransportado al spawn por §a%sender%
  # Discord Command
  discordMsg: |-
     §7Aquí tienes nuestro discord, §a%sender% §7:
    §9§nhttps://discord.gg/HHtQ8wU2TK
  # Free Fall Command
  freefallMsg: §d§lHas sido teletransportado aquí arriba por nuv
```

# Placeholders:
>Coming soon...
