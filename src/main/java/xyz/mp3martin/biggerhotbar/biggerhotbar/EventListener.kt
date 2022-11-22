package xyz.mp3martin.biggerhotbar.biggerhotbar

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
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
  fun onBlockPlaceEvent(event: BlockPlaceEvent) {
    return
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
//          event.player.sendMessage("$oldSlot + $newSlot")
          if (oldSlot != newSlot) {
            val moveLeftOldSlot = (oldSlot == 6 || oldSlot == 7 || oldSlot == 8)
            val moveRightOldSlot = (oldSlot == 0 || oldSlot == 1 || oldSlot == 2)

            val moveLeftNewSlot = (newSlot == 0 || newSlot == 1 || newSlot == 2)
            val moveRightNewSlot = (newSlot == 8 || newSlot == 7 || newSlot == 6)

            if ((moveLeftOldSlot && moveLeftNewSlot) || ((oldSlot == 6 || oldSlot == 7) && (newSlot == 7 || newSlot == 8))) {
              event.player.inventory.heldItemSlot = 6
              moveItemsHotbarInvSmall(plugin, event.player, 0)
            } else if ((moveRightOldSlot && moveRightNewSlot) || ((oldSlot == 1 || oldSlot == 2) && (newSlot == 0 || newSlot == 1))) {
              event.player.inventory.heldItemSlot = 2
              moveItemsHotbarInvSmall(plugin, event.player, 1)
            }
          }
        }, 1)
      } else {
        // - Mode is center -
        scheduleSyncDelayedTask(plugin, {
          val oldSlot = event.player.inventory.heldItemSlot

          event.player.inventory.heldItemSlot = 4

          val scrLeft = oldSlot - 4
          val scrRight = 4 - oldSlot

          if (oldSlot != 4) {
            if (oldSlot > 4) {
              for (i in 0..min(scrLeft, maxMovesAtOnce) - 1) {
                moveItemsHotbarInvSmall(plugin, event.player, 0)
              }
            } else {
              for (i in 0..min(scrRight, maxMovesAtOnce) - 1) {
                moveItemsHotbarInvSmall(plugin, event.player, 1)
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