package cz.vse.fis.minecraft.funshit.powereggs

import org.bukkit.event.player.PlayerEggThrowEvent

interface PowerEgg {
    val id: String
    fun execute(event: PlayerEggThrowEvent)
}