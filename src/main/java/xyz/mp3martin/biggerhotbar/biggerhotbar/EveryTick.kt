package xyz.mp3martin.biggerhotbar.biggerhotbar

import org.bukkit.scheduler.BukkitRunnable


fun everyTick(plugin: BiggerHotbar) {
  object: BukkitRunnable() {
    override fun run() {
      //every tick
    }
  }.runTaskTimer(plugin, 0L, 1)
}