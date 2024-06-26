package de.mathisburger.challanges.randomizer

import de.mathisburger.ChallangeEnum
import de.mathisburger.Variables
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.ItemSpawnEvent
import org.bukkit.inventory.ItemStack


/**
 * Listens on block events
 */
class RandomizerBlockListener : Listener {

    companion object {
        var allocations: Map<Material, Material> = mapOf()
    }


    @EventHandler
    fun onBlockDestroy(e: BlockBreakEvent) {
        if (Variables.currentChallanges.indexOf(ChallangeEnum.RANDOMIZER_BLOCK) > -1) {
            e.isCancelled = true;
            val newMaterial = allocations[e.block.type]!!;
            e.block.location.world?.dropItemNaturally(e.block.location, ItemStack(newMaterial))
            e.block.type = Material.AIR;
        }
    }
}