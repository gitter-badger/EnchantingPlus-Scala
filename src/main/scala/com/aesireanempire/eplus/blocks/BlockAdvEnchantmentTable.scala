package com.aesireanempire.eplus.blocks

import com.aesireanempire.eplus.EnchantingPlus
import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.tabs.CreativeTabBlocks
import net.minecraft.block.state.IBlockState
import net.minecraft.block.{BlockPistonBase, BlockEnchantmentTable}
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{EnumFacing, BlockPos}
import net.minecraft.world.World

object BlockAdvEnchantmentTable extends BlockEnchantmentTable() {

    final val NAME = "advEnchantmentTable"
    setCreativeTab(CreativeTabBlocks)
    setUnlocalizedName(NAME)

    override def createNewTileEntity(world: World, meta: Int): TileEntity = new TileEntityAdvEnchantmentTable()

    override def onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean = {

        if (player.isSneaking) {
            return false
        }

        player.openGui(EnchantingPlus, 0, world, pos.getX, pos.getY, pos.getZ)

        true
    }

    override def onBlockPlacedBy(world: World, pos:BlockPos, state: IBlockState, entity: EntityLivingBase, stack: ItemStack) {
        val direction = BlockPistonBase.getFacingFromEntity(world, pos, entity)
        // TODO: Figure out what to replace this with
        //var meta = state.getBlock.getMetaFromState(state)

        //world.setBlockMetadataWithNotify(x, y, z, direction, 2)
    }

    override def isFullCube: Boolean = false

    override def isOpaqueCube: Boolean = false
}
