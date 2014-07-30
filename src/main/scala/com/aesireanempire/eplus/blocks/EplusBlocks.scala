package com.aesireanempire.eplus.blocks

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import cpw.mods.fml.common.registry.GameRegistry


object EplusBlocks {

    /**
     * Creates any block objects that need to be initialized.
     */
    def preInit() = {
        GameRegistry.registerBlock(BlockAdvEnchantmentTable, BlockAdvEnchantmentTable.getUnlocalizedName)
        GameRegistry.registerTileEntity(classOf[TileEntityAdvEnchantmentTable], "eplus:advEnchantmentTable")
    }

    /**
     * Registers all recipes needed for blocks
     */
    def init() = {}

}
