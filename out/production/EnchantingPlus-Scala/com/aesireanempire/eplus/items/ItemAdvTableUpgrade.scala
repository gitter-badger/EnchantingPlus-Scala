package com.aesireanempire.eplus.items

import com.aesireanempire.eplus.blocks.BlockAdvEnchantmentTable
import com.aesireanempire.eplus.tabs.CreativeTabItems
import net.minecraft.block.BlockEnchantmentTable
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.{EnumFacing, BlockPos}
import net.minecraft.world.World

object ItemAdvTableUpgrade extends Item {

    final val NAME = "advTableUpgrade"
    setCreativeTab(CreativeTabItems)
    setUnlocalizedName(NAME)

    override def onItemUse(itemStack: ItemStack, player: EntityPlayer, world: World, pos: BlockPos, side: EnumFacing, hitX : Float, hitY : Float, hitZ : Float): Boolean = {
        if (!world.isRemote) {
            if (world.getBlockState(pos).getBlock.isInstanceOf[BlockEnchantmentTable]) {
                world.setBlockState(pos, BlockAdvEnchantmentTable.getDefaultState)
                world.setTileEntity(pos, BlockAdvEnchantmentTable.createNewTileEntity(world, 0))
                world.getBlockState(pos).getBlock.onBlockPlacedBy(world, pos, BlockAdvEnchantmentTable.getDefaultState, player, itemStack)
                itemStack.stackSize -= 1
                return true
            }
        }

        false
    }
}
