package com.aesireanempire.eplus.inventory

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack

class SlotArmor(armorType: Int, player: EntityPlayer, id: Int, x: Int, y: Int) extends Slot(player.inventory, id, x,
  y) {
  override def getSlotStackLimit: Int = 1

  override def isItemValid(itemStack: ItemStack): Boolean = {
    itemStack match {
      case x: Any => x.getItem.isValidArmor(x, armorType, player)
      case _ => false
    }
  }
}
