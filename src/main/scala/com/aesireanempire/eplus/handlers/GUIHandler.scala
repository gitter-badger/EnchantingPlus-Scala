package com.aesireanempire.eplus.handlers

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.{ContainerAdvEnchantment, GUIAdvEnchantment}
import net.minecraftforge.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.BlockPos
import net.minecraft.world.World

object GUIHandler extends IGuiHandler {
    override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
        val te = world.getTileEntity(new BlockPos(x, y, z))
        ID match {
            case 0 => new ContainerAdvEnchantment(player, te.asInstanceOf[TileEntityAdvEnchantmentTable])
        }
    }

    override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
        val te = world.getTileEntity(new BlockPos(x, y, z))
        ID match {
            case 0 => new GUIAdvEnchantment(player, te.asInstanceOf[TileEntityAdvEnchantmentTable])
        }
    }
}
