# Patch From List
## Server
### Classic Bukkit
- https://github.com/SparklyPower/SparklyPaper
- https://github.com/DivineMC/DivineMC
- https://github.com/pufferfish-gg/Pufferfish
- https://github.com/Cryptite/Slice
- https://github.com/The-Fafnir/Beef
- https://github.com/LeavesMC/Leaves
- https://github.com/Winds-Studio/Leaf
- https://github.com/PlazmaMC/PlazmaBukkit

### Folia Bukkit
- https://github.com/Edenor-Minecraft/Foldenor
- https://github.com/LuminolMC/Luminol

## Mods
- https://modrinth.com/mod/lithium
- https://github.com/fxmorin/carpet-fixes

# LevelBukkit
## Features
- Experimental: Show which zone has which players in tps command (only region), like
![](public/tps-region-example.png)

# Other
## Features
- Allow soil to moisten from water directly under it

## Optimization
- Optimize CraftServer.getWorld(UUID)
- Lobotomize stuck villagers (off by default)
- getBiome Optimize
- SIMD utilities
- Raid AI Optimize
- Improve map-saving performance
- HashedList Optimize
- Faster sequencing of futures for chunk structure gen
- Optimize state lookup more
- RecipeManager Optimize
- elytra check Optimise, and hand swing Optimize
- Reduce sensor work
- Placing locked hopper no longer send NC updates
- Skip "distanceToSqr" call in "ServerEntity#sendChanges" if the delta movement hasn't changed
- Skip dirty stats copy when requesting player stats
- Cache coordinate key used for nearby players when ticking chunks

## Security
- Safe seed support (off by default)

## Vanilla
- Vanilla hopper support (off by default)
- Vanilla end teleportation behavior

## Fix
- Fix MC-98160 and MC-105103
- Fix MC-93826
- Fix MC-110386
- Fix MC-172801
- Fix concurrency issues when using "imageToBytes" in multiple threads
- Fix 'outdated server' showing in ping before server fully boots
