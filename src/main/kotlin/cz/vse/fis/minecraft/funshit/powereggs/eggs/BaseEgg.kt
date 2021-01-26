package cz.vse.fis.minecraft.funshit.powereggs.eggs

import cz.vse.fis.minecraft.funshit.powereggs.PowerEgg
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.Recipe
import org.bukkit.inventory.ShapelessRecipe

abstract class BaseEgg : PowerEgg {
    protected abstract val color: ChatColor
    protected abstract val name: String
    protected abstract val ingredients: List<Material>

    override fun recipe(): Recipe {
        val item = ItemStack(Material.EGG)
        val meta = item.itemMeta

        meta.setDisplayName("$color$name${ChatColor.RESET}")
        meta.addEnchant(Enchantment.DURABILITY, 1, true)
        meta.lore = listOf(id.key)

        item.itemMeta = meta

        val recipe = ShapelessRecipe(id, item)

        ingredients.forEach { recipe.addIngredient(it) }

        return recipe
    }
}