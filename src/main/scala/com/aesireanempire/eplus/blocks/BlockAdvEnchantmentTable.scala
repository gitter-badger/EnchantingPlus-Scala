package com.aesireanempire.eplus.blocks

import com.aesireanempire.eplus.EnchantingPlus
import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.render.BlockRenderingHandler
import com.aesireanempire.eplus.tabs.CreativeTabBlocks
import net.minecraft.block.{BlockPistonBase, BlockEnchantmentTable}
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IIcon
import net.minecraft.world.World

object BlockAdvEnchantmentTable extends BlockEnchantmentTable() {

    setCreativeTab(CreativeTabBlocks)
    setBlockName("advEnchantmentTable")
    setHardness(10.0F)
    setResistance(4000.0F)

    override def createNewTileEntity(world: World, meta: Int): TileEntity = new TileEntityAdvEnchantmentTable()

    override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, dx: Float,
                                  dy: Float, dz: Float): Boolean = {

        if (player.isSneaking) {
            return false
        }

        player.openGui(EnchantingPlus, 0, world, x, y, z)

        true
    }


    override def onBlockPlacedBy(world: World, x: Int, y: Int, z: Int, entity: EntityLivingBase, statck: ItemStack) {
        val direction = BlockPistonBase.determineOrientation(world, x, y, z, entity)
        world.setBlockMetadataWithNotify(x, y, z, direction, 2)
    }

    override def getRenderType: Int = BlockRenderingHandler.getRenderId

    override def renderAsNormalBlock(): Boolean = false

    override def isOpaqueCube: Boolean = false
}
