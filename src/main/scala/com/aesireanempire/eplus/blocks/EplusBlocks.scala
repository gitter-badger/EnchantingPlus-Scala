package com.aesireanempire.eplus.blocks

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block


object EplusBlocks {

  /**
   * Creates any block objects that need to be initialized.
   */
  def preInit() = {
    GameRegistry.registerBlock(BlockAdvEnchantmentTable, BlockAdvEnchantmentTable.getUnlocalizedName)
  }

  /**
   * Registers all recipes needed for blocks
   */
  def init() = {}

}
