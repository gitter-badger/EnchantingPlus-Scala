package com.aesireanempire.eplus.blocks

import com.aesireanempire.eplus.blocks.Entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.tabs.CreativeTabBlocks
import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World

object BlockAdvEnchantmentTable extends BlockContainer(Material.rock) {

  setCreativeTab(CreativeTabBlocks)
  setBlockName("advEnchantmentTable")

  override def createNewTileEntity(world : World, meta : Int): TileEntity = new TileEntityAdvEnchantmentTable()
}
