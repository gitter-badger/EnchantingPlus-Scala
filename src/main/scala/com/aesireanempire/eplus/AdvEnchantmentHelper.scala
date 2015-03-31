package com.aesireanempire.eplus

import java.util

import net.minecraft.enchantment.{Enchantment, EnchantmentData, EnchantmentHelper}
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

import scala.collection.JavaConversions._

object AdvEnchantmentHelper {
    def getEnchantmentList: Array[Enchantment] = {
        // We can't access enchantmentsList directly, but we know it is an array of 256 items referenced by their index.
        var enchantmentList: List[Enchantment] = List()
        for( i <- 0 to 255) {
            val e = Enchantment.getEnchantmentById(i)
            if(e != null){
                enchantmentList = e :: enchantmentList
            }
        }
        enchantmentList.toArray
    }

    def buildEnchantmentList(itemStack: ItemStack): Array[EnchantmentData] = {
        val enchantmentsOnItem = getEnchantmentsOn(itemStack)

        var possibleEnchantments: Array[Enchantment] = getEnchantmentList.filter {
            e => e != null && (e.canApplyAtEnchantingTable(itemStack) || (isBook(itemStack) && e.isAllowedOnBooks))
        }

        for (enchantment <- enchantmentsOnItem) {
            possibleEnchantments = possibleEnchantments.filter(e => e.canApplyTogether(enchantment.enchantmentobj))
        }

        enchantmentsOnItem ++: possibleEnchantments.map(e => new EnchantmentData(e, 0))
    }

    def getEnchantmentsOn(itemStack: ItemStack): Array[EnchantmentData] = {
        EnchantmentHelper.getEnchantments(itemStack).asInstanceOf[util.Map[Int, Int]].map(e => new EnchantmentData(Enchantment.getEnchantmentById(e._1), e._2)).toArray
    }

    def isBook(itemStack: ItemStack): Boolean = {
        if (itemStack == null) return false
        def item = itemStack.getItem
        item.equals(Items.book) || item.equals(Items.enchanted_book)
    }
}
