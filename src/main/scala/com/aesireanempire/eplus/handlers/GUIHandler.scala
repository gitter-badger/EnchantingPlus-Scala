package com.aesireanempire.eplus.handlers

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.{ContainerAdvEnchantment, GUIAdvEnchantment}
import cpw.mods.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

object GUIHandler extends IGuiHandler {
  override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
    val te = world.getTileEntity(x, y, z)
    ID match {
      case 0 => new ContainerAdvEnchantment(player, te.asInstanceOf[TileEntityAdvEnchantmentTable])
    }
  }

  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
    val te = world.getTileEntity(x, y, z)
    ID match {
      case 0 => new GUIAdvEnchantment(player, te.asInstanceOf[TileEntityAdvEnchantmentTable])
    }
  }
}
