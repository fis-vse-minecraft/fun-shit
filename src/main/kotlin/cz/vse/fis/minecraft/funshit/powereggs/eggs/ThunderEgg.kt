package cz.vse.fis.minecraft.funshit.powereggs.eggs

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.Particle
import org.bukkit.event.player.PlayerEggThrowEvent
import org.bukkit.plugin.Plugin
import kotlin.math.abs

class ThunderEgg(plugin: Plugin) : BaseEgg() {
    override val id: NamespacedKey = NamespacedKey(plugin, "power_egg_thunder")
    override val color: ChatColor = ChatColor.BLUE
    override val name: String = "Thunder egg"
    override val ingredients: List<Material> = listOf(Material.EGG, Material.IRON_NUGGET)

    private val reach = 20

    override fun execute(event: PlayerEggThrowEvent) {
        val egg = event.egg
        val world = egg.world
        val origin = egg.location

        world.spawnParticle(Particle.SMOKE_NORMAL, origin, 300)
        world.livingEntities
            .filter {
                abs(it.location.distance(origin)) < reach &&
                it.uniqueId != event.player.uniqueId
            }
            .forEach { entity ->
                repeat (5) { world.strikeLightning(entity.location) }
                entity.damage(10.0, egg)
            }
    }
}