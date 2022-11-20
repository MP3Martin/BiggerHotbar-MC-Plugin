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
    if (bhIsEnabled && event.player.hasPermission("biggerhotbar.hotbar")) {
      // - BiggerHotbar is enabled -
      // - Player has permission -

      scheduleSyncDelayedTask(plugin, {
        val oldSlot = event.player.inventory.heldItemSlot

        event.player.inventory.heldItemSlot = 4

        var scrLeft = oldSlot - 4
        var scrRight = 4 - oldSlot

        if (oldSlot != 4) {
          if (oldSlot > 4) {
            for (i in 0..min(scrLeft, maxMovesAtOnce) -1) {
              moveItemsHotbarInvSmall(plugin, event.player, 0)
            }
          } else {
            for (i in 0..min(scrRight, maxMovesAtOnce) -1) {
              moveItemsHotbarInvSmall(plugin, event.player, 1)
            }
          }
        }
      }, 1)
    }
    return
  }

  @EventHandler
  fun onPlayerJoinEvent(event: PlayerJoinEvent) {
    centerHotbar(plugin)
  }
}