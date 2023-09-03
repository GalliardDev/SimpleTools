# SimpleTools README

# Commands
> [ ] - optional arguments<br>
> < > - required arguments<br>

> **/simpletools -** Base/Info command<br>
> **/spawn [player] -** Teleports you or another player to the spawn<br>
> **/freefall <player> -** Makes a player fall from their Y coordinate plus 200 blocks<br>
> **/discord -** Gives you your discord invitation link if set in config<br>
> **/globalchest [player] -** Opens an inventory (for you or another player) which allows you and other players to deposit and withdraw items. It is useful to community projects _(I'm working on a way to prevent stealing)_<br>
> **/thunder <player> <times> -** Strikes a player with a thunder <times> times<br>
> **/sendcoords <player> -** Sends your coordinates to another player in a private message<br>
> **/astick -** Gives yourself the admin stick<br>
> **/worldblock <world> -** Blocks a world for players to not enter<br>
> **/lobby [player] -** Teleports you or another player to the lobby world if set in config<br>
> **/payxp <player> <amount> -** Pays a player a specified amount of XP levels<br>
> **/streload -** Reloads the config.<br>

# Permissions
> **simpletools.&#42; -** This gives you all the perms (not recommended)<br>
> **simpletools.reload -** Allows you to reload the plugin config (WIP) <br>
> **simpletools.spawn -** Allows you to use /spawn command<br>
> **simpletools.spawn.others -** Allows you to use /spawn &lt;player&gt; command<br>
> **simpletools.freefall -** Allows you to use /freefall &lt;player&gt; command<br>
> **simpletools.discord -** Allows you to use /discord command<br>
> **simpletools.globalchest -** Allows you to use /globalchest command<br>
> **simpletools.globalchest.others -** Allows you to use /globalchest &lt;player&gt; command<br>
> **simpletools.thunder -** Allows you to use /thunder &lt;player&gt; &lt;times&gt; command<br>
> **simpletools.sendcoords -** Allows you to use /sendcoords &lt;player&gt; command<br>
> **simpletools.astick -** Allows you to use /astick command<br>
> **simpletools.worldblock -** Allows you to use /worldblock &lt;world&gt; command<br>
> **simpletools.lobby -** Allows you to use /lobby command<br>
> **simpletools.lobby.others -** Allows you to use /lobby &lt;player&gt; command<br>
> **simpletools.payxp -** Allows you to use /payxp &lt;player&gt; &lt;amount&gt; command<br>
> **simpletools.adminchat -** Allows you to use the adminchat<br>
> **simpletools.chatformat -** Allows you to use format in the chat

# [NEW!] Recipes

## Scissors
> These are the **Scissors**, with them you can cut some mobs just like this poor creeper!<br>
> ![Scissors](https://exmaster.es/assets/images/minecraft/scissorsRecipe.png)<br>
> ![Scissors](https://exmaster.es/assets/images/minecraft/creeperScissors1.png)<br>
> ![Scissors](https://exmaster.es/assets/images/minecraft/creeperScissors2.png)<br>

## Admin Stick
> This is the **Admin Stick**, with it Admins can control or punish players (btw it instakills u)<br>
> ![Admin Stick](https://exmaster.es/assets/images/minecraft/adminStickRecipe.png)

## Cookable Rotten Flesh
> This recipe allows you to cook Rotten Flesh in a Campfire, it will take 10 minutes and it will give you Raw Beef with a 70% of probability and Bones with a 30% of probability<br>
> ![Admin Stick](https://exmaster.es/assets/images/minecraft/rottenFleshRecipe1.png)<br>
> ![Admin Stick](https://exmaster.es/assets/images/minecraft/rottenFleshRecipe2.png)

## Convertible Pillager 
> Last but not least, you can turn a Pillager into a Baby Villager using an Undying Totem but it has a 15% chance to work!<br>
> ![Admin Stick](https://exmaster.es/assets/images/minecraft/pillagerTotem1.png)<br>
> ![Admin Stick](https://exmaster.es/assets/images/minecraft/pillagerTotem2.png)<br>
> ![Admin Stick](https://exmaster.es/assets/images/minecraft/pillagerTotem3.png) _Don't ask why the villager is black_


# Placeholders
> Coming soon...

# config.yml
```YAML
config:
  # TITLES
  joinTitle: true
  leaveTitle: true 
  deathTitle: true
  harvestOnRightClick: true
  autoItemRefill: true
  # LOBBY
  lobby: "lobby"

language:
  # es_ES ============================================================================================================
  # COSAS DEL PLUGIN
  prefix: "&6[&9SimpleTools&6]"
  configReloaded: "&aSe ha recargado la configuración!"
  # ERRORES
  onlyPlayerCommand: "&cEste comando sólo lo puede ejecutar un jugador"
  noPermission: "&cNo tienes permisos para eso!"
  tooManyArguments: "&cDemasiados argumentos!"
  playerRequired: "&cDebes especificar un jugador!"
  invalidArgument: "&cArgumento no válido!"
  temporarilyDisabled: "&cEste comando está temporalmente desactivado"
  # COMANDO GLOBAL CHEST
  globalChestTitle: "&8Cofre global"
  # COMANDO DEL SPAWN
  spawnSelf: "&7Has sido teletransportado al spawn"
  spawnYouOthers: "&7Has teletransportado a &a%victim% &7al spawn"
  spawnOthersYou: "&7Has sido teletransportado al spawn por &a%sender%"
  # COMANDO DEL LOBBY
  lobbySelf: "&7Has sido teletransportado al lobby"
  lobbyYouOthers: "&7Has teletransportado a &a%victim% &7al lobby"
  lobbyOthersYou: "&7Has sido teletransportado al lobby por &a%sender%"
  noLobby: "&cEl lobby no está definido!"
  # COMANDO DEL LINK DE DISCORD
  discordMsg: "&7Aquí tienes nuestro discord, &a%sender% &7:\n&9&nhttps://discord.gg/HHtQ8wU2TK"
  # COMANDO DE CAÍDA LIBRE
  freefallMsg: "&d&lHas sido teletransportado aquí arriba por nuv"
  # MENSAJES DE LOS TÍTULOS
  deathTitleMsg: "&c&l%player% ha muerto"
  joinTitleMsg: "&aHa entrado al servidor!"
  leaveTitleMsg: "&cSe ha ido del servidor!"
  joinLeaveNameFormat: "&e"
  # COMANDO DE ENVIAR COORDENADAS
  coordsMsg: "&8[&c%sender%&8 -> &cTú&8] &7Mis coordenadas son:\n&aX: &7%x%\n&aY: &7%y%\n&aZ: &7%z%"
  # NOMBRES DE LOS ITEMS CUSTOM
  scissorsName: "&e&lTijeras"
  scissorsLore: "&7Vacas, cerdos, zombies"
  adminStickName: "&c&lADMIN STICK"
  adminStickLore: "&dUn palo para dominarlos a todos!"
  # BLOQUEADOR DE MUNDOS
  worldIsBlocked: "&cEste mundo está bloqueado"
  worldBlocked: "&cSe ha bloqueado el mundo"
  worldUnblocked: "&aSe ha desbloqueado el mundo"
  # COMANDO DE PAGAR/ENVIAR XP
  youGotPaidXP: "&e%player% &7te ha dado &e%amount% &7niveles de &aXP"
  notEnoughLevels: "&cNo tienes suficientes niveles para esto!"
  # ==================================================================================================================
  
  # en_US ============================================================================================================
  # PLUGIN THINGS
  #prefix: "&6[&9SimpleTools&6]"
  #configReloaded: "&aConfig reloaded!"
  # ERRORS
  #onlyPlayerCommand: "&cThis command can only be executed by a player"
  #noPermission: "&cYou don't have permissions to do that!"
  #tooManyArguments: "&cToo many arguments!"
  #playerRequired: "&cYou must specify a player!"
  #invalidArgument: "&cInvalid argument!"
  #temporarilyDisabled: "&cThis command is temporarily disabled"
  # GLOBAL CHEST COMMAND
  #globalChestTitle: "&8Global chest"
  # SPAWN COMMAND
  #spawnSelf: "&7You have been teleported to the spawn"
  #spawnYouOthers: "&7You've sent &a%victim% &7to the spawn"
  #spawnOthersYou: "&7You've been sent to the spawn by &a%sender%"
  # LOBBY COMMAND
  #lobbySelf: "&7You have been teleported to lobby"
  #lobbyYouOthers: "&7You've sent &a%victim% &7to the lobby"
  #lobbyOthersYou: "&7You've been sent to the lobby by &a%sender%"
  #noLobby: "&cThere's no lobby defined in the config!"
  # DISCORD COMMAND
  #discordMsg: "&7Here's our discord, &a%sender% &7:\n&9&nhttps://discord.gg/HHtQ8wU2TK"
  # FREE FALL COMMAND
  #freefallMsg: "&d&lU have been teleported up here, fool!"
  # TITLES MESSAGES
  #deathTitleMsg: "&c&l%player% has died"
  #joinTitleMsg: "&aJoined the server!"
  #leaveTitleMsg: "&cLeft the server!"
  #joinLeaveNameFormat: "&e"
  # COORDS MESSAGE
  #coordsMsg: "&8[&c%sender%&8 -> &cYou&8] &7These are my coords:\n&aX: &7%x%\n&aY: &7%y%\n&aZ: &7%z%"
  # ITEMS NAMES
  #scissorsName: "&e&lScissors"
  #scissorsLore: "&7Try cutting off some cows, pigs or zombies"
  #adminStickName: "&c&lADMIN STICK"
  #adminStickLore: "&dA stick to rule them all!"
  # WORLD BLOCKER
  #worldIsBlocked: "&cThis world is blocked"
  #worldBlocked: "&cThe world has been blocked"
  #worldUnblocked: "&aThe world has been unblocked"
  # XP PAY
  #youGotPaidXP: "&e%player% &7sent you &e%amount% &aXP &7levels"
  #notEnoughLevels: "&cYou don't have enough levels for this!"
  # ==================================================================================================================

version: 2.0.0
```
