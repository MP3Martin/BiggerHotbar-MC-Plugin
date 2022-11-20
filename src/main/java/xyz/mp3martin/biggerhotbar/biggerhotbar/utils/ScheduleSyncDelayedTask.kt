package xyz.mp3martin.biggerhotbar.biggerhotbar.utils

import org.bukkit.Bukkit
import xyz.mp3martin.biggerhotbar.biggerhotbar.BiggerHotbar


fun scheduleSyncDelayedTask(plugin: BiggerHotbar, runnable: Runnable?, delay: Int): Int {
  return Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, runnable, delay.toLong())
}