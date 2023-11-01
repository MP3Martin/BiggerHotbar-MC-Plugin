package xyz.mp3martin.biggerhotbar.biggerhotbar.utils

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


fun moveItemsHotbarInvSmall(player: Player, direction: Int) {
  val inv = player.inventory
  val items: MutableMap<Int, ItemStack?> = HashMap()
  for (i in 0..inv.size) {
    if (inv.getItem(i) != null) {
      items[i] = inv.getItem(i)
    } else {
      items[i] = null
    }
  }
  fun moveItem(from: Int, to: Int) {
    val item = items[from]
    player.inventory.setItem(to, item)
  }

  fun moveItemReverse(from: Int, to: Int) {
    moveItem(to, from)
  }

  val moveItemFunction = if (direction == 0) ::moveItem else ::moveItemReverse
  moveItemFunction(0, 27)
  for (i in 1..8) {
    moveItemFunction(i, i - 1)
  }

  for (i in 27..34) {
    moveItemFunction(i, i + 1)
  }
  moveItemFunction(35, 8)
}
