package xyz.mp3martin.biggerhotbar.biggerhotbar.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import xyz.mp3martin.biggerhotbar.biggerhotbar.BiggerHotbar
import xyz.mp3martin.biggerhotbar.biggerhotbar.utils.Settings.formatMessage


class BiggerhotbarCommand(plugin: BiggerHotbar) : TabExecutor {

    private val plugin: BiggerHotbar

    init {
        this.plugin = plugin
    }

    private fun toggleBiggerHotbar(value: Boolean? = null) {
        val fwIsEnabled = plugin.config.getString("bh_enabled")

        if (value == null) {
            if (fwIsEnabled == "true") {
                toggleBiggerHotbar(false)
            } else if (fwIsEnabled == "false") {
                toggleBiggerHotbar(true)
            }
        }
        if (value == true) {
            plugin.config.set("bh_enabled", "true")
        } else if (value == false) {
            plugin.config.set("bh_enabled", "false")
        }

        plugin.saveConfig()
        plugin.reloadConfig()
    }
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player){
            // --------- \\
            val p : Player = sender
            
            when (args[0]) {
                "enable" -> {
                    toggleBiggerHotbar(true)
                    p.sendMessage(formatMessage("§aEnabled§r BiggerHotbar"))
                }
                "disable" -> {
                    toggleBiggerHotbar(false)
                    p.sendMessage(formatMessage("§cDisabled§r BiggerHotbar"))
                }
                "toggle" -> {
                    toggleBiggerHotbar()
                    val status = if (plugin.config.getString("bh_enabled") == "true") {
                        "§aEnabled§r"
                    } else {
                        "§cDisabled§r"
                    }
                    p.sendMessage(formatMessage("$status BiggerHotbar"))
                }
                "status" -> {
                    val status = if (plugin.config.getString("bh_enabled") == "true") {
                        "§aenabled§r"
                    } else {
                        "§cdisabled§r"
                    }
                    p.sendMessage(formatMessage("BiggerHotbar is $status"))
                }
                "test" -> {
                    p.sendMessage(formatMessage("ok"))

                }
                else -> {
                    return false
                }
            }
            return true
            // --------- \\
        } else {
            // is not player
            // logger.info("You are not a player!")
            sender.sendMessage(formatMessage("You are not a player!"))
            return true
        }
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String>? {
        if (args.size == 1) {
            return mutableListOf("enable", "disable", "toggle", "status")
        } else if (args.size == 2) {
            return null
        }
        return null
    }
}