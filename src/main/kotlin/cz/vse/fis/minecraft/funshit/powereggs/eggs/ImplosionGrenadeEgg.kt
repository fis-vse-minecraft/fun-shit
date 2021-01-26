package cz.vse.fis.minecraft.funshit.powereggs.eggs

import cz.vse.fis.minecraft.funshit.powereggs.PowerEgg
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.event.player.PlayerEggThrowEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.util.Vector
import kotlin.random.Random

class ImplosionGrenadeEgg(private val plugin: JavaPlugin) : PowerEgg {
    private val radius: Int = 5

    override val id = NamespacedKey(plugin, "power_egg_implosion_grenade")

    override fun recipe(): Recipe {
        val item = ItemStack(Material.EGG)
        val meta = item.itemMeta

        meta.setDisplayName("${ChatColor.LIGHT_PURPLE}Implosion Grenade Egg${ChatColor.RESET}")
        meta.lore = listOf(id.key)

        item.itemMeta = meta

        val recipe = ShapelessRecipe(id, item)

        recipe.addIngredient(Material.EGG)
        recipe.addIngredient(Material.ENDER_EYE)

        return recipe
    }

    override fun execute(event: PlayerEggThrowEvent) {
        val origin = event.egg.location
        val world = event.egg.world
        val blocks = blocksInRadius(origin.block, radius).filter {
            it.location.distance(origin) < 2 + Random.nextInt(radius + 1)
        }

        val floating = blocks.map {
            val block = world.spawnFallingBlock(
                it.location,
                it.blockData
            )

            block.setGravity(false)
            block.velocity = Vector(
                block.location.x - origin.x,
                block.location.y - origin.y,
                block.location.z - origin.z
            ).multiply(-0.1F)

            block
        }

        blocks.forEach { it.type = Material.AIR }

        world.spawnParticle(Particle.REVERSE_PORTAL, origin, 200, 1.0, 1.0, 1.0)
        world.spawnParticle(Particle.PORTAL, origin, 200, 1.0, 1.0, 1.0)
        world.playSound(origin, Sound.ENTITY_ENDERMAN_TELEPORT, 2F, 1F)

        // Prevent spawning of the chicken
        event.isHatching = false

        Bukkit
            .getScheduler()
            .scheduleSyncDelayedTask(plugin, { floating.forEach { it.remove() } }, 10)
    }

    private fun blocksInRadius(block: Block, radius: Int): List<Block> {
        if (radius <= 0) return listOf(block)

        val blocks = mutableListOf<Block>()

        for (x in block.x - radius..block.x + radius) {
            for (y in block.y - radius..block.y + radius) {
                for (z in block.z - radius..block.z + radius) {
                    blocks += block.world.getBlockAt(x, y, z)
                }
            }
        }

        return blocks
    }
}