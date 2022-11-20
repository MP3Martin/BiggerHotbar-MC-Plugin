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
    moveItem(1, 0)
    moveItem(2, 1)
    moveItem(3, 2)
    moveItem(4, 3)
    moveItem(5, 4)
    moveItem(6, 5)
    moveItem(7, 6)
    moveItem(8, 7)

    moveItem(27, 28)
    moveItem(28, 29)
    moveItem(29, 30)
    moveItem(30, 31)
    moveItem(31, 32)
    moveItem(32, 33)
    moveItem(33, 34)
    moveItem(34, 35)
    moveItem(35, 8)
  } else {
    moveItemReverse(0, 27)
    moveItemReverse(1, 0)
    moveItemReverse(2, 1)
    moveItemReverse(3, 2)
    moveItemReverse(4, 3)
    moveItemReverse(5, 4)
    moveItemReverse(6, 5)
    moveItemReverse(7, 6)
    moveItemReverse(8, 7)

    moveItemReverse(27, 28)
    moveItemReverse(28, 29)
    moveItemReverse(29, 30)
    moveItemReverse(30, 31)
    moveItemReverse(31, 32)
    moveItemReverse(32, 33)
    moveItemReverse(33, 34)
    moveItemReverse(34, 35)
    moveItemReverse(35, 8)
  }
}