package com.aesireanempire.eplus.blocks

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack


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
    def init() = {
        GameRegistry.addShapedRecipe(
            new ItemStack(BlockAdvEnchantmentTable),
            "gbg", "oto", "geg",
            'g'.asInstanceOf[Character], Items.gold_ingot, 'b'.asInstanceOf[Character], Items.writable_book,
            'o'.asInstanceOf[Character], Blocks.obsidian, 't'.asInstanceOf[Character], Blocks.enchanting_table,
            'e'.asInstanceOf[Character], Items.ender_eye
        )
    }

}
