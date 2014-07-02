package com.aesireanempire.eplus

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.inventory.{SlotArmor, SlotEnchantment, TableInventory}
import net.minecraft.enchantment.{Enchantment, EnchantmentHelper}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{Container, IInventory, Slot}
import net.minecraft.item.ItemStack

class ContainerAdvEnchantment(player: EntityPlayer, tile: TileEntityAdvEnchantmentTable) extends Container {

    val tableInventory: IInventory = new TableInventory(this, "enchant", true, 1)

    var enchantmentList: Array[Enchantment] = null

    addSlotToContainer(new SlotEnchantment(this, tableInventory, 0, 37, 17))
    bindPlayerInventory()

    def bindPlayerInventory() = {
        for (i <- 0 until 3) {
            for (j <- 0 until 9) {
                addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 17 + j * 18 + 26, 91 + i * 18))
            }
        }

        for (i <- 0 until 9) {
            addSlotToContainer(new Slot(player.inventory, i, 17 + i * 18 + 26, 149))
        }

        for (i <- 0 until 4) {
            addSlotToContainer(new SlotArmor(i, player, 36 - i, 7, 24 + i * 19))
        }
    }

    override def canInteractWith(player: EntityPlayer): Boolean = true

    override def onCraftMatrixChanged(par1IInventory: IInventory): Unit = {
        super.onCraftMatrixChanged(par1IInventory)

        val itemStack: ItemStack = par1IInventory.getStackInSlot(0)

        if(itemStack != null) {
            enchantmentList = AdvEnchantmentHelper.buildEnchantmentList(itemStack)
        }
    }
}
