package com.aesireanempire.eplus.gui.elements

import com.aesireanempire.eplus.{ContainerAdvEnchantment, GUIAdvEnchantment}
import net.minecraft.enchantment.EnchantmentData
import net.minecraft.util.ResourceLocation

class ListBox(posX: Int, posY: Int, width: Int, height: Int, texture: ResourceLocation,
              screen: GUIAdvEnchantment) extends GuiElement(posX, posY, width, height, 0, texture, screen) {

    private var data = Array.empty[listItem]
    private var dataProvider: ContainerAdvEnchantment = null

    def setData(enchantments: Array[EnchantmentData]) = {

        if (enchantments.isEmpty) {
            data = Array.empty[listItem]
        }

        for (enchantment <- enchantments) {
            data = new listItem(enchantment, posX, posY + 14 * enchantments.indexOf(enchantment), width, 14,
                this) +: data
        }
    }

    def setDataProvider(container: ContainerAdvEnchantment) = {
        dataProvider = container
    }

    override def drawExtras() = {
        for (item <- data) {
            if (item.isVisible) {
                item.draw()
            }
        }
    }

    override def update() = {
        if (dataProvider != null && dataProvider.hasUpdated) {
            setData(dataProvider.dataSet)
        }
    }

    private def getListItem(x: Int, y: Int): listItem = {
        for (list <- data) {
            if (list.isUnderMouse(x, y)) {
                return list
            }
        }
        null
    }

    override def mouseMoved(x: Int, y: Int): Unit = {
        val list = getListItem(x, y)

        if (list != null) {
            list.mouseMoved(x, y)
        }
    }

    override def handleMovementChange(dY: Int) = {
        for (list <- data) {
            list.handleMovementChange(-dY)
        }
    }

    override def isVisible: Boolean = true
}

