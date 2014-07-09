package com.aesireanempire.eplus

import com.aesireanempire.eplus.blocks.entities.TileEntityAdvEnchantmentTable
import com.aesireanempire.eplus.inventory.{SlotArmor, SlotEnchantment, TableInventory}
import net.minecraft.enchantment.{EnchantmentHelper, EnchantmentData, Enchantment}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{Container, IInventory, Slot}
import net.minecraft.item.ItemStack
import scala.collection.JavaConversions._


class ContainerAdvEnchantment(player: EntityPlayer, tile: TileEntityAdvEnchantmentTable) extends Container {

    val tableInventory: IInventory = new TableInventory(this, "enchant", true, 1)

    var dataSet = Array.empty[EnchantmentData]
    var hasUpdated = false

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
            addSlotToContainer(new SlotArmor(i, player, 39 - i, 7, 24 + i * 19))
        }
    }

    override def canInteractWith(player: EntityPlayer): Boolean = true

    override def onCraftMatrixChanged(par1IInventory: IInventory): Unit = {
        super.onCraftMatrixChanged(par1IInventory)

        val itemStack: ItemStack = par1IInventory.getStackInSlot(0)

        var newEnchantmentList = Array.empty[EnchantmentData]
        if (itemStack != null) {
            newEnchantmentList = AdvEnchantmentHelper.buildEnchantmentList(itemStack)
        }

        if (!dataSet.equals(newEnchantmentList)) {
            dataSet = newEnchantmentList
            hasUpdated = true
        }
    }

    def enchantItem(player : EntityPlayer, enchants: collection.mutable.Map[Int, Int], cost : Int): Boolean = {
        val itemStack = tableInventory.getStackInSlot(0)

        if(itemStack == null) return false

        EnchantmentHelper.setEnchantments(enchants, itemStack)
        true
    }

    override def transferStackInSlot(player : EntityPlayer, slot : Int): ItemStack = {
        null
    }
}
