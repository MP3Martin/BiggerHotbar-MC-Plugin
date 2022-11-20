package xyz.mp3martin.biggerhotbar.biggerhotbar

import org.bukkit.plugin.java.JavaPlugin
import xyz.mp3martin.biggerhotbar.biggerhotbar.commands.BiggerhotbarCommand

class BiggerHotbar : JavaPlugin() {
  override fun onEnable() {
    // Plugin startup logic
    logger.info("Hello World!!")
    getCommand("biggerhotbar")!!.executor = BiggerhotbarCommand(this)
    this.server.pluginManager.registerEvents(EventListener(this), this)

    config.addDefault("bh_enabled", true)
    config.addDefault("maxMovesAtOnce", 4)
    config.options().copyDefaults(true)
    saveConfig()

//    everyTick(this)
  }

  override fun onDisable() {
    // Plugin shutdown logic
  }
}