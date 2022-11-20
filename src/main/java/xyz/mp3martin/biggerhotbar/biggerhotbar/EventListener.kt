package xyz.mp3martin.biggerhotbar.biggerhotbar

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent


class EventListener(plugin: BiggerHotbar) : Listener {
    private val plugin: BiggerHotbar

    init {
        this.plugin = plugin
    }

    @EventHandler
    fun onBlockPlaceEvent(event: BlockPlaceEvent) {
        return
    }
}