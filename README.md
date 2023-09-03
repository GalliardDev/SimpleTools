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

  #----------------------------------#
  # MODULES                          #
  #----------------------------------#
  joinTitle: true
  leaveTitle: true 
  deathTitle: true
  harvestOnRightClick: true
  autoItemRefill: true
  
  #----------------------------------#
  # LOBBY WORLD DEFINITION           #
  #----------------------------------#
  lobby: "lobby"

language:
  
  #----------------------------------#
  # PLUGIN STUFF                     #
  #----------------------------------#
  prefix: '&#ff3f1f&lSimpleTools &e&l»'
  adminchatPrefix: '&#ff3f1f&lAdminChat &e&l»&r'
  configReloaded: "&aSe ha recargado la configuración!"

  #----------------------------------#
  # ERRORS                          #
  #----------------------------------#
  onlyPlayerCommand: "&cEste comando sólo lo puede ejecutar un jugador"
  noPermission: "&cNo tienes permisos para eso!"
  tooManyArguments: "&cDemasiados argumentos!"
  playerRequired: "&cDebes especificar un jugador!"
  invalidArgument: "&cArgumento no válido!"
  temporarilyDisabled: "&cEste comando está temporalmente desactivado"
  notOnlineOrConnected: "&cEl jugador no existe o no está conectado!"

  #----------------------------------#
  # MESSAGES                         #
  #----------------------------------#
  globalChestTitle: "&8Cofre global"
  spawnSelf: "&7Has sido teletransportado al spawn"
  spawnYouOthers: "&7Has teletransportado a &a%victim% &7al spawn"
  spawnOthersYou: "&7Has sido teletransportado al spawn por &a%sender%"
  lobbySelf: "&7Has sido teletransportado al lobby"
  lobbyYouOthers: "&7Has teletransportado a &a%victim% &7al lobby"
  lobbyOthersYou: "&7Has sido teletransportado al lobby por &a%sender%"
  noLobby: "&cEl lobby no está definido!"
  discordMsg: "&7Aquí tienes nuestro discord, &a%sender% &7:\n&9&nhttps://discord.gg/HHtQ8wU2TK"
  freefallMsg: "&d&lHas sido teletransportado aquí arriba por nuv"
  coordsMsg: "&8[&c%sender%&8 -> &cTú&8] &7Mis coordenadas son:\n&aX: &7%x%\n&aY: &7%y%\n&aZ: &7%z%"
  worldIsBlocked: "&cEste mundo está bloqueado"
  worldBlocked: "&cSe ha bloqueado el mundo"
  worldUnblocked: "&aSe ha desbloqueado el mundo"
  youGotPaidXP: "&e%player% &7te ha dado &e%amount% &7niveles de &aXP"
  notEnoughLevels: "&cNo tienes suficientes niveles para esto!"
  youWasMentioned: "&e%player% &7te ha mencionado!"
  mentionFormat: "&a&l"
  deathTitleMsg: "&c&l%player% ha muerto"
  joinTitleMsg: "&aHa entrado al servidor!"
  leaveTitleMsg: "&cSe ha ido del servidor!"
  joinLeaveNameFormat: "&e"

  # BELOW THIS LINE REQUIRES SERVER RESTART

  #----------------------------------#
  # COMMAND ARGUMENTS                #
  #----------------------------------#
  player: "jugador"
  levels: "niveles"
  times: "veces"
  world: "mundo"

  #----------------------------------#
  # COMMAND DESCRIPTIONS             #
  #----------------------------------#
  spawnDescription: "Teletransporta al jugador al spawn"
  lobbyDescription: "Teletransporta al jugador al lobby"
  discordDescription: "Muestra el discord del servidor"
  freefallDescription: "Teletransporta al jugador a la altura del cielo"
  sendcoordsDescription: "Manda tus coordenadas a otro jugador"
  blockworldDescription: "Bloquea un mundo"
  payxpDescription: "Paga niveles de XP a otro jugador"
  astickDescription: "Te da el Admin Stick"
  reloadDescription: "Recarga la configuración del plugin"
  thunderDescription: "Invoca un rayo en la posición del jugador"
  globalchestDescription: "Abre el cofre global" 

  #----------------------------------#
  # CUSTOM ITEMS                     #
  #----------------------------------#
  scissorsName: "&e&lTijeras"
  scissorsLore: "&7Vacas, cerdos, zombies"
  adminStickName: "&c&lADMIN STICK"
  adminStickLore: "&dUn palo para dominarlos a todos!"

# DO NOT EDIT
file-version: 2
```