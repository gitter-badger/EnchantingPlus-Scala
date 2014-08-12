package com.aesireanempire.eplus.inventory

import net.minecraft.inventory.{Container, InventoryBasic}

class TableInventory(container: Container, name: String, hasName: Boolean, length: Int) extends InventoryBasic(name,
    hasName, length) {

    override def markDirty(): Unit = {
        super.markDirty()
        container.onCraftMatrixChanged(this)
    }
}
