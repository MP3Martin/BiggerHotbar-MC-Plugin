package xyz.mp3martin.biggerhotbar.biggerhotbar.utils

import org.bukkit.Bukkit.getOnlinePlayers
import xyz.mp3martin.biggerhotbar.biggerhotbar.BiggerHotbar


fun centerHotbar(plugin: BiggerHotbar) {
  val bhIsEnabled = plugin.config.getBoolean("bh_enabled")
  if (bhIsEnabled) {
    for (player in getOnlinePlayers()) {
      if(player.hasPermission("biggerhotbar.hotbar")) {
        if (player.inventory.heldItemSlot != 4) {
          player.inventory.heldItemSlot = 4
        }
      }
    }
  }
}