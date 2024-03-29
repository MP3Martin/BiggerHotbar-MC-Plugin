package xyz.mp3martin.biggerhotbar.biggerhotbar

import com.jeff_media.updatechecker.UpdateCheckSource
import com.jeff_media.updatechecker.UpdateChecker
import org.bukkit.plugin.java.JavaPlugin
import xyz.mp3martin.biggerhotbar.biggerhotbar.commands.BiggerhotbarCommand


class BiggerHotbar : JavaPlugin() {
  override fun onEnable() {
    // Plugin setup
    logger.info("BiggerHotbar is working!")
    getCommand("biggerhotbar")!!.executor = BiggerhotbarCommand(this)
    this.server.pluginManager.registerEvents(EventListener(this), this)
    MetricsLite(this, 18959)

    // Config setup
    val configItemsMap = mapOf(
      "bh_enabled" to false,
      "maxMovesAtOnce" to 4,
      "mode" to "center"
    )

    configItemsMap.forEach { entry ->
      config.addDefault(entry.key, entry.value)
    }

    config.options().copyDefaults(true)
    saveConfig()

//    everyTick(this)

    UpdateChecker(this, UpdateCheckSource.GITHUB_RELEASE_TAG, "MP3Martin/BiggerHotbar-MC-Plugin")
      .setDownloadLink("https://github.com/MP3Martin/BiggerHotbar-MC-Plugin/releases/latest/")
      .setNotifyOpsOnJoin(true)
      .checkNow()
  }

  override fun onDisable() {
    // Plugin shutdown logic
  }
}