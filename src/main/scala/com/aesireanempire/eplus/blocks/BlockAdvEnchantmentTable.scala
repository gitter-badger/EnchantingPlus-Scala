package com.aesireanempire.eplus.blocks

import com.aesireanempire.eplus.EnchantingPlus
import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.tabs.CreativeTabBlocks
import net.minecraft.block.BlockEnchantmentTable
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IIcon
import net.minecraft.world.World

object BlockAdvEnchantmentTable extends BlockEnchantmentTable() {

    setCreativeTab(CreativeTabBlocks)
    setBlockName("advEnchantmentTable")

    private var topIcon: IIcon = null
    private var bottomIcon: IIcon = null

    override def createNewTileEntity(world: World, meta: Int): TileEntity = new TileEntityAdvEnchantmentTable()

    override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, side: Int, dx: Float,
                                  dy: Float, dz: Float): Boolean = {

        if (player.isSneaking) {
            return false
        }

        player.openGui(EnchantingPlus, 0, world, x, y, z)

        true
    }

    override def getIcon(side : Int, meta : Int): IIcon = {
        side match {
            case 0 => bottomIcon
            case 1 => topIcon
            case _ => blockIcon
        }
    }

    override def registerBlockIcons(iconRegister : IIconRegister): Unit = {
        blockIcon =  iconRegister.registerIcon("eplus:enchanting_table_side")
        bottomIcon = iconRegister.registerIcon("eplus:enchanting_table_bottom")
        topIcon = iconRegister.registerIcon("eplus:enchanting_table_top")
    }
}
