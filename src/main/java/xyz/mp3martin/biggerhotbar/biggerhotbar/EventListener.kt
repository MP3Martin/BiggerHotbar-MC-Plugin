package xyz.mp3martin.biggerhotbar.biggerhotbar

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemHeldEvent
import org.bukkit.event.player.PlayerJoinEvent
import xyz.mp3martin.biggerhotbar.biggerhotbar.utils.centerHotbar
import xyz.mp3martin.biggerhotbar.biggerhotbar.utils.moveItemsHotbarInvSmall
import xyz.mp3martin.biggerhotbar.biggerhotbar.utils.scheduleSyncDelayedTask
import kotlin.math.min


class EventListener(plugin: BiggerHotbar) : Listener {
  private val plugin: BiggerHotbar

  init {
    this.plugin = plugin
  }

  @EventHandler
  fun onPlayerItemHeldEvent(event: PlayerItemHeldEvent) {
    val oldSlot = event.player.inventory.heldItemSlot
    val bhIsEnabled = plugin.config.getBoolean("bh_enabled")
    val maxMovesAtOnce = plugin.config.getInt("maxMovesAtOnce")
    val mode = plugin.config.getString("mode")
    if (bhIsEnabled && event.player.hasPermission("biggerhotbar.hotbar")) {
      // - BiggerHotbar is enabled -
      // - Player has permission -

      if (mode == "sides") {
        // - Mode is sides -
        scheduleSyncDelayedTask(plugin, {
          val newSlot = event.player.inventory.heldItemSlot
          if (oldSlot != newSlot) {
            val moveLeftOldSlot = (oldSlot in 6..8)
            val moveRightOldSlot = (oldSlot in 0..2)

            val moveLeftNewSlot = (newSlot in 0..2)
            val moveRightNewSlot = (newSlot in 6..8)

            if ((moveLeftOldSlot && moveLeftNewSlot) || ((oldSlot in 6..7) && (newSlot in 7..8))) {
              event.player.inventory.heldItemSlot = 6
              moveItemsHotbarInvSmall(event.player, 0)
            } else if ((moveRightOldSlot && moveRightNewSlot) || ((oldSlot in 1..2) && (newSlot in 0..1))) {
              event.player.inventory.heldItemSlot = 2
              moveItemsHotbarInvSmall(event.player, 1)
            }
          }
        }, 1)
      } else {
        // - Mode is center -
        scheduleSyncDelayedTask(plugin, {
          val oldSlot: Int = event.player.inventory.heldItemSlot

          event.player.inventory.heldItemSlot = 4

          val scrLeft: Int = oldSlot - 4
          val scrRight: Int = 4 - oldSlot

          if (oldSlot != 4) {
            if (oldSlot > 4) {
              for (i in 0 until min(scrLeft, maxMovesAtOnce)) {
                moveItemsHotbarInvSmall(event.player, 0)
              }
            } else {
              for (i in 0 until min(scrRight, maxMovesAtOnce)) {
                moveItemsHotbarInvSmall(event.player, 1)
              }
            }
          }
        }, 1)
      }
    }
    return
  }

  @EventHandler
  fun onPlayerJoinEvent(event: PlayerJoinEvent) {
    val mode = plugin.config.getString("mode")
    if (mode == "center") {
      centerHotbar(plugin)
    }
  }
}