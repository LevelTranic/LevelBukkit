# Patch From List
- https://github.com/SparklyPower/SparklyPaper
- https://github.com/DivineMC/DivineMC
- https://github.com/Edenor-Minecraft/Foldenor
- https://github.com/pufferfish-gg/Pufferfish
- https://github.com/Cryptite/Slice
- https://github.com/The-Fafnir/Beef
- https://github.com/LeavesMC/Leaves
- https://github.com/Winds-Studio/Leaf
- https://github.com/PlazmaMC/PlazmaBukkit

# LevelBukkit Patch
- Experimental: Show which zone has which players in tps command (only region)

# Other Patch
- Safe seed support (off by default)
- Fix MC-98160 and MC-105103
- Fix MC-93826
- Fix MC-110386
- Fix MC-172801
- Optimize CraftServer.getWorld(UUID)
- getBiome Optimize
- Skip "distanceToSqr" call in "ServerEntity#sendChanges" if the delta movement hasn't changed
- Fix concurrency issues when using "imageToBytes" in multiple threads
- Skip dirty stats copy when requesting player stats
- Cache coordinate key used for nearby players when ticking chunks
- SIMD utilities
- Improve map-saving performance
- ai.raid
- entity.fast_elytra_check + entity.fast_hand_swing
- RecipeManager Optimize
- Reduce sensor work
- Fix 'outdated server' showing in ping before server fully boots
- Allow soil to moisten from water directly under it
- Lobotomize stuck villagers (off by default)
- Placing locked hopper no longer send NC updates
- Vanilla hopper support (off by default)
- HashedList
- Faster sequencing of futures for chunk structure gen
- Optimise state lookup more

