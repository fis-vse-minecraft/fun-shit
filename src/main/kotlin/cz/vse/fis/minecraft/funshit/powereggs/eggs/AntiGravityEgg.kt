package cz.vse.fis.minecraft.funshit.powereggs.eggs

import org.bukkit.*
import org.bukkit.event.player.PlayerEggThrowEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import kotlin.math.abs

class AntiGravityEgg(plugin: JavaPlugin) : BaseEgg() {
    override val id: NamespacedKey = NamespacedKey(plugin, "power_egg_anti_gravity")
    override val name: String = "Anti-gravity egg"
    override val color: ChatColor = ChatColor.GREEN
    override val ingredients: List<Material> = listOf(Material.EGG, Material.SHULKER_SHELL)

    private val reach = 10.0

    override fun execute(event: PlayerEggThrowEvent) {
        val world = event.egg.world
        val origin = event.egg.location

        world.spawnParticle(Particle.ENCHANTMENT_TABLE, origin, 5000, reach/2, reach/2, reach/2)
        world.playSound(origin, Sound.ENTITY_SHULKER_BULLET_HIT, 3F, 1F)

        world.livingEntities
            .filter { abs(it.location.distance(origin)) < reach }
            .forEach {
                val effect = PotionEffect(
                    PotionEffectType.LEVITATION,
                    100,
                    3,
                    true,
                    false,
                    false
                )

                it.addPotionEffect(effect)
            }
    }
}