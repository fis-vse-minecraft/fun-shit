package cz.vse.fis.minecraft.funshit.powereggs.eggs

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.player.PlayerEggThrowEvent
import org.bukkit.plugin.java.JavaPlugin

class GrenadeEgg(plugin: JavaPlugin) : BaseEgg() {
    override val id: NamespacedKey = NamespacedKey(plugin, "power_egg_grenade")
    override val color: ChatColor = ChatColor.RED
    override val name: String = "Grenade"
    override val ingredients: List<Material> = listOf(Material.EGG, Material.TNT)

    override fun execute(event: PlayerEggThrowEvent) {
        TODO("Not yet implemented")
    }
}