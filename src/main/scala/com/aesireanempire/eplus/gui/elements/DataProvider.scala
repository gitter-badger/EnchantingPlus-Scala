package com.aesireanempire.eplus.gui.elements

import net.minecraft.enchantment.EnchantmentData

abstract class DataProvider[T] {
    var dataSet: Array[T]

    var hasUpdated: Boolean = false

    def setData(data: Array[T]) = {
        if(!dataSet.equals(data)) {
            dataSet = data.clone()
            hasUpdated = true
        }
    }
}
