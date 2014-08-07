package com.aesireanempire.eplus.blocks.entities

import net.minecraft.tileentity.TileEntity
import net.minecraftforge.common.util.ForgeDirection

class TileEntityAdvEnchantmentTable extends TileEntity {
  def direction: ForgeDirection = ForgeDirection.getOrientation(getBlockMetadata)

}
