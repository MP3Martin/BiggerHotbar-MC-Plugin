package xyz.mp3martin.biggerhotbar.biggerhotbar.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import xyz.mp3martin.biggerhotbar.biggerhotbar.BiggerHotbar
import xyz.mp3martin.biggerhotbar.biggerhotbar.utils.Settings.formatMessage
import xyz.mp3martin.biggerhotbar.biggerhotbar.utils.centerHotbar


class BiggerhotbarCommand(plugin: BiggerHotbar) : TabExecutor {

  private val plugin: BiggerHotbar

  init {
    this.plugin = plugin
  }

  private fun toggleBiggerHotbar(value: Boolean? = null) {
    val bhIsEnabled = plugin.config.getBoolean("bh_enabled")
    if (value == null) {
      toggleBiggerHotbar(!bhIsEnabled)
    } else {
      plugin.config.set("bh_enabled", value)
    }

    plugin.saveConfig()
    plugin.reloadConfig()
  }

  override fun onCommand(sender: CommandSender, command: Command, label: String, sysargs: Array<out String>): Boolean {
    var args = sysargs
    if (sender.hasPermission("biggerhotbar.commands")) {
      // --------- \\

      val p = sender

      if (args.getOrNull(0) == null) args = arrayOf("status")

      when (args[0]) {
        "enable" -> {
          toggleBiggerHotbar(true)
          centerHotbar(plugin)
          p.sendMessage(formatMessage("§aEnabled§r BiggerHotbar"))
        }

        "disable" -> {
          toggleBiggerHotbar(false)
          centerHotbar(plugin)
          p.sendMessage(formatMessage("§cDisabled§r BiggerHotbar"))
        }

        "toggle" -> {
          toggleBiggerHotbar()
          centerHotbar(plugin)
          val status = if (plugin.config.getBoolean("bh_enabled")) {
            "§aEnabled§r"
          } else {
            "§cDisabled§r"
          }
          p.sendMessage(formatMessage("$status BiggerHotbar"))
        }

        "status" -> {
          val status = if (plugin.config.getBoolean("bh_enabled")) {
            "§aenabled§r"
          } else {
            "§cdisabled§r"
          }
          p.sendMessage(formatMessage("BiggerHotbar is $status"))
        }

        "mode" -> {
          val mode = plugin.config.getString("mode")
          if (args.getOrNull(1) == null) {
            p.sendMessage(formatMessage("Current mode is §3$mode"))
            p.sendMessage(formatMessage("All available modes are: §3center§r, §3sides"))
          } else if (args[1].lowercase() == "center") {
            plugin.config.set("mode", "center")
            centerHotbar(plugin)
            p.sendMessage(formatMessage("Successfully changed mode to §3center"))
            plugin.saveConfig()
            plugin.reloadConfig()
          } else if (args[1].lowercase() == "sides") {
            plugin.config.set("mode", "sides")
            p.sendMessage(formatMessage("Successfully changed mode to §3sides"))
            plugin.saveConfig()
            plugin.reloadConfig()
          } else {
            return false
          }
        }

        "reload" -> {
          val success = try {
            plugin.reloadConfig()
            1
          } catch (e: Throwable) {
            0
          }
          if (success == 1) {
            p.sendMessage(formatMessage("§aSuccessfully §rreloaded BiggerHotbar"))
          } else {
            p.sendMessage(formatMessage("§cError when §rreloading BiggerHotbar"))
          }
        }

        "version" -> {
          p.sendMessage(formatMessage("§rPlugin version: §3${plugin.description.version}"))
        }

//        "test" -> {
//          p.sendMessage(formatMessage("§a§lOk"))
//        }

        else -> {
          return false
        }
      }
      return true
      // --------- \\
    } else {
      sender.sendMessage(formatMessage("§cNo permission!"))
      return true
    }
  }

  override fun onTabComplete(
    sender: CommandSender,
    command: Command,
    alias: String,
    args: Array<out String>
  ): MutableList<String>? {
    if (sender.hasPermission("biggerhotbar.commands")) {
      return when (args.size) {
        1 -> {
          mutableListOf("enable", "disable", "toggle", "status", "mode", "reload", "version")
        }

        2 -> {
          when (args[0]) {
            "mode" -> {
              mutableListOf("center", "sides")
            }

            else -> {
              null
            }
          }
        }

        else -> {
          null
        }
      }
    }
    return mutableListOf("")
  }
}
