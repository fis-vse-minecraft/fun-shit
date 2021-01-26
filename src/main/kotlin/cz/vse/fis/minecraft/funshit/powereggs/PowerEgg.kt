package cz.vse.fis.minecraft.funshit.powereggs

import org.bukkit.NamespacedKey
import org.bukkit.event.player.PlayerEggThrowEvent
import org.bukkit.inventory.Recipe

interface PowerEgg {
    val id: NamespacedKey
    fun recipe(): Recipe
    fun execute(event: PlayerEggThrowEvent)
}