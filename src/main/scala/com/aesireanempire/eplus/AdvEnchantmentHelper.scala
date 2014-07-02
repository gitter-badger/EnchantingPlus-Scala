package com.aesireanempire.eplus

import net.minecraft.enchantment.{Enchantment, EnchantmentHelper}
import net.minecraft.item.ItemStack

import scala.collection.JavaConversions._

object AdvEnchantmentHelper {
    def buildEnchantmentList(itemStack: ItemStack): Array[Enchantment] = {
        val enchantmentsOnItem = EnchantmentHelper.getEnchantments(itemStack).keySet().toList

        val possibleEnchantments: Array[Enchantment] = Enchantment.enchantmentsList.filter(e => e != null && e
            .canApplyAtEnchantingTable(itemStack))

        for (id <- enchantmentsOnItem) {
            val enchantment: Enchantment = Enchantment.enchantmentsList(id.asInstanceOf[Int])

            possibleEnchantments.filter(e => e.canApplyTogether(enchantment))
        }

        possibleEnchantments
    }

}
