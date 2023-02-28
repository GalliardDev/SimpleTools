# Commands:
> **/spawn [player] -** Teleports you or other player to the spawn
> **/freefall <player> -** Makes a player fall from their Y + 200
> **/discord -** Gives you your discord invitation link if exists
> **/globalchest -** Opens an inventory which allows you and other players to deposit and withdraw items. It is useful to community projects (I'm working on a way to prevent stealing)
> **/thunder <player> <times> -** Strikes a player with a thunder <times> times

# Permissions:
> **SimpleTools.* -** All commands
> **SimpleTools.spawn -** /spawn
> **SimpleTools.spawn.others -** /spawn <player>
> **SimpleTools.thunder -** /thunder <player> <times>
> **SimpleTools.discord -** /discord
> **SimpleTools.globalchest -** /globalchest
> **SimpleTools.freefall -** /freefall <player>

# Config file:
```YAML
language:
  # onEnable
  enableMsg: §6[§9SimpleTools§6] SimpleTools ha sido habilitado!
  # onDisable
  disableMsg: §6[§9SimpleTools§6] SimpleTools ha sido deshabilitado!
  # Errors
  onlyPlayerCommand: §6[§9SimpleTools§6] §cEste comando sólo lo puede ejecutar un
    jugador
  noPermission: §6[§9SimpleTools§6] §cNo tienes permisos para esto, put!
  tooManyArguments: §6[§9SimpleTools§6] §cDemasiados argumentos!
  playerRequired: §6[§9SimpleTools§6] §cDebes especificar un jugador!
  # Spawn Command
  spawnSelf: §6[§9SimpleTools§6] §7Has sido teletransportado al spawn
  spawnYouOthers: §6[§9SimpleTools§6] §7Has teletransportado a §a%victim% §7al spawn
  spawnOthersYou: §6[§9SimpleTools§6] §7Has sido teletransportado al spawn por §a%sender%
  # Discord Command
  discordMsg: |-
    §6[§9SimpleTools§6] §7Aquí tienes nuestro discord, §a%sender% §7:
    §9§nhttps://discord.gg/HHtQ8wU2TK
  # Free Fall Command
  freefallMsg: §6[§9SimpleTools§6] §d§lHas sido teletransportado aquí arriba por nuv
```

# Placeholders:
>Coming soon...
