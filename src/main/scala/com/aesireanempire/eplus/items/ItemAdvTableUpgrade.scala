package com.aesireanempire.eplus.items

import com.aesireanempire.eplus.blocks.BlockAdvEnchantmentTable
import com.aesireanempire.eplus.tabs.CreativeTabItems
import net.minecraft.block.BlockEnchantmentTable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.world.World

object ItemAdvTableUpgrade extends Item {

  setCreativeTab(CreativeTabItems)
  setUnlocalizedName("advTableUpgrade")
  setTextureName("eplus:enchanting_table_upgrade")

  override def onItemUse(itemStack: ItemStack, player: EntityPlayer, world: World, x: Int, y: Int, z: Int, side: Int, p_77648_8_ : Float, p_77648_9_ : Float, p_77648_10_ : Float): Boolean = {
    if (!world.isRemote) {
      if (world.getBlock(x, y, z).isInstanceOf[BlockEnchantmentTable]) {
        world.setBlock(x, y, z, BlockAdvEnchantmentTable)
        world.setTileEntity(x, y, z, BlockAdvEnchantmentTable.createNewTileEntity(world, 0))
        world.getBlock(x, y, z).onBlockPlacedBy(world, x, y, z, player, itemStack)
        itemStack.stackSize -= 1
        return true
      }
    }

    false
  }
}
