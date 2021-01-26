package cz.vse.fis.minecraft.funshit.utilities

import org.bukkit.Location
import org.bukkit.block.Block

object BlockUtilities {
    fun blocksAround(location: Location, radius: Int): List<Block> = blocksAround(location.block, radius)
    fun blocksAround(block: Block, radius: Int): List<Block> {
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