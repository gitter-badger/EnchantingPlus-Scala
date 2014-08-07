package com.aesireanempire.eplus.tabs

import com.aesireanempire.eplus.blocks.BlockAdvEnchantmentTable
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

object CreativeTabBlocks extends CreativeTabs("eplus.blocks") {
  override def getTabIconItem: Item = Item.getItemFromBlock(BlockAdvEnchantmentTable)
}
