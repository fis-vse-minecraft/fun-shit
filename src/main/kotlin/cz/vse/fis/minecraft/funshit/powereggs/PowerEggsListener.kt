package cz.vse.fis.minecraft.funshit.powereggs

import cz.vse.fis.minecraft.funshit.powereggs.eggs.ImplosionGrenadeEgg
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerEggThrowEvent
import org.bukkit.plugin.java.JavaPlugin

class PowerEggsListener(private val plugin: JavaPlugin) : Listener {

    private val registeredEggs: List<PowerEgg> = listOf(
        ImplosionGrenadeEgg(plugin)
    )

    private val logger = plugin.logger

    @EventHandler
    fun onPlayerEggThrowEvent(event: PlayerEggThrowEvent) {
        val power = event.egg.getMetadata("power")

        // If the power metadata is empty, the egg is not enchanted
        // and therefore no action is require
        if (power.isEmpty()) return

        val id = power.first()
        val egg = registeredEggs.find { it.id == id.asString() }

        if (egg == null) {
            logger.info("Unknown egg power with value [${id.asString()}]!")
            return
        }

        // Let the actual implementation do the funny thing
        egg.execute(event)
    }
}
