package com.aesireanempire.eplus.inventory

import net.minecraft.inventory.{Container, IInventory, Slot}

class SlotEnchantment(container: Container, inventory: IInventory, id: Int, x: Int, y: Int) extends Slot(inventory, id,
    x, y) {
    override def getSlotStackLimit: Int = 1
}
