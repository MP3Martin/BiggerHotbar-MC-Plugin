package xyz.mp3martin.biggerhotbar.biggerhotbar.utils

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import xyz.mp3martin.biggerhotbar.biggerhotbar.BiggerHotbar


fun moveItemsHotbarInvSmall(plugin: BiggerHotbar, player: Player, direction: Int) {
  val inv = player.inventory
  val items: MutableMap<Int, Any> = HashMap()
  for (i in 0..inv.size) {
    if (inv.getItem(i) != null) {
      items[i] = inv.getItem(i)
    } else {
      items[i] = "NOOO"
    }
  }
  fun moveItem(from: Int, to: Int) {
    val item = if (items[from] is String) {
      null
    } else {
      items[from]
    }
    player.inventory.setItem(to, item as ItemStack?)
  }
  fun moveItemReverse(from: Int, to: Int) {
    moveItem(to, from)
  }
  if (direction == 0) {
    moveItem(0, 27)
    for (i in 1..8) {
        moveItem(i, i-1)
    }

    for (i in 27..34) {
        moveItem(i, i+1)
    }
    moveItem(35, 8)
  } else {
    moveItemReverse(0, 27)
    for (i in 1..8) {
        moveItemReverse(i, i-1)
    }

    for (i in 27..34) {
        moveItemReverse(i, i+1)
    }
    moveItemReverse(35, 8)
  }
}
