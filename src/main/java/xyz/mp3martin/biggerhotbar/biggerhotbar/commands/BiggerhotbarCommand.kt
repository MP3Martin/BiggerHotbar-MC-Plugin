package xyz.mp3martin.biggerhotbar.biggerhotbar.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import xyz.mp3martin.biggerhotbar.biggerhotbar.BiggerHotbar
import xyz.mp3martin.biggerhotbar.biggerhotbar.utils.Settings.formatMessage
import xyz.mp3martin.biggerhotbar.biggerhotbar.utils.centerHotbar
import xyz.mp3martin.biggerhotbar.biggerhotbar.utils.moveItemsHotbarInvSmall


class BiggerhotbarCommand(plugin: BiggerHotbar) : TabExecutor {

  private val plugin: BiggerHotbar

  init {
    this.plugin = plugin
  }

  private fun toggleBiggerHotbar(value: Boolean? = null) {
    val bhIsEnabled = plugin.config.getBoolean("bh_enabled")

    if (value == null) {
      if (bhIsEnabled) {
        toggleBiggerHotbar(false)
      } else {
        toggleBiggerHotbar(true)
      }
    }
    if (value == true) {
      plugin.config.set("bh_enabled", true)
    } else if (value == false) {
      plugin.config.set("bh_enabled", false)
    }

    plugin.saveConfig()
    plugin.reloadConfig()
  }
  override fun onCommand(sender: CommandSender, command: Command, label: String, sysargs: Array<out String>): Boolean {
    var args = sysargs
    if (sender is Player) {
      if (sender.hasPermission("biggerhotbar.commands")) {
        // --------- \\
        val p: Player = sender

        try {
          args[0]
        } catch (e: Throwable) {
          args = arrayOf("status")
        }

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

          "test" -> {
//          moveItemsHotbarInvSmall(plugin, p, Integer.parseInt(args[1]))
            p.sendMessage(formatMessage("§a§lOk"))
          }

          else -> {
            return false
          }
        }
        return true
        // --------- \\
      }  else {
        sender.sendMessage(formatMessage("§cNo permission!"))
        return true
      }
    } else {
      // is not player
      // logger.info("You are not a player!")
      sender.sendMessage(formatMessage("You are not a player!"))
      return true
    }
  }

  override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
    if (sender.hasPermission("biggerhotbar.commands")) {
      return when (args.size) {
        1 -> {
          mutableListOf("enable", "disable", "toggle", "status", "reload")
        }
        2 -> {
          null
        }
        else -> {
          null
        }
      }
    }
    return null
  }
}